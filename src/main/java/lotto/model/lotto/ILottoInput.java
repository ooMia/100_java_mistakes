package lotto.model.lotto;

import base.model.Schema;
import java.util.List;

public interface ILottoInput extends Schema {
    List<Integer> numbers();
}
