package lotto.controller;

import base.model.Validator;
import base.view.ConsoleInput;
import base.view.View;
import java.util.List;
import java.util.Scanner;

import lotto.model.lotto.winning.IWinningLottoInput;
import lotto.model.lotto.winning.WinningLottoInputDto;
import lotto.model.lotto.winning.WinningLottoInputValidator;
import lotto.view.sin.WinningLottoInputView;

public class WinningLottoConsoleInput extends ConsoleInput<IWinningLottoInput> {
    private WinningLottoConsoleInput(
            View<IWinningLottoInput> view,
            Validator<IWinningLottoInput> validator,
            Scanner scanner
    ) {
        super(view, validator, scanner);
    }

    public static ConsoleInput<IWinningLottoInput> of(Scanner scanner) {
        return new WinningLottoConsoleInput(
                new WinningLottoInputView(),
                new WinningLottoInputValidator(),
                scanner
        );
    }

    @Override
    protected IWinningLottoInput parseDto(String input) throws IllegalArgumentException {
        try {
            List<Integer> numbers = parseInput(input);
            return new WinningLottoInputDto(numbers);
        } catch (Exception e) {
            throw new IllegalArgumentException("The input is not a comma separated list of integers.");
        }
    }

    private List<Integer> parseInput(String input) throws NumberFormatException {
        var split = List.of(input.split(","));
        return split.stream().map(Integer::parseInt).toList();
    }

}
