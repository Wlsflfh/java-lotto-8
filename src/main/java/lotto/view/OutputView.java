package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.Prize;
import lotto.service.dto.LottoResultDto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String TICKET_COUNT_HEADER_MESSAGE = "\n%d개를 구매했습니다.\n";
    private static final String LOTTO_RESULT_HEADER_MESSAGE = "\n당첨 통계\n---";
    private static final String LOTTO_RESULT_FORMAT = "%s - %d개\n";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public static void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printLottoTickets(int ticketCount, List<List<LottoNumber>> lottoTickets) {
        System.out.printf(TICKET_COUNT_HEADER_MESSAGE, ticketCount);

        for (List<LottoNumber> lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public void printLottoResult(LottoResultDto lottoResultDto) {
        Map<Prize, Integer> sortedResult = new EnumMap<>(Prize.class);
        sortedResult.putAll(lottoResultDto.getLottoResult());

        System.out.println(LOTTO_RESULT_HEADER_MESSAGE);
        for (Map.Entry<Prize, Integer> prizeIntegerEntry : sortedResult.entrySet()) {
            if (PrizeView.from(prizeIntegerEntry.getKey()).equals(PrizeView.NONE)) continue;

            System.out.printf(LOTTO_RESULT_FORMAT, PrizeView.from(prizeIntegerEntry.getKey()).getMessage(), prizeIntegerEntry.getValue());
        }

        System.out.printf(EARNING_RATE_MESSAGE, lottoResultDto.getEarningRate());
    }
}
