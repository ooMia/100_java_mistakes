package lotto.view.sin;


import base.view.View;
import lotto.model.bonus.IBonusInput;

public class BonusInputView extends View<IBonusInput> {

    @Override
    public void header() {
        super.header();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    @Override
    public void render(IBonusInput iBonusInput) {

    }
}
