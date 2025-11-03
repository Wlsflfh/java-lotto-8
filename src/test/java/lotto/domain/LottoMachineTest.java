package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoMachineTest {

    @Test
    @DisplayName("오름차순으로 정렬된 로또 번호를 올바르게 생성하는지 확인한다")
    void generateSortedLottoTicketTest() {
        // given
        LottoMachine lottoMachine = new LottoMachine(new PurchaseAmount(3000));

        // when
        LottoTickets lottoTickets = lottoMachine.getLottoTickets();

        // then
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            List<Integer> numbers = lottoTicket.getLottoTicket().stream()
                    .map(ln -> Integer.parseInt(ln.toString()))
                    .collect(Collectors.toList());

            List<Integer> sorted = numbers.stream()
                    .sorted()
                    .collect(Collectors.toList());

            assertEquals(sorted, numbers);
        }
    }
}
