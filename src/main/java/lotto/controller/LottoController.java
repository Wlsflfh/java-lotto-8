package lotto.controller;

import lotto.domain.*;
import lotto.domain.generator.LottoMachine;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.service.LottoStatisticsCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoController(InputView inputView, OutputView outputView, RandomNumberGenerator randomNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void play() {
        PurchaseAmount purchaseAmount = readPurchaseMoneyUntilValid();
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, randomNumberGenerator);
        LottoTickets lottoTickets = lottoMachine.getLottoTickets();
        outputView.printLottoTickets(purchaseAmount.calculateTicketCount(), lottoTickets.getLottoTickets());

        Lotto lotto = readWinningNumbersUntilValid();
        WinningLotto winningLotto = readWinningLottoUntilValid(lotto);

        LottoStatisticsCalculator lottoStatisticsCalculator = new LottoStatisticsCalculator(lottoTickets, winningLotto);
        outputView.printLottoResult(lottoStatisticsCalculator.calculateLottoStatistics(purchaseAmount));
    }

    public PurchaseAmount readPurchaseMoneyUntilValid() {
        try {
            return new PurchaseAmount(inputView.readPurchaseMoney());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return readPurchaseMoneyUntilValid();
        }
    }

    public Lotto readWinningNumbersUntilValid() {
        try {
            return new Lotto(inputView.readWinningNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return readWinningNumbersUntilValid();
        }
    }

    public WinningLotto readWinningLottoUntilValid(Lotto lotto) {
        try {
            return new WinningLotto(lotto, new LottoNumber(inputView.readBonusNumbers()));
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return readWinningLottoUntilValid(lotto);
        }
    }

}
