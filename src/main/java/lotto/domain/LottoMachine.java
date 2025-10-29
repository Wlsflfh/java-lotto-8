package lotto.domain;

import lotto.domain.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final List<List<LottoNumber>> lottoTickets;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoMachine(int ticketCount, RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.lottoTickets = generateLottoTickets(ticketCount);
    }

    public List<MatchResult> calculateMatchCount(WinningLotto winningLotto) {
        List<MatchResult> lottoMatchCount = new ArrayList<>();

        for (List<LottoNumber> lottoTicket : lottoTickets) {
            int count = winningLotto.matchCount(lottoTicket);
            boolean isBonus = winningLotto.hasBonus(lottoTicket);
            lottoMatchCount.add(new MatchResult(count, isBonus));
        }

        return lottoMatchCount;
    }

    private List<List<LottoNumber>> generateLottoTickets(int ticketCount) {
        List<List<LottoNumber>> lottoTickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(sortLottoNumbers(randomNumberGenerator.generate()));
        }

        return lottoTickets;
    }

    private List<LottoNumber> sortLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    public List<List<LottoNumber>> getLottoTickets() {
        return lottoTickets;
    }
}
