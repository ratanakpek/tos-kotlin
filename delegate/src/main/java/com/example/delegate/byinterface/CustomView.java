package com.example.delegate.byinterface;

public class CustomView implements ShowAble {
    @Override
    public void show() {
        System.out.println("CustomView.show()");
    }
}
