package EcommerceSystem;

import java.util.*;

// Handles the checkout process, including payment and shipping
public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = 0;
        double shippingFee = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired())
                throw new IllegalStateException(product.getName() + " is expired");

            if (quantity > product.getQuantity())
                throw new IllegalStateException(product.getName() + " is out of stock");

            subtotal += product.getPrice() * quantity;

            if (product instanceof Shippable)
                toShip.addAll(Collections.nCopies(quantity, (Shippable) product));
        }

        if (!toShip.isEmpty()) shippingFee = 30;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total)
            throw new IllegalStateException("Insufficient balance");

        
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);
        }

        customer.checkBalance(total);

        
        ShippingService.ship(toShip);

        
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %s %.0f\n", quantity, product.getName(),
                    product.getPrice() * quantity);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shippingFee);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer balance after payment: %.0f\n", customer.getBalance());

        cart.clear();
    }
}
