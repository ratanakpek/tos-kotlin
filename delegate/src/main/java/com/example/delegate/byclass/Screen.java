package com.example.delegate.byclass;


public class Screen {
    private View view;

    public Screen(View v) {
        view = v;
    }

    public void show() {
        view.show();
    }
}
