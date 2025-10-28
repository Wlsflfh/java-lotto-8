package lotto.domain.model;

public class Money {

    private static final int UNIT = 1000;
    private static final int MIN_AMOUNT = 0;
    private static final int MAX_AMOUNT = 1000000;

    private final int money;

    public Money(String moneyText) {
        int money = validateNumeric(moneyText);
        validateRange(money);
        validateUnit(money);
        this.money = money;
    }

    public int calculateTicketCount() {
        return money / UNIT;
    }

    private int validateNumeric(String moneyText) {
        try {
            return Integer.parseInt(moneyText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자만 가능합니다.");
        }
    }

    private void validateRange(int money) {
        if (money <= MIN_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수만 가능합니다.");
        }

        if (money > MAX_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000,000원 이하만 가능합니다.");
        }
    }

    private void validateUnit(int money) {
        if (money % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
