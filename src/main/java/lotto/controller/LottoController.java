package lotto.controller;

import lotto.domain.*;
import lotto.domain.LottoMachine;
import lotto.service.LottoStatisticsCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        PurchaseAmount purchaseAmount = readPurchaseMoneyUntilValid();
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount);
        LottoTickets lottoTickets = lottoMachine.getLottoTickets();
        outputView.printLottoTickets(purchaseAmount.calculateTicketCount(), lottoTickets.getLottoTickets());

        WinningLotto winningLotto = getWinningLotto();

        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottoTickets, winningLotto);
        outputView.printLottoStatistics(lottoStatisticsCalculator.calculateLottoStatistics(purchaseAmount));
    }

    private WinningLotto getWinningLotto() {
        Lotto lotto = readWinningNumbersUntilValid();
        return readWinningLottoUntilValid(lotto);
    }

    private PurchaseAmount readPurchaseMoneyUntilValid() {
        try {
            return new PurchaseAmount(inputView.readPurchaseMoney());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return readPurchaseMoneyUntilValid();
        }
    }

    private Lotto readWinningNumbersUntilValid() {
        try {
            return new Lotto(inputView.readWinningNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return readWinningNumbersUntilValid();
        }
    }

    private WinningLotto readWinningLottoUntilValid(Lotto lotto) {
        try {
            return new WinningLotto(lotto, new LottoNumber(inputView.readBonusNumbers()));
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return readWinningLottoUntilValid(lotto);
        }
    }

}
