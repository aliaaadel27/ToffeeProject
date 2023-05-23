import java.awt.*;

public class Item {
    private String name , brand , category , description;
    private int price , id , discountPercentage;
    private int quantity;
    private Image image;

    public Item(){}

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public Image getImage() {
        return image;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Function to display the details of the item
     */
    public void displayItem() {
        System.out.println("    ID : " + id);
        System.out.println("    Name : " + name);
        System.out.println("    Brand : " + brand);
        System.out.println("    Price : " + price);
        System.out.println("    Category : " + category);
        System.out.println("    Discount percentage : " + discountPercentage + "%");
        System.out.println("    Description : " + description);
    }
}
