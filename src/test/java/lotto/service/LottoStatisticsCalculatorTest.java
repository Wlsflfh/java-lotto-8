package lotto.service;

import lotto.domain.*;
import lotto.service.dto.LottoStatisticsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoStatisticsCalculatorTest {

    @Test
    @DisplayName("로또 결과를 올바르게 계산하는지 확인한다")
    void calculateLottoStatisticsTest() {
        // given
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        LottoTickets lottoTickets = new LottoTickets(List.of(lottoTicket));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottoTickets, winningLotto);

        // then
        LottoStatisticsDto lottoStatisticsDto = lottoStatisticsCalculator.calculateLottoStatistics(new PurchaseAmount(1000));
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
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        LottoTickets lottoTickets = new LottoTickets(List.of(lottoTicket));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottoTickets, winningLotto);

        // then
        LottoStatisticsDto lottoStatisticsDto = lottoStatisticsCalculator.calculateLottoStatistics(new PurchaseAmount(1000));
        assertEquals(lottoStatisticsDto.getEarningRate(), 200000000.0);
    }
}
