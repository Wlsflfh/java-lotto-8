package lotto.service;

import lotto.domain.*;
import lotto.service.dto.LottoStatisticsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatisticsCalculator {

    private static final int INIT_COUNT = 0;

    private final LottoTickets lottoTickets;
    private final WinningLotto winningLotto;

    public LottoStatisticsCalculator(LottoTickets lottoTickets, WinningLotto winningLotto) {
        this.lottoTickets = lottoTickets;
        this.winningLotto = winningLotto;
    }

    public LottoStatisticsDto calculateLottoStatistics(Money money) {
        Map<Prize, Integer> lottoStatistics = createEmptyLottoStatistics();
        PrizeStatistics prizeStatistics = new PrizeStatistics();

        List<MatchResult> lottoMatchCount = lottoTickets.matchAllWith(winningLotto);

        for (MatchResult matchResult : lottoMatchCount) {
            Prize prize = Prize.from(matchResult);
            lottoStatistics.merge(prize, 1, Integer::sum);
            prizeStatistics.accumulate(prize);
        }

        return new LottoStatisticsDto(lottoStatistics, prizeStatistics.getEarningRate(money));
    }

    private Map<Prize, Integer> createEmptyLottoStatistics() {
        Map<Prize, Integer> lottoResult = new HashMap<>();

        for (Prize prize : Prize.values()) {
            lottoResult.put(prize, INIT_COUNT);
        }

        return lottoResult;
    }
}
