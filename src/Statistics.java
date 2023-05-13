package src;

import java.util.Vector;

public class Statistics {
    private Vector<Item> items;
    private int monthlySales;
    private int dailySales;

    public void calculateSales() {

    }

    public int getMonthlySales() {
        return monthlySales;
    }

    public int getDailySales() {
        return dailySales;
    }

    public Item getPopularItem() {
        Item item = new Item();
        return item;
    }
}
