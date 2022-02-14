package happy.dev.userservice.domain.user;

import happy.dev.userservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class SearchedUserTest {

    @Autowired
    UserRepository userRepository;


    SignUpUser user;
    Long seq;

    @BeforeEach
    void setUp() {
        user = new SignUpUser("aa@aa.aa", "aa!");
        seq = userRepository.save(user);
    }

    //대상 아이디가 존재하지 않으면 예외처리
    @Test
    @DisplayName("해당 seq에 사용자 정보가 없으면 예외출력")
    void 사용자_조회_예외_출력() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()->{
            SearchedUser searchedUser = userRepository.findById(10000L);
        });
    }

    //대상 아이디가 존재하지 않으면 예외처리
    @Test
    @DisplayName("해당 seq에 사용자 정보가 있으면 OK")
    void 사용자_조회() {
        Assertions.assertDoesNotThrow(()->{
            SearchedUser user = userRepository.findById(this.seq);
        });
    }

    //대상 아이디가 로그인이 되면 last_login_at 현재 시간으로 업데이트

    //대상 아이디가 로그인이 되면 login_count 증가
}