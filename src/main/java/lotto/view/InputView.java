package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String COMMA_DELIMITER = ",";
    private static final String INPUT_MONEY_MESSAGE = "\n구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "\n당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "\n보너스 번호를 입력해 주세요.";

    public int readPurchaseMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        try {
            return Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            OutputView.showErrorMessage("[ERROR] 잘못된 입력입니다.");
            return readPurchaseMoney();
        }
    }

    public List<Integer> readWinningNumbers() {
        try {
            System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
            return Arrays.stream(userInput().split(COMMA_DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage("[ERROR] 잘못된 입력입니다.");
            return readWinningNumbers();
        }
    }

    public int readBonusNumbers() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        try {
            return Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            OutputView.showErrorMessage("[ERROR] 잘못된 입력입니다.");
            return readBonusNumbers();
        }
    }

    private String userInput() {
        return Console.readLine();
    }
}
