import java.util.Vector;

public class Category {
    private String name;
    private Vector<Item> items;
    private String description;

    public Category(String Name) {
        name = Name;
        items = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {

    }

    public void viewItemDetails(Item item) {

    }

    public void viewCategory() {
        System.out.print("\n\n--------------------------------------------------------------------------\n" +
                name + " :\n--------------------------------------------------------------------------\n");
        for (Item item : items) {
            item.displayItem();
            System.out.println("\n");
        }
    }
}
