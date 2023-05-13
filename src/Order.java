import java.util.Vector;

public class Order {
    private int ID;
    private Vector<Item> items;
    private String shippingAddress;
    private Status status;
    private Buyer buyer;
    private PaymentMethods paymentMethod;
    private float totalPrice = 0;

    public Order(int id) {
        ID = id;
        items = new Vector<>();
    }

    public void reOrder(Order pastOrder , int id) {
        ID = id;
        for (Item item : pastOrder.getItems())
            this.addItem(item);
        shippingAddress = pastOrder.getShippingAddress();
        status = Status.Processing;
        buyer = pastOrder.getBuyer();
        paymentMethod = pastOrder.getPaymentMethod();
    }

    public int getID() {
        return ID;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public float getTotalPrice() {
        this.calculateTotalPrice();
        return totalPrice;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void calculateTotalPrice() {
        for (Item item : items) {
            totalPrice += (item.getPrice()) - (item.getPrice() * (item.getDiscountPercentage() / 100.0));
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getLoyaltyPoints() {
        return buyer.getLoyaltyPoints();
    }


}
