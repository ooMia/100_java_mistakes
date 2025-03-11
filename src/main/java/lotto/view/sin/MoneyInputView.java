package lotto.view.sin;


import base.view.View;
import lotto.model.money.IMoneyInput;

public class MoneyInputView extends View<IMoneyInput> {
    @Override
    public void header() {
        super.header();
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void render(IMoneyInput iMoneyInput) {

    }
}
