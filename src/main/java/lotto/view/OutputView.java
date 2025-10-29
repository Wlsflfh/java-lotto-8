package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.Prize;
import lotto.service.dto.LottoResultDto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.Prize.*;

public class OutputView {

    public static void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printLottoTickets(int ticketCount, List<List<LottoNumber>> lottoTickets) {
        System.out.printf("\n%d개를 구매했습니다.\n", ticketCount);

        for (List<LottoNumber> lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public void printLottoResult(LottoResultDto lottoResultDto) {
        Map<Prize, Integer> sortedResult = new EnumMap<>(Prize.class);
        sortedResult.putAll(lottoResultDto.getLottoResult());

        System.out.println("\n당첨 통계\n---");
        for (Map.Entry<Prize, Integer> prizeIntegerEntry : sortedResult.entrySet()) {
            if (fromPrize(prizeIntegerEntry.getKey()).equals("NONE")) continue;

            System.out.printf("%s - %d개\n", fromPrize(prizeIntegerEntry.getKey()), prizeIntegerEntry.getValue());
        }

        System.out.printf("총 수익률은 %.1f%%입니다.", lottoResultDto.getEarningRate());
    }

    private String fromPrize(Prize prize) {
        if (prize == PLACE_OF_5ST) return "3개 일치 (5,000원)";
        if (prize == PLACE_OF_4ST) return "4개 일치 (50,000원)";
        if (prize == PLACE_OF_3ST) return "5개 일치 (1,500,000원)";
        if (prize == PLACE_OF_2ST) return "5개 일치, 보너스 볼 일치 (30,000,000원)";
        if (prize == PLACE_OF_1ST) return "6개 일치 (2,000,000,000원)";
        return "NONE";
    }
}
