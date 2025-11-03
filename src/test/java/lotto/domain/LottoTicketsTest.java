package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketsTest {

    @Test
    @DisplayName("올바르게 MatchResult를 계산하는지 확인한다")
    void matchAllWithTest() {
        // given
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)));
        LottoTickets lottoTickets = new LottoTickets(List.of(lottoTicket));

        // when
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 7)), new LottoNumber(18));
        List<MatchResult> matchResults = lottoTickets.matchAllWith(winningLotto);

        // then
        for (MatchResult matchResult : matchResults) {
            assertEquals(matchResult.getMatchCount(), 5);
            assertFalse(matchResult.isBonusMatch());
        }
    }
}
