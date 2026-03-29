package seedu.address.model.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu of products available for ordering.
 */
public class ProductList {

    private List<Product> menu;

    /**
     * Constructs a new {@code ProductList} with a predefined set of products.
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
     * Returns the product at the specified 1-based index in the menu.
     * @param index 1-based index in the menu.
     * @return menu item.
     */
    public Product getItem(int index) {
        return menu.get(index - 1);
    }

    /**
     * Returns the index of the given {@code Product} in the menu.
     */
    public int getIndex(Product product) {
        return menu.indexOf(product) + 1;
    }

    /**
     * Returns true if the index corresponds to a valid item on the menu.
     */
    public boolean isValidItem(int index) {
        return index > 0 && index < menu.size() + 1;
    }

    /**
     * Compares the order of two products in the menu.
     * @return -1 if {@code p1} comes before {@code p2}, 1 if {@code p1} comes after {@code p2},
     *      and 0 if {@code p1} and {@code p2} are the same product.
     */
    public int compare(Product p1, Product p2) {
        Integer i1 = getIndex(p1);
        Integer i2 = getIndex(p2);
        return i1.compareTo(i2);
    }
}
