package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.processor;

import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.AuthenticationProvider;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.OAuthTokenProvider;

public class OAuthAuthenticationProcessor extends AuthenticationProcessor {

    public OAuthAuthenticationProcessor(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public boolean isAuthorized(AuthenticationProvider authProvider) {
        if (authProvider instanceof OAuthTokenProvider) {
            return Boolean.TRUE;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(authProvider);
        } else {
            return Boolean.FALSE;
        }
    }

}