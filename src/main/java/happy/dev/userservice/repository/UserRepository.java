package happy.dev.userservice.repository;

import happy.dev.userservice.domain.user.SearchedUser;
import happy.dev.userservice.domain.user.SignUpUser;

import java.util.List;

public interface UserRepository {
    Long save(SignUpUser user);

    SearchedUser findById(Long id);

    List<SearchedUser> findAll();

    boolean findByEmail(String email);
}
