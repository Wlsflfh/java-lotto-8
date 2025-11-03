package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final LottoTickets lottoTickets;

    public LottoMachine(PurchaseAmount purchaseAmount) {
        this.lottoTickets = generateLottoTickets(purchaseAmount);
    }

    private LottoTickets generateLottoTickets(PurchaseAmount purchaseAmount) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<LottoTicket> tickets = new ArrayList<>();

        for (int i = 0; i < purchaseAmount.calculateTicketCount(); i++) {
            List<LottoNumber> lottoTicket = createSortedLottoTicket(lottoGenerator.generate());
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
