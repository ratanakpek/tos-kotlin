package com.example.designpatterninkotlinjava.behavior;

import static org.junit.Assert.assertTrue;

import android.util.Log;

import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.processor.AuthenticationProcessor;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.processor.OAuthAuthenticationProcessor;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.OAuthTokenProvider;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.SamlAuthenticationProvider;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.processor.UsernamePasswordAuthenticationProcessor;
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.provider.UsernamePasswordProvider;
import com.example.designpatterninkotlinjava.behavioral.example2.ConsoleBasedLogger;
import com.example.designpatterninkotlinjava.behavioral.example2.DebugBasedLogger;
import com.example.designpatterninkotlinjava.behavioral.example2.ErrorBaseLogger;
import com.example.designpatterninkotlinjava.behavioral.example2.Logger;

import org.junit.Test;

public class ChainOfResponsibilityTest {

    private static AuthenticationProcessor getChainOfAuthProcessor() {

        AuthenticationProcessor oAuthProcessor = new OAuthAuthenticationProcessor(null);
        AuthenticationProcessor unamePasswordProcessor = new UsernamePasswordAuthenticationProcessor(oAuthProcessor);
        return unamePasswordProcessor;
    }

    @Test
    public void givenOAuthProvider_whenCheckingAuthorized_thenSuccess() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
        boolean isAuthorized = authProcessorChain.isAuthorized(new OAuthTokenProvider());
        assertTrue(isAuthorized);
    }

    @Test
    public void givenUsernamePasswordProvider_whenCheckingAuthorized_thenSuccess() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
        boolean isAuthorized = authProcessorChain.isAuthorized(new UsernamePasswordProvider());
        assertTrue(isAuthorized);
    }

    @Test
    public void givenSamlAuthProvider_whenCheckingAuthorized_thenFailure() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
        boolean isAuthorized = authProcessorChain.isAuthorized(new SamlAuthenticationProvider());
        assertTrue(!isAuthorized);
    }


    private Logger doChainning() {

        Logger consoleLogger = new ConsoleBasedLogger(Logger.OUTPUTINFO);

        Logger errorLogger = new ErrorBaseLogger(Logger.ERRORINFO);
        consoleLogger.setNextLevelLogger(errorLogger);

        Logger debugLogger = new DebugBasedLogger(Logger.DEBUGINFO);
        errorLogger.setNextLevelLogger(debugLogger);

        return consoleLogger;
    }


    @Test
    public void chainning() {
        Logger chainLogger = doChainning();

        //chainLogger.logMessage(Logger.OUTPUTINFO, "OUTPUT INFO 1");
        //chainLogger.logMessage(Logger.ERRORINFO, "ERROR INFO 2");
        chainLogger.logMessage(Logger.DEBUGINFO, "DEBUG INFO 3");
    }
}
