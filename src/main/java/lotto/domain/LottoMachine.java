package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final List<List<Integer>> lottoTickets;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoMachine(int ticketCount, RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.lottoTickets = generateLottoNumbers(ticketCount);
    }

    private List<List<Integer>> generateLottoNumbers(int ticketCount) {
        List<List<Integer>> lottoTickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(sortLottoNumbers(randomNumberGenerator.generate()));
        }

        return lottoTickets;
    }

    private List<Integer> sortLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public List<List<Integer>> getLottoTickets() {
        return lottoTickets;
    }
}
