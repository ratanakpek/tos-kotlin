package com.example.delegate.byinterface;

public class View implements ShowAble {

    private CustomView v;

    public View(CustomView view) {
        v = view;
    }

    @Override
    public void show() {
        v.show();
    }
}
