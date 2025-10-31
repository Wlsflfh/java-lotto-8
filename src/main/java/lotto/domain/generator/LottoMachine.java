package lotto.domain.generator;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final LottoTickets lottoTickets;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoMachine(PurchaseAmount purchaseAmount, RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.lottoTickets = generateLottoTickets(purchaseAmount);
    }

    private LottoTickets generateLottoTickets(PurchaseAmount purchaseAmount) {
        List<LottoTicket> tickets = new ArrayList<>();

        for (int i = 0; i < purchaseAmount.calculateTicketCount(); i++) {
            List<LottoNumber> lottoTicket = createSortedLottoTicket(randomNumberGenerator.generate());
            tickets.add(new LottoTicket(lottoTicket));
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
