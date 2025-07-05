package EcommerceSystem;

public class Main {
    public static void main(String[] args) {
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, false, 0.2);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 2, false, 0.7);

        Customer customer = new Customer("Eyad", 100000); 
        Cart cart = new Cart();

        cart.add(cheese, 5); // Add 5 units of cheese
        cart.add(cheese, 2); // Add 2 more units of cheese (total = 7)
        cart.add(biscuits, 1); // Add 1 unit of biscuits

        System.out.println("Cart before removal:");
        cart.displayCart();

        cart.remove(cheese, 3); // Remove 3 units of cheese
        System.out.println("\nCart after removing 3 units of cheese:");
        cart.displayCart();

        double subtotal = cart.calculateTotalPrice();
        double shippingFee = cart.getItems().values().stream().anyMatch(qty -> qty > 0) ? 30 : 0;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            System.out.printf("Insufficient balance. Total cost: %.0f, Customer balance: %.0f\n", total, customer.getBalance());
            return;
        }

        CheckoutService.checkout(customer, cart); // Perform checkout
    }
}
