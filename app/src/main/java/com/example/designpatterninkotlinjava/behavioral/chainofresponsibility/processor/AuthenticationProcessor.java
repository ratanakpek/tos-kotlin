package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.processor;

import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.AuthenticationProvider;

public abstract class AuthenticationProcessor {
    public AuthenticationProcessor nextProcessor;

    public AuthenticationProcessor(AuthenticationProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract boolean isAuthorized(AuthenticationProvider authProvider);
}
