package happy.dev.userservice.controller;

import happy.dev.userservice.domain.user.SearchedUser;
import happy.dev.userservice.domain.user.SignUpUser;

import java.util.List;

public interface UserController {
    List<SearchedUser> getUserList();
    SearchedUser getUser(Long seq);
    SearchedUser join(SignUpUser user);
}
