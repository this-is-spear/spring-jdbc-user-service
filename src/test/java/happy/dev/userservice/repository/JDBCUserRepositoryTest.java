package happy.dev.userservice.repository;

import happy.dev.userservice.domain.user.SearchedUser;
import happy.dev.userservice.domain.user.SignUpUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class JDBCUserRepositoryTest {

    @Autowired
    private JDBCUserRepository jdbcUserRepository;
    private SignUpUser user;
    private Long seq;

    String INSERT_MESSAGE_SQL = "insert into users(email, passwd) values (?, ?)" ;
    @BeforeEach
    void setUp() {
        user = new SignUpUser("aaaa@aaa.cc", "123!");
        seq = jdbcUserRepository.save(user);
    }

    @Test
    @DisplayName("유저 저장하는 테스트")
    void saveUser() {
        System.out.println("seq = " + seq);
        SearchedUser searchedUser = jdbcUserRepository.findById(seq);
        System.out.println("searchedUser = " + searchedUser);
    }

    @Test
    @DisplayName("유저 조회 테스트")
    public void findOneUser() {
        SearchedUser searchedUser = jdbcUserRepository.findById(seq);
        Assertions.assertThat(user.getEmail()).isEqualTo(searchedUser.getEmail());
    }

    @Test
    @DisplayName("유저 전체 조회하는 테스트")
    void findAll() {
        List<SearchedUser> users = jdbcUserRepository.findAll();
        for (SearchedUser user : users) {
            System.out.println("user = " + user.toString());
        }
    }
}