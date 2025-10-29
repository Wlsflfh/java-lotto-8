package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public int readPurchaseMoney() {
        System.out.println("\n구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            OutputView.showErrorMessage("[ERROR] 잘못된 입력입니다.");
            return readPurchaseMoney();
        }
    }

    public List<Integer> readWinningNumbers() {
        try {
            System.out.println("\n당첨 번호를 입력해 주세요.");
            return Arrays.stream(userInput().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage("[ERROR] 잘못된 입력입니다.");
            return readWinningNumbers();
        }
    }

    public int readBonusNumbers() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
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
