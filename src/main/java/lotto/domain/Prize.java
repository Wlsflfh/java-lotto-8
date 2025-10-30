package lotto.domain;

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
        if (PLACE_OF_5ST.getMatchCount() == matchResult.getMatchCount()) return PLACE_OF_5ST;
        if (PLACE_OF_4ST.getMatchCount() == matchResult.getMatchCount()) return PLACE_OF_4ST;
        if (PLACE_OF_3ST.getMatchCount() == matchResult.getMatchCount() && PLACE_OF_3ST.hasBonus() == matchResult.isBonusMatch()) return PLACE_OF_3ST;
        if (PLACE_OF_2ST.getMatchCount() == matchResult.getMatchCount() && PLACE_OF_2ST.hasBonus() == matchResult.isBonusMatch()) return PLACE_OF_2ST;
        if (PLACE_OF_1ST.getMatchCount() == matchResult.getMatchCount()) return PLACE_OF_1ST;
        return NONE;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean hasBonus() {
        return hasBonus;
    }
}
