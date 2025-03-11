package lotto.controller;

import base.controller.Converter;
import base.view.View;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
import lotto.model.lotto.ILottoOutput;
import lotto.model.lotto.ILottoPurchaseOutput;
import lotto.model.lotto.LottoOutputDto;
import lotto.model.lotto.LottoPurchaseOutputDto;
import lotto.model.money.IMoneyInput;
import lotto.view.sout.LottoPurchaseOutputView;

public class MoneyToPurchaseLotto extends Converter<IMoneyInput, ILottoPurchaseOutput> {

    private final Random random;
    private MoneyToPurchaseLotto(View<ILottoPurchaseOutput> view, Random random) {
        super(view);
        this.random = random;
    }

    public static MoneyToPurchaseLotto of(Random random) {
        return new MoneyToPurchaseLotto(
                new LottoPurchaseOutputView(),
                random);
    }

    @Override
    public ILottoPurchaseOutput convert(IMoneyInput iMoneyInput) {
        var tmp = new ArrayList<ILottoOutput>();
        int size = iMoneyInput.money() / 1000;
        while (size-- > 0) {
            tmp.add(generateRandomLotto());
        }
        return LottoPurchaseOutputDto.of(tmp);
    }

    private LottoOutputDto generateRandomLotto() {
        var numbers = new TreeSet<Integer>();
        while (numbers.size() < 6) {
            numbers.add(random.nextInt(45) + 1);
        }
        return LottoOutputDto.of(numbers);
    }
}
