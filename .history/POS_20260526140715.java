package supermarket_system;

public class POS {
    public boolean authorizePayment(String card, String pin, double amount) {
        if (card == null || card.trim().isEmpty()) {
            return false;
        }
        if (pin == null || pin.length() < 4) {
            return false;
        }
        return amount >= 0.0;
    }
}
