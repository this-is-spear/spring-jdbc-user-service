package happy.dev.userservice.web.user.controller;

import happy.dev.userservice.domain.message.FindUserMessage;
import happy.dev.userservice.domain.message.JoinUserMessage;
import happy.dev.userservice.domain.user.SignUpUser;
import happy.dev.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService userService;

    @Override
    @RequestMapping
    public FindUserMessage getUserList(Model model) {
        FindUserMessage message = userService.findAll();
        return message;
    }

    @Override
    @RequestMapping("/{userId}")
    public FindUserMessage getUser(@PathVariable("userId") Long seq) {
        FindUserMessage message = userService.findOne(seq);
        return message;
    }

    @Override
    @RequestMapping("/join")
    public JoinUserMessage join(@RequestBody SignUpUser user) {
        JoinUserMessage message = userService.join(user);
        return message;
    }
}
