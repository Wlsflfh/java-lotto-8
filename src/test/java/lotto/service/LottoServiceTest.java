package lotto.service;

import lotto.domain.*;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.service.dto.LottoResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoServiceTest {

    class FixedLottoGenerator implements RandomNumberGenerator {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @Test
    @DisplayName("로또 결과를 올바르게 계산하는지 확인한다")
    void calculateLottoResultTest() {
        // given
        Money money = new Money(1000);
        LottoMachine lottoMachine = new LottoMachine(money.calculateTicketCount(), new FixedLottoGenerator());
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoService lottoService = new LottoService(lottoMachine, winningLotto);

        // then
        LottoResultDto lottoResultDto = lottoService.calculateLottoResult(money);
        Map<Prize, Integer> lottoResult = lottoResultDto.getLottoResult();

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
        Money money = new Money(1000);
        LottoMachine lottoMachine = new LottoMachine(money.calculateTicketCount(), new FixedLottoGenerator());
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoService lottoService = new LottoService(lottoMachine, winningLotto);

        // then
        LottoResultDto lottoResultDto = lottoService.calculateLottoResult(money);
        assertEquals(lottoResultDto.getEarningRate(), 200000000.0);
    }
}
