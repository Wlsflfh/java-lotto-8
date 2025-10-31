package lotto.domain;

public class PrizeStatistics {

    private static final int PERCENT = 100;

    private double amount;

    public PrizeStatistics() {
        this.amount = 0;
    }

    public void accumulate(Prize prize) {
        amount += prize.getPrizeMoney();
    }

    public double getEarningRate(Money money) {
        return calculateEarningRate(money);
    }

    private double calculateEarningRate(Money money) {
        return (amount / money.getMoney()) * PERCENT;
    }
}
