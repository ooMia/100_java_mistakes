package base.controller;

import base.model.Schema;
import base.view.View;

public abstract class Converter<IN extends Schema, OUT extends Schema> {
    private final View<OUT> view;

    public Converter(
            View<OUT> view
    ) {
        this.view = view;
    }

    public final OUT tryConvert(IN input) {
        OUT res = convert(input);
        if (view != null) {
            view.render(res);
        }
        return res;
    }

    protected abstract OUT convert(IN in);
}
