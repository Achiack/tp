package seedu.address.model.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of products, or a menu.
 */
public class ProductList {

    private List<Product> menu;

    /**
     * Create the product list with populated items.
     */
    public ProductList() {
        this.menu = new ArrayList<>();
        menu.add(new Product("Chicken Rice", 4.50));
        menu.add(new Product("Mixed Rice", 4.50));
        menu.add(new Product("Beef Udon", 7.00));
        menu.add(new Product("Ice Cream", 2.50));
        menu.add(new Product("Caesar Salad", 5.50));
        menu.add(new Product("Smoked Salmon Bagel", 8.50));
        menu.add(new Product("Apple Juice", 1.50));
        menu.add(new Product("Cafe Latte", 3.00));
    }

    /**
     * Returns the item at the specified one-based index.
     */
    public Product getItem(int index) {
        return menu.get(index - 1);
    }

    /**
     * Returns true if the index corresponds to a valid item on the menu.
     */
    public boolean isValidItem(int index) {
        return index > 0 && index < menu.size();
    }
}
