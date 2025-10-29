package lotto.controller;

import lotto.domain.Money;
import lotto.domain.LottoMachine;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
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
        Money money = readPurchaseMoneyUntilValid();
        LottoMachine lottoMachine = new LottoMachine(money.calculateTicketCount(), new LottoGenerator());
        outputView.printLottoTickets(money.calculateTicketCount(), lottoMachine.getLottoTickets());

        Lotto lotto = readWinningNumbersUntilValid();
        WinningLotto winningLotto = readWinningLottoUntilValid(lotto);

        LottoService lottoService = new LottoService(lottoMachine, winningLotto);
        outputView.printLottoResult(lottoService.calculateLottoResult(money));
    }

    public Money readPurchaseMoneyUntilValid() {
        try {
            return new Money(inputView.readPurchaseMoney());
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
