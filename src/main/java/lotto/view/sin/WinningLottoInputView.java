package lotto.view.sin;


import base.view.View;
import lotto.model.lotto.winning.IWinningLottoInput;

public class WinningLottoInputView extends View<IWinningLottoInput> {
    @Override
    public void header() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    @Override
    public void render(IWinningLottoInput iLottoInput) {

    }
}
