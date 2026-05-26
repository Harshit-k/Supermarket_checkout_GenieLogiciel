package supermarket_system;

import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    private static final double BASE_DELIVERY_COST = 10.0;

    private final Cart currentCart;
    private final POS pos;
    private final Map<String, Item> itemsByName;
    private Customer currentCustomer;
    private boolean checkoutStarted;

    public CashRegister() {
        this.currentCart = new Cart();
        this.pos = new POS();
        this.itemsByName = new HashMap<>();
    }

    public CashRegister(POS pos) {
        this.currentCart = new Cart();
        this.pos = pos == null ? new POS() : pos;
        this.itemsByName = new HashMap<>();
    }

    public void registerItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        itemsByName.put(item.getName().toLowerCase(), item);
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
        this.currentCart.clear();
        this.currentCart.setCustomer(customer);
    }

    public void scanItem(Item item, int quantity) {
        ensureCheckoutStarted();
        item.removeFromStock(quantity);
        currentCart.addItem(item, quantity);
    }

    public void scanItem(String name, int quantity) {
        ensureCheckoutStarted();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        Item item = itemsByName.get(name.toLowerCase());
        if (item == null) {
            throw new IllegalArgumentException("item not found: " + name);
        }
        scanItem(item, quantity);
    }

    public double computeSubtotal() {
        ensureCheckoutStarted();
        return currentCart.computeSubtotal();
    }

    public double computeBill() {
        ensureCheckoutStarted();
        double subtotal = currentCart.getTotal();
        return currentCustomer.applyDiscount(subtotal);
    }

    public double computeTotal(boolean withHomeDelivery) {
        ensureCheckoutStarted();

        double discounted = computeBill();

        if (!withHomeDelivery) {
            return discounted;
        }

        double deliveryCost = currentCustomer.applyDeliveryDiscount(BASE_DELIVERY_COST);
        return discounted + deliveryCost;
    }

    public boolean pay(String card, String pin) {
        ensureCheckoutStarted();
        double amount = computeBill();
        boolean approved = pos.authorizePayment(card, pin, amount);
        if (approved) {
            finishCheckout();
        }
        return approved;
    }

    public Cart getCart() {
        return currentCart;
    }

    public Cart getCurrentCart() {
        return currentCart;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public POS getPos() {
        return pos;
    }

    public boolean isCheckoutStarted() {
        return checkoutStarted;
    }

    public void finishCheckout() {
        ensureCheckoutStarted();
        checkoutStarted = false;
        currentCustomer = null;
        currentCart.clear();
    }

    private void ensureCheckoutStarted() {
        if (!checkoutStarted) {
            throw new IllegalStateException("no checkout in progress");
        }
    }
}
