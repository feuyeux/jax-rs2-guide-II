package com.example.jaas;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;

/*
 * set JAVA_OPTS=
 * -Djava.security.auth.login.config=D:\+aries\github\jax-rs2-guide\sample\6\security-rest\src\main\resources\restJaas.conf
 */
public class RestLoginModule implements LoginModule {
    private static final Logger LOG = Logger.getLogger(RestLoginModule.class);

    private Subject subject;
    private CallbackHandler callbackHandler;

    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    private String userName = null;
    private char[] passWord = null;

    private RestUserPrincipal userPrincipal;
    private RestRolePrincipal testRolePrincipal;

    private RestLoginDao dao = new RestLoginDao();

    @Override
    public void initialize(final Subject subject, final CallbackHandler callbackHandler, final Map<String, ?> sharedState, final Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("call back handler is null");
        }

        final Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password: ", false);

        try {
            callbackHandler.handle(callbacks);
            userName = ((NameCallback) callbacks[0]).getName();
            passWord = ((PasswordCallback) callbacks[1]).getPassword();
            if (userName == null || passWord == null) {
                throw new LoginException("Callback handler does not return login data properly");
            }
            RestLoginModule.LOG.info("username=" + userName);
            //logger.info("password" + password);
            if (dao.isValidUser(userName, passWord)) {
                succeeded = true;
                return succeeded;
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        RestLoginModule.LOG.info("committing...");
        if (succeeded == false) {
            return false;
        } else {
            userPrincipal = new RestUserPrincipal(userName);
            if (!subject.getPrincipals().contains(userPrincipal)) {
                subject.getPrincipals().add(userPrincipal);
            }

            /*  testPasswordPrincipal = new TestPasswordPrincipal(new String(
                        password));
                if (!subject.getPrincipals().contains(testPasswordPrincipal)) {
                    subject.getPrincipals().add(testPasswordPrincipal);
                    
                }
            */
            final List<String> roles = dao.getRoles(userName, userPrincipal);
            for (final String role : roles) {
                testRolePrincipal = new RestRolePrincipal(role);
                if (!subject.getPrincipals().contains(testRolePrincipal)) {
                    subject.getPrincipals().add(testRolePrincipal);
                }
            }
            commitSucceeded = true;
            RestLoginModule.LOG.info("principals=" + subject.getPrincipals());
            for (final Principal p : subject.getPrincipals()) {
                if (p instanceof RestRolePrincipal) {
                    RestLoginModule.LOG.info(" ROLE: " + p.getName());
                }
            }
            return commitSucceeded;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceeded == false) {
            succeeded = false;
            userName = null;
            if (passWord != null) {
                passWord = null;
            }
            userPrincipal = null;
        } else {
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        userName = null;
        if (passWord != null) {
            for (int i = 0; i < passWord.length; i++) {
                passWord[i] = ' ';
                passWord = null;
            }
        }
        userPrincipal = null;
        return true;
    }
}
