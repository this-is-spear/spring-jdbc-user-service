package happy.dev.userservice.web.user.controller;


import happy.dev.userservice.domain.message.FindUserMessage;
import happy.dev.userservice.domain.message.JoinUserMessage;
import happy.dev.userservice.domain.user.SignUpUser;
import org.springframework.ui.Model;

public interface UserController {
    FindUserMessage getUserList(Model model);
    FindUserMessage getUser(Long seq);
    JoinUserMessage join(SignUpUser user);
}
