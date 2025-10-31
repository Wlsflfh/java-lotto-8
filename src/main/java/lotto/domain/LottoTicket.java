package lotto.domain;

import java.util.List;

public class LottoTicket {

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public int countMatching(Lotto winningNumbers) {
        int count = 0;

        for (LottoNumber lottoNumber : numbers) {
            if (winningNumbers.contains(lottoNumber)) {
                count++;
            }
        }

        return count;
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
