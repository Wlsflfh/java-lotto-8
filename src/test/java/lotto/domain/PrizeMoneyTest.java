package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrizeMoneyTest {

    @Test
    @DisplayName("총 상금 금액을 올바르게 축적하고 수익률을 계산하는지 확인한다")
    void accumulateEarningRateTest() {
        // given
        PrizeMoney prizeMoney = new PrizeMoney();

        // when
        prizeMoney.accumulate(Prize.PLACE_OF_5ST); // + 5000
        prizeMoney.accumulate(Prize.PLACE_OF_5ST); // + 5000

        // then
        assertEquals(prizeMoney.getEarningRate(new PurchaseAmount(5000)), 200.0);
    }
}
