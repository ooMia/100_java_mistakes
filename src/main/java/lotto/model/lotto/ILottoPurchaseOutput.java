package lotto.model.lotto;


import base.model.Schema;
import java.util.List;

public interface ILottoPurchaseOutput extends Schema {
    List<ILottoOutput> purchased();
}
