package lotto.domain.result;

public enum Prize {

    NONE(0, 0, false),
    PLACE_OF_5ST(5_000, 3, false),
    PLACE_OF_4ST(50_000, 4, false),
    PLACE_OF_3ST(1_500_000, 5, false),
    PLACE_OF_2ST(30_000_000, 5, true),
    PLACE_OF_1ST( 2_000_000_000, 6, false);

    private final int prizeMoney;
    private final int matchCount;
    private final boolean hasBonus;

    Prize(int prizeMoney, int matchCount, boolean hasBonus) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
    }

    public static Prize from(MatchResult matchResult) {
        if (matchResult.getMatchCount() == 3) return PLACE_OF_5ST;
        if (matchResult.getMatchCount() == 4) return PLACE_OF_4ST;
        if (matchResult.getMatchCount() == 5) {
            if (matchResult.isBonusMatch()) return PLACE_OF_2ST;
            else if (!matchResult.isBonusMatch()) return PLACE_OF_3ST;
        }
        if (matchResult.getMatchCount() == 6) return PLACE_OF_1ST;
        return NONE;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public boolean getIsBonus() {
        return this.hasBonus;
    }
}