package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.processor;

import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.AuthenticationProvider;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.UsernamePasswordProvider;

public class UsernamePasswordAuthenticationProcessor extends AuthenticationProcessor {

    public UsernamePasswordAuthenticationProcessor(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public boolean isAuthorized(AuthenticationProvider authProvider) {
        if (authProvider instanceof UsernamePasswordProvider) {
            return Boolean.TRUE;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(authProvider);
        } else {
            return Boolean.FALSE;
        }
    }

}