package lotto.domain;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateDuplicateBonusNumber();
    }

    public int countMatchingNumbers(LottoTicket lottoTicket) {
        return lottoTicket.countMatching(winningNumbers);
    }

    public boolean containsBonusNumber(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusNumber);
    }

    private void validateDuplicateBonusNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
