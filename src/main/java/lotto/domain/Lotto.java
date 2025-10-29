package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicate(numbers);
        this.lottoNumbers = toNumberObjects(numbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private List<LottoNumber> toNumberObjects(List<Integer> numbers) {
        List<LottoNumber> winningLottoNumbers = new ArrayList<>();

        for (Integer checkNumber : numbers) {
            winningLottoNumbers.add(new LottoNumber(checkNumber));
        }

        return winningLottoNumbers;
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 중복될 수 없습니다.");
        }
    }
}
