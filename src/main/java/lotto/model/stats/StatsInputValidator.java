package lotto.model.stats;

import base.model.Validator;

public class StatsInputValidator implements Validator<IStatsInput> {
    @Override
    public void validate(IStatsInput statsInputDto) throws IllegalArgumentException {
        var input = statsInputDto.purchasedLotto();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("statsInputDto.purchasedLotto().isEmpty()");
        }
    }
}
