package lotto.domain.core;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusLottoNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusLottoNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusLottoNumber = bonusLottoNumber;
        validateDuplicateBonusNumber();
    }

    public int matchCount(List<LottoNumber> lottoTicket) {
        int count = 0;

        for (LottoNumber lottoNumber : lottoTicket) {
            if (winningNumbers.contains(lottoNumber)) {
                count++;
            }
        }

        return count;
    }

    public boolean hasBonus(List<LottoNumber> lottoTicket) {
        return lottoTicket.contains(bonusLottoNumber);
    }

    private void validateDuplicateBonusNumber() {
        if (winningNumbers.contains(bonusLottoNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
