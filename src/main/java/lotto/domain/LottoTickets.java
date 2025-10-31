package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = List.copyOf(lottoTickets);
    }

    public List<MatchResult> matchAllWith(WinningLotto winningLotto) {
        List<MatchResult> lottoMatchCount = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            int count = winningLotto.countMatchingNumbers(lottoTicket);
            boolean isBonus = winningLotto.containsBonusNumber(lottoTicket);
            lottoMatchCount.add(new MatchResult(count, isBonus));
        }

        return lottoMatchCount;
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
