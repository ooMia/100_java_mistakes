package lotto.controller;

import base.model.Validator;
import base.view.ConsoleInput;
import base.view.View;
import java.util.NoSuchElementException;
import java.util.Scanner;

import lotto.model.bonus.BonusInputDto;
import lotto.model.bonus.BonusInputValidator;
import lotto.model.bonus.IBonusInput;
import lotto.model.lotto.winning.IWinningLottoOutput;
import lotto.view.sin.BonusInputView;

public class WinningBonusConsoleInput extends ConsoleInput<IBonusInput> {

    private final IWinningLottoOutput winningLotto;

    private WinningBonusConsoleInput(
            View<IBonusInput> view,
            Validator<IBonusInput> validator,
            IWinningLottoOutput winningLotto,
            Scanner scanner
    ) throws IllegalArgumentException {
        super(view, validator, scanner);
        this.winningLotto = winningLotto;
    }

    public static ConsoleInput<IBonusInput> of(IWinningLottoOutput winningLotto, Scanner scanner) {
        return new WinningBonusConsoleInput(
                new BonusInputView(),
                new BonusInputValidator(),
                winningLotto,
                scanner);
    }

    @Override
    protected IBonusInput parseDto(String input) throws IllegalArgumentException {
        try {
            return BonusInputDto.of(winningLotto.numbers(), Integer.parseInt(input));
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new IllegalArgumentException("The input is not a integer.");
        }
    }
}
