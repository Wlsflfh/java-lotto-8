package lotto.domain.generator;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final LottoTickets lottoTickets;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoMachine(Money money, RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.lottoTickets = generateLottoTickets(money);
    }

    private LottoTickets generateLottoTickets(Money money) {
        List<LottoTicket> tickets = new ArrayList<>();

        for (int i = 0; i < money.calculateTicketCount(); i++) {
            List<LottoNumber> numbers = createSortedLottoTicket(randomNumberGenerator.generate());
            tickets.add(new LottoTicket(numbers));
        }

        return new LottoTickets(tickets);
    }

    private List<LottoNumber> createSortedLottoTicket(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }
}
