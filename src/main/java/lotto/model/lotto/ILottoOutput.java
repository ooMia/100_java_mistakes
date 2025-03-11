package lotto.model.lotto;

import base.model.Schema;
import java.util.SortedSet;

public interface ILottoOutput extends Schema {
    SortedSet<Integer> numbers();
}
