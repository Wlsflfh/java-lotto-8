package lotto.service;

import lotto.domain.Money;
import lotto.domain.LottoMachine;
import lotto.domain.MatchResult;
import lotto.domain.Prize;
import lotto.domain.WinningLotto;
import lotto.service.dto.LottoResultDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    private static final int PERCENT = 100;
    private static final int INIT_COUNT = 0;

    private final LottoMachine lottoMachine;
    private final WinningLotto winningLotto;

    public LottoService(LottoMachine lottoMachine, WinningLotto winningLotto) {
        this.lottoMachine = lottoMachine;
        this.winningLotto = winningLotto;
    }

    public LottoResultDto calculateLottoResult(Money money) {
        Map<Prize, Integer> lottoResult = initLottoResult();
        double totalPrizeMoney = 0;

        List<MatchResult> lottoMatchCount = lottoMachine.calculateMatchCount(winningLotto);

        for (MatchResult matchResult : lottoMatchCount) {
            Prize prize = Prize.from(matchResult);
            lottoResult.merge(prize, 1, Integer::sum);
            totalPrizeMoney += prize.getPrizeMoney();
        }

        return new LottoResultDto(lottoResult, calculateEarningRate(totalPrizeMoney, money));
    }

    private Map<Prize, Integer> initLottoResult() {
        Map<Prize, Integer> lottoResult = new HashMap<>();

        for (Prize prize : Prize.values()) {
            lottoResult.put(prize, INIT_COUNT);
        }

        return lottoResult;
    }

    private double calculateEarningRate(double totalPrizeMoney, Money money) {
        return totalPrizeMoney / money.getMoney() * PERCENT;
    }
}
