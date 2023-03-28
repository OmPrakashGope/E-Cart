package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Cart {
    int cart_id;
    String name;
    Double price;

    public Cart(int cart_id, String name, Double price) {
        this.cart_id = cart_id;
        this.name = name;
        this.price = price;
    }

    public Cart(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public static boolean addCart(Customer customer, Product product) {
        try {
            String add = "insert into cart(customer_id,product_id) values(" + customer.getId() + "," + product.getId() + "); ";
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Cart getCart(int customer_id, int product_id) {
        String query = "Select cart_id from cart where customer_id = " + customer_id + " and product_id = " + product_id + ";";
        System.out.println(query);
        DatabaseConnection dbConn = new DatabaseConnection();
        ResultSet rs = null;
        try {
            rs = dbConn.getQueryTable(query);
            System.out.println(rs);
            if (rs != null && rs.next()) {
                return new Cart(rs.getInt("cart_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(rs.getInt("cid"));
        return null;
    }
}
