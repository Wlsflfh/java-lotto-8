package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int FIRST_LOTTO_NUMBER_RANGE = 1;
    private static final int LAST_LOTTO_NUMBER_RANGE = 45;

    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    public void validateNumberRange(int number) {
        if (number < FIRST_LOTTO_NUMBER_RANGE || number > LAST_LOTTO_NUMBER_RANGE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1에서 45 사이의 숫자여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber lottoNumber1 = (LottoNumber) o;
        return number == lottoNumber1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
