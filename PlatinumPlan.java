package supermarket_system;

public class PlatinumPlan implements CustomerPlan {
	private static final double DISCOUNT = 0.30;

    @Override
    public double getDiscount(double total) { return DISCOUNT; }

    @Override
    public double getAnnualFee()  { return 200.0; }

    @Override
    public String getPlanName()   { return "platinum"; }

    @Override
    public double getDeliveryCostMultiplier() { return 0.0; }
}
