package EcommerceSystem;

import java.util.LinkedHashMap;
import java.util.Map;

// Represents a shopping cart that holds products and their quantities
public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new LinkedHashMap<>();
    }

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }

        int existingQty = items.getOrDefault(product, 0);
        items.put(product, existingQty + quantity);
    }

    public void remove(Product product, int quantity) {
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException(product.getName() + " is not in the cart.");
        }

        int existingQty = items.get(product);
        if (quantity > existingQty) {
            throw new IllegalArgumentException("Cannot remove more than the existing quantity of " + product.getName());
        }

        if (quantity == existingQty) {
            items.remove(product); // Remove the product entirely if quantity becomes zero
        } else {
            items.put(product, existingQty - quantity); // Reduce the quantity
        }
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void displayCart() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Quantity: " + quantity);
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
