import java.util.Vector;

public class Catalogue {
    private Vector<Category> categories;

    public Catalogue() {
        categories = new Vector<>();
        Category category1 = new Category("Cupcakes");
        categories.add(category1);
        Category category2 = new Category("Russian dessert");
        categories.add(category2);
        Category category3 = new Category("Eastern sweets");
        categories.add(category3);
        Category category4 = new Category("Donuts");
        categories.add(category4);
    }

    public void addCategory(String Name) {
        Category newCategory = new Category(Name);
        categories.add(newCategory);
    }


    public Vector<Category> getCategories() {
        return categories;
    }

    public Item searchItem(String searched , int nameOrBrand) {
        for (Category category : categories) {
            for (Item item : category.getItems()) {
                if (nameOrBrand == 1) {
                    if (item.getName().equals(searched))
                        return item;
                } else if (nameOrBrand == 2) {
                    if (item.getBrand().equals(searched))
                        return item;
                }
            }
        }
        return null;
    }

    public void viewCategory(String Name) {
        for (Category category : categories) {
            if (category.getName().equals(Name))
                category.viewCategory();
        }
    }

    public void viewCatalogue() {
        for (Category category : categories)
            category.viewCategory();
    }

}
