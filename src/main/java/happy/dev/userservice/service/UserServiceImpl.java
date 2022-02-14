package happy.dev.userservice.service;

import happy.dev.userservice.domain.message.FindUserMessage;
import happy.dev.userservice.domain.message.JoinUserMessage;
import happy.dev.userservice.domain.user.SearchedUser;
import happy.dev.userservice.domain.user.SignUpUser;
import happy.dev.userservice.exception.NoUserDataException;
import happy.dev.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public JoinUserMessage join(SignUpUser signUpUser) {
        try {
            log.debug("duplicateEmail : {}", duplicateEmail(signUpUser));
            if (duplicateEmail(signUpUser)) {
                throw new DuplicateKeyException("이메일 중복 입니다.");
            }
            userRepository.save(signUpUser);
            return new JoinUserMessage(true, "가입완료");
        } catch (DuplicateKeyException e) {
            log.debug("join fail [{}][{}]", this.getClass(), e.getMessage());
            return new JoinUserMessage(false, "이메일 중복");
        } catch (Exception e){
            log.debug("join fail [{}][{}]", this.getClass(), e.getMessage());
            return new JoinUserMessage(false, "가입실패");
        }
    }

    private boolean duplicateEmail(SignUpUser signUpUser) {
        log.debug("check duplicate {}", signUpUser.getPrincipal());
        return userRepository.findByEmail(signUpUser.getPrincipal());
    }

    @Override
    public FindUserMessage findOne(Long seq) {
        try {
            SearchedUser user = userRepository.findById(seq);
            FindUserMessage message = new FindUserMessage(true, "조회완료");
            message.getUserList().add(user);
            return message;
        } catch (NoUserDataException e) {
            log.error("{}", e.getMessage());
            return new FindUserMessage(false, "조회실패");
        }
    }

    @Override
    public FindUserMessage findAll() {
        try {
            List<SearchedUser> userList = userRepository.findAll();
            return new FindUserMessage(true, "조회완료", userList);
        } catch (NoUserDataException e) {
            log.error("{}", e.getMessage());
            return new FindUserMessage(false, "조회실패");
        }
    }
}
