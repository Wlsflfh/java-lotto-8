package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoGenerator implements RandomNumberGenerator{

    private static final int LOTTO_SIZE = 6;
    private static final int FIRST_LOTTO_NUMBER_RANGE = 1;
    private static final int LAST_LOTTO_NUMBER_RANGE = 45;

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(FIRST_LOTTO_NUMBER_RANGE, LAST_LOTTO_NUMBER_RANGE, LOTTO_SIZE);
    }
}
