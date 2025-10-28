package lotto.domain.model;

import java.util.List;

public class Lotto {

    private final List<Number> numbers;

    public Lotto(List<Number> numbers) {
        validateLottoSize(numbers);
        this.numbers = numbers;
    }

    private void validateLottoSize(List<Number> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }


}
