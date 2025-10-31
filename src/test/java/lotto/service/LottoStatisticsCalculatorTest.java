package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.LottoMachine;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.service.dto.LottoStatisticsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoStatisticsCalculatorTest {

    class FixedLottoGenerator implements RandomNumberGenerator {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @Test
    @DisplayName("로또 결과를 올바르게 계산하는지 확인한다")
    void calculateLottoStatisticsTest() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, new FixedLottoGenerator());
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottoMachine.getLottoTickets(), winningLotto);

        // then
        LottoStatisticsDto lottoStatisticsDto = lottoStatisticsCalculator.calculateLottoStatistics(purchaseAmount);
        Map<Prize, Integer> lottoResult = lottoStatisticsDto.getLottoResult();

        assertEquals(1, lottoResult.get(Prize.PLACE_OF_1ST));
        for (Prize prize : Prize.values()) {
            if (prize != Prize.PLACE_OF_1ST) {
                assertEquals(0, lottoResult.get(prize));
            }
        }
    }

    @Test
    @DisplayName("로또 결과에 따른 수익률을 올바르게 계산하는지 확인한다")
    void calculateEarningRateTest() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, new FixedLottoGenerator());
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottoMachine.getLottoTickets(), winningLotto);

        // then
        LottoStatisticsDto lottoStatisticsDto = lottoStatisticsCalculator.calculateLottoStatistics(purchaseAmount);
        assertEquals(lottoStatisticsDto.getEarningRate(), 200000000.0);
    }
}
