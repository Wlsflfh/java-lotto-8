package lotto.domain;

public class PurchaseAmount {

    private static final int UNIT = 1000;
    private static final int MIN_AMOUNT = 0;
    private static final int NO_REMAINDER = 0;
    private static final int MAX_AMOUNT = 1000000;

    private final int amount;

    public PurchaseAmount(int amount) {
        validateRange(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    public int calculateTicketCount() {
        return amount / UNIT;
    }

    private void validateRange(int amount) {
        if (amount <= MIN_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수만 가능합니다.");
        }

        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000,000원 이하만 가능합니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % UNIT != NO_REMAINDER) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
