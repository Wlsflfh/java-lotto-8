package lotto.domain;

public class PrizeMoney {

    private static final int PERCENT = 100;
    private static final int INTI_AMOUNT = 0;

    private double amount;

    public PrizeMoney() {
        this.amount = INTI_AMOUNT;
    }

    public void accumulate(Prize prize) {
        amount += prize.getPrizeMoney();
    }

    public double getEarningRate(PurchaseAmount purchaseAmount) {
        return calculateEarningRate(purchaseAmount);
    }

    private double calculateEarningRate(PurchaseAmount purchaseAmount) {
        return (amount / purchaseAmount.getAmount()) * PERCENT;
    }
}
