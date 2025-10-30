package lotto.view;

import lotto.domain.Prize;

import static lotto.domain.Prize.*;

public enum PrizeView {

    NONE("NONE"),
    PLACE_OF_5ST_VIEW("3개 일치 (5,000원)"),
    PLACE_OF_4ST_VIEW("4개 일치 (50,000원)"),
    PLACE_OF_3ST_VIEW("5개 일치 (1,500,000원)"),
    PLACE_OF_2ST_VIEW("5개 일치, 보너스 볼 일치 (30,000,000원)"),
    PLACE_OF_1ST_VIEW("6개 일치 (2,000,000,000원)");

    private final String message;

    PrizeView(String message) {
        this.message = message;
    }

    public static PrizeView from(Prize prize) {
        if (prize == PLACE_OF_5ST) return PLACE_OF_5ST_VIEW;
        if (prize == PLACE_OF_4ST) return PLACE_OF_4ST_VIEW;
        if (prize == PLACE_OF_3ST) return PLACE_OF_3ST_VIEW;
        if (prize == PLACE_OF_2ST) return PLACE_OF_2ST_VIEW;
        if (prize == PLACE_OF_1ST) return PLACE_OF_1ST_VIEW;
        return NONE;
    }

    public String getMessage() {
        return message;
    }
}
