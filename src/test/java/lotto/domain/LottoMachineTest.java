package lotto.domain;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LottoMachineTest {

    class FixedLottoGenerator implements RandomNumberGenerator {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @Test
    @DisplayName("올바르게 MatchResult를 계산하는지 확인한다")
    void calculateMatchCountTest() {
        // given
        int ticketCount = 1;
        LottoMachine lottoMachine = new LottoMachine(ticketCount, new FixedLottoGenerator());

        // when
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 7)), new LottoNumber(8));
        List<MatchResult> matchResults = lottoMachine.calculateMatchCount(winningLotto);

        // then
        for (MatchResult matchResult : matchResults) {
            assertEquals(matchResult.getMatchCount(), 5);
            assertFalse(matchResult.isBonusMatch());
        }
    }

    @Test
    @DisplayName("오름차순으로 정렬된 로또 번호를 올바르게 생성하는지 확인한다")
    void generateSortLottoNumbersTest() {
        // given
        int ticketCount = 3;
        LottoMachine lottoMachine = new LottoMachine(ticketCount, new LottoGenerator());

        // when
        List<List<LottoNumber>> lottoTickets = lottoMachine.getLottoTickets();

        // then
        for (List<LottoNumber> lottoTicket : lottoTickets) {
            List<Integer> numbers = lottoTicket.stream()
                    .map(ln -> Integer.parseInt(ln.toString()))
                    .collect(Collectors.toList());

            List<Integer> sorted = numbers.stream()
                    .sorted()
                    .collect(Collectors.toList());

            assertEquals(sorted, numbers);
        }
    }
}
