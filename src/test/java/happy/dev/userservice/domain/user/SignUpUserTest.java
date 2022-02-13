package happy.dev.userservice.domain.user;

import happy.dev.userservice.exception.ExceptionsToEmailProcessing;
import happy.dev.userservice.exception.ExceptionsToPasswordProcessing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignUpUserTest {

    @Test
    @DisplayName("비밀번호가 특수 문자가 아니면 가입 되지 않게 하기")
    void 비밀번호가_특수문자가_아니면_가입_되지_않게_하기() {
        assertThrows(ExceptionsToPasswordProcessing.class, ()->{
            SignUpUser signUpUser = new SignUpUser("aaaa@aaa.cc", "bbb");
        });
    }

    @Test
    @DisplayName("비밀번호가 특수 문자면 가입 되게 하기")
    void 비밀번호가_특수문자면_가입_되게_하기() {
        assertDoesNotThrow(()->{
            SignUpUser signUpUser = new SignUpUser("aaaa@aaa.cc", "bbb!");
        });
    }

    @Test
    @DisplayName("이메일 형식이 아니면 가입되지 않게 하기")
    void 이메일_형식이_아니면_예외_출력() {
        assertThrows(ExceptionsToEmailProcessing.class, ()->{
            SignUpUser signUpUser = new SignUpUser("aaa", "bbb!");
        });
    }

    @Test
    @DisplayName("이메일 형식이 맞으면 가입되게 하기")
    void 이메일_형식이_맞으면_가입되게_하기() {
        assertDoesNotThrow(()->{
            SignUpUser signUpUser = new SignUpUser("aaa@aaa.a", "bbb!");
        });
    }

    @Test
    @DisplayName("만약 이메일 형식이 아니고 비밀번호에 특수문자가 들어가지 않는다면 특별한 예외를 처리해야 한다.")
    void 둘_다_틀린다면() {
        assertThrows(InputMismatchException.class, ()->{
            SignUpUser signUpUser = new SignUpUser("aaaa", "bbb");
        });
    }
}