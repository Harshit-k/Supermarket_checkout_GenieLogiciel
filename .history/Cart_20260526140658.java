package supermarket_system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartEntry> entries = new ArrayList<>();
    private Customer customer;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addItem(Item item, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }

        CartEntry existing = findEntryByItemId(item.getId());
        if (existing != null) {
            existing.increaseQuantity(quantity);
        } else {
            entries.add(new CartEntry(item, quantity));
        }
    }

    public List<CartEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public double computeSubtotal() {
        double subtotal = 0.0;
        for (CartEntry entry : entries) {
            subtotal += entry.getLineTotal();
        }
        return subtotal;
    }

    public double getTotal() {
        return computeSubtotal();
    }

    public int getTotalUnits() {
        int total = 0;
        for (CartEntry entry : entries) {
            total += entry.getQuantity();
        }
        return total;
    }

    public void clear() {
        entries.clear();
        customer = null;
    }

    private CartEntry findEntryByItemId(String itemId) {
        for (CartEntry entry : entries) {
            if (entry.getItem().getId().equals(itemId)) {
                return entry;
            }
        }
        return null;
    }
}
