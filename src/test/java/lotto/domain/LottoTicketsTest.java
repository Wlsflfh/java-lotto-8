package lotto.domain;

import lotto.domain.generator.LottoMachine;
import lotto.domain.generator.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketsTest {

    class FixedLottoGenerator implements RandomNumberGenerator {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @Test
    @DisplayName("올바르게 MatchResult를 계산하는지 확인한다")
    void matchAllWithTest() {
        // given
        LottoMachine lottoMachine = new LottoMachine(new PurchaseAmount(1000), new FixedLottoGenerator());
        LottoTickets lottoTickets = lottoMachine.getLottoTickets();

        // when
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 7)), new LottoNumber(8));
        List<MatchResult> matchResults = lottoTickets.matchAllWith(winningLotto);

        // then
        for (MatchResult matchResult : matchResults) {
            assertEquals(matchResult.getMatchCount(), 5);
            assertFalse(matchResult.isBonusMatch());
        }
    }
}
