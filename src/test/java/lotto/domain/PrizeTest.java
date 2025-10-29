package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrizeTest {

    @Test
    @DisplayName("matchResult 결과로부터 올바르게 Prize 타입을 반환하는지 확인한다")
    void fromTest() {
        // given
        MatchResult placeOf5st = new MatchResult(3, false);
        MatchResult placeOf4st = new MatchResult(4, false);
        MatchResult placeOf3st = new MatchResult(5, false);
        MatchResult placeOf2st = new MatchResult(5, true);
        MatchResult placeOf1st = new MatchResult(6, false);
        MatchResult none = new MatchResult(2, false);

        // when - then
        assertEquals(Prize.from(placeOf5st), Prize.PLACE_OF_5ST);
        assertEquals(Prize.from(placeOf4st), Prize.PLACE_OF_4ST);
        assertEquals(Prize.from(placeOf3st), Prize.PLACE_OF_3ST);
        assertEquals(Prize.from(placeOf2st), Prize.PLACE_OF_2ST);
        assertEquals(Prize.from(placeOf1st), Prize.PLACE_OF_1ST);
        assertEquals(Prize.from(none), Prize.NONE);
    }
}
