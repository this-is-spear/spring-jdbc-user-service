package happy.dev.userservice.service;

import happy.dev.userservice.domain.message.FindUserMessage;
import happy.dev.userservice.domain.message.JoinUserMessage;
import happy.dev.userservice.domain.user.SignUpUser;

public interface UserService {
    JoinUserMessage join(SignUpUser signUpUser);

    FindUserMessage findOne(Long seq);

    FindUserMessage findAll();
}
