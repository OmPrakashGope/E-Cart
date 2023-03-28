package sample;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {
    int oid;

    public Order(int oid) {
        this.oid = oid;
    }

    public int getOid() {
        return oid;
    }

    public static boolean placeOrder(Customer customer, Product product)
    {
        try
        {
            String placeOrder = "insert into orders(customer_id,product_id,status) values("+customer.getId()+","+product.getId()+",'Ordered'); ";
            System.out.println(customer.getId()+" "+product.getId());
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(placeOrder);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public static int placeOrderMultipleProducts(ObservableList<Product> productObservableList,Customer customer)
    {
        int count = 0;
        for(Product product : productObservableList){
            System.out.println(product);
            if(placeOrder(customer,product))
               count++;
        }
        return count;
    }
    public static Order getOrder(int customer_id, int product_id) {
        String query = "Select oid from orders where customer_id = " + customer_id + " and product_id = " + product_id + ";";
        System.out.println(query);
        DatabaseConnection dbConn = new DatabaseConnection();
        ResultSet rs = null;
        try {
            rs = dbConn.getQueryTable(query);
            System.out.println(rs);
            if (rs != null && rs.next()) {
                return new Order(rs.getInt("oid"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(rs.getInt("cid"));
        return null;
    }
}
