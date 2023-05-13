package src;

import java.util.Vector;

public class Admin extends User{
    private int adminID;
    private String adminName;
    private Vector<Order> orders;

    public Admin(UserAccount admin) {
        this.account = new UserAccount(admin.getUserName(), admin.getEmail(), admin.getPassword(), admin.getAddress(), admin.getPhoneNumber());
    }
    public int getAdminID() {
        return adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /////////////////////////////  Implement ///////////////////////////////
    public void addItem() {

    }

    public Item editItem(Item item) {
        return item;
    }

    public void cancelItem(Item item) {

    }

    public void viewStatistics() {

    }

    public void viewOrders() {

    }

    public void setLoyalty() {

    }

    public void suspendBuyer(Buyer user) {

    }

    public void addCategory() {

    }
}
