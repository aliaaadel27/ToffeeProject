import java.util.*;
import java.io.*;


public class Buyer extends User {
    private Vector<Order> orders;
    private int loyaltyPoints;

    public Buyer(){
        orders = new Vector<>();
    }

    public Buyer(UserAccount buyer) {
        this.account = new UserAccount(buyer.getUserName(), buyer.getEmail(), buyer.getPassword(), buyer.getAddress(), buyer.getPhoneNumber());
        orders = new Vector<>();
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
    }

    public Order createOrder(int id) {
        Order newOrder = new Order(id);
        return newOrder;
    }

    public Order reOrder(Order pastOrder , int id) {
        Order newOrder = createOrder(id);
        newOrder.reOrder(pastOrder , id);

        return newOrder;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void cancelOrder(Order order) {

    }
    public void viewHistory() {
        for (Order order : orders) {
            order.viewOrder();
            System.out.println("\n\n\n");
        }
        System.out.println("\n--------------------------------------------------------------------------\n");
    }
}
