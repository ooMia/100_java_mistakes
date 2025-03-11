package base.view;

import base.model.Schema;

public abstract class View<T extends Schema> {

    public void header() {
        System.out.println();
    }

    public void render(T t) {
        System.out.println(t);
    }
}
