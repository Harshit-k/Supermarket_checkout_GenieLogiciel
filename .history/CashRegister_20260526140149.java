package supermarket_system;

public class CashRegister {
    private static final double BASE_DELIVERY_COST = 10.0;

    private final Cart cart;
    private Customer currentCustomer;
    private boolean checkoutStarted;

    public CashRegister() {
        this.cart = new Cart();
    }

    public void startCheckout(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }
        if (checkoutStarted) {
            throw new IllegalStateException("a checkout is already in progress");
        }

        this.currentCustomer = customer;
        this.checkoutStarted = true;
        this.cart.clear();
    }

    public void scanItem(Item item, int quantity) {
        ensureCheckoutStarted();
        item.removeFromStock(quantity);
        cart.addItem(item, quantity);
    }

    public double computeSubtotal() {
        ensureCheckoutStarted();
        return cart.computeSubtotal();
    }

    public double computeTotal(boolean withHomeDelivery) {
        ensureCheckoutStarted();

        double subtotal = computeSubtotal();
        double discounted = currentCustomer.applyDiscount(subtotal);

        if (!withHomeDelivery) {
            return discounted;
        }

        double deliveryCost = currentCustomer.applyDeliveryDiscount(BASE_DELIVERY_COST);
        return discounted + deliveryCost;
    }

    public Cart getCart() {
        return cart;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public boolean isCheckoutStarted() {
        return checkoutStarted;
    }

    public void finishCheckout() {
        ensureCheckoutStarted();
        checkoutStarted = false;
        currentCustomer = null;
        cart.clear();
    }

    private void ensureCheckoutStarted() {
        if (!checkoutStarted) {
            throw new IllegalStateException("no checkout in progress");
        }
    }
}
