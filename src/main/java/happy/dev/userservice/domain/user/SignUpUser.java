package happy.dev.userservice.domain.user;

import happy.dev.userservice.exception.ExceptionsToEmailProcessing;
import happy.dev.userservice.exception.ExceptionsToPasswordProcessing;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class SignUpUser {
    private String email;
    private String pwd;

    public SignUpUser(String email, String password) {
        String email_pattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String pwd_pattern = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$";

        if (!Pattern.matches(email_pattern, email) && Pattern.matches(pwd_pattern, password)) {
            throw new InputMismatchException("아이디는 이메일 형식이어야하고, 패스워드에는 특수문자가 존재해야 합니다.");
        }

        if (!Pattern.matches(email_pattern, email)) {
            throw new ExceptionsToEmailProcessing("이메일 형식이 아닙니다.");
        }
        this.email = email;
        if (Pattern.matches(pwd_pattern, password)){
            throw new ExceptionsToPasswordProcessing("패스워드에 특수문자가 들어가야 합니다.");
        }
        this.pwd = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }
}
