package lotto.domain;

import java.util.List;

public class LottoTicket {

    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public int countMatching(Lotto winningNumbers) {
        int count = 0;

        for (LottoNumber lottoNumber : lottoTicket) {
            if (winningNumbers.contains(lottoNumber)) {
                count++;
            }
        }

        return count;
    }

    public boolean contains(LottoNumber bonusNumber) {
        return lottoTicket.contains(bonusNumber);
    }

    public List<LottoNumber> getLottoTicket() {
        return lottoTicket;
    }
}
