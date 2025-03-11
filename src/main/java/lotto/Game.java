package lotto;

import lotto.controller.*;
import lotto.model.bonus.BonusOutputDto;
import lotto.model.bonus.IBonusOutput;
import lotto.model.lotto.winning.IWinningLottoOutput;
import lotto.model.money.IMoneyInput;

import java.io.ByteArrayInputStream;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Scanner scanner;
    private final String[] args;
    private final Random random;
    public Game(String[] args, Random random) {
        this.args = args;
        this.random = random;
    }

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public void runGame(){

        if (args.length >= 3) {
            ByteArrayInputStream actual = new ByteArrayInputStream(
                    String.join(System.lineSeparator(), args).getBytes());
            System.setIn(actual);
        }

        // 구입 금액 입력
        IMoneyInput money;
        try (var console = MoneyConsoleInput.of(getScanner())) {
            money = console.getInput();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 로또 구매
        var purchasedLotto = MoneyToPurchaseLotto.of(random).tryConvert(money);

        // 당첨 로또 번호 입력
        IWinningLottoOutput winningLotto;
        try (var console = WinningLottoConsoleInput.of(getScanner())) {
            winningLotto = IWinningLottoOutput.of(console.getInput());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 보너스 번호 입력
        IBonusOutput bonusNumber;
        try (var console = WinningBonusConsoleInput.of(winningLotto, getScanner())) {
            bonusNumber = BonusOutputDto.of(console.getInput());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 당첨 통계 출력
        PurchaseLottoToPrize.of(winningLotto, bonusNumber).tryConvert(purchasedLotto);
    }
}
