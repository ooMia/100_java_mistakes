package lotto.view.sout;

import base.view.View;
import lotto.model.lotto.ILottoPurchaseOutput;

public class LottoPurchaseOutputView extends View<ILottoPurchaseOutput> {
    @Override
    public void render(ILottoPurchaseOutput iLottoPurchaseOutput) {
        System.out.println();
        var purchased = iLottoPurchaseOutput.purchased();
        System.out.println(purchased.size() + "개를 구매했습니다.");
        purchased.forEach(System.out::println);
        System.out.println();
    }

}
