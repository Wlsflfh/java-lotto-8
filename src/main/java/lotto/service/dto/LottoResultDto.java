package lotto.service.dto;

import lotto.domain.Prize;

import java.util.Map;

public class LottoResultDto {

    private final Map<Prize, Integer> lottoResult;
    private final double earningRate;

    public LottoResultDto(Map<Prize, Integer> lottoResult, double earningRate) {
        this.lottoResult = lottoResult;
        this.earningRate = earningRate;
    }

    public Map<Prize, Integer> getLottoResult() {
        return lottoResult;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
