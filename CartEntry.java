package supermarket_system;

import java.util.Objects;

public class CartEntry {
    private final Item item;
    private int quantity;

    public CartEntry(Item item, int quantity) {
        this.item = Objects.requireNonNull(item, "item cannot be null");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        quantity += amount;
    }

    public double getLineTotal() {
        return item.getPrice(quantity);
    }

    @Override
    public String toString() {
        return String.format("%s x%d = %.2f EUR", item.getName(), quantity, getLineTotal());
    }
}
