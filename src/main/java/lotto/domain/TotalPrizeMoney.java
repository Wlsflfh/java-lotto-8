package lotto.domain;

public class TotalPrizeMoney {

    private static final int PERCENT = 100;

    private double amount;

    public TotalPrizeMoney() {
        this.amount = 0;
    }

    public double calculateEarningRate(Money money) {
        return (amount / money.getMoney()) * PERCENT;
    }

    public void accumulate(Prize prize) {
        amount += prize.getPrizeMoney();
    }
}
