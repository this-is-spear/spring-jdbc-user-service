package happy.dev.userservice.domain.user;

import happy.dev.userservice.exception.ExceptionsToEmailProcessing;
import happy.dev.userservice.exception.ExceptionsToPasswordProcessing;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class SignUpUser {
    private String principal;
    private String credentials;

    public SignUpUser(String principal, String credentials) {
        checkValidation(principal, credentials);
        setPrincipal(principal);
        setCredentials(credentials);
    }

    private void checkValidation(String principal, String credentials) {
        if (!Pattern.matches(SignPattern.PRINCIPAL_PATTERN, principal) && Pattern.matches(SignPattern.CREDENTIALS_PATTERN, credentials)) {
            throw new InputMismatchException("아이디는 이메일 형식이어야하고, 패스워드에는 특수문자가 존재해야 합니다.");
        }
    }

    private void setPrincipal(String principal) {
        if (!Pattern.matches(SignPattern.PRINCIPAL_PATTERN, principal)) {
            throw new ExceptionsToEmailProcessing("이메일 형식이 아닙니다.");
        }
        this.principal = principal;
    }

    private void setCredentials(String credentials) {
        if (Pattern.matches(SignPattern.CREDENTIALS_PATTERN, credentials)){
            throw new ExceptionsToPasswordProcessing("패스워드에 특수문자가 들어가야 합니다.");
        }
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCredentials() {
        return credentials;
    }
}
