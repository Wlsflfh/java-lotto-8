package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = List.copyOf(tickets);
    }

    public List<MatchResult> matchAllWith(WinningLotto winningLotto) {
        List<MatchResult> lottoMatchCount = new ArrayList<>();

        for (LottoTicket lottoTicket : tickets) {
            int count = winningLotto.countMatchingNumbers(lottoTicket);
            boolean isBonus = winningLotto.containsBonusNumber(lottoTicket);
            lottoMatchCount.add(new MatchResult(count, isBonus));
        }

        return lottoMatchCount;
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }
}
