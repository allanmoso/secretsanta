package secretsanta.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import secretsanta.dto.UserEditDto;
import secretsanta.dto.UserViewDto;
import secretsanta.model.User;
import secretsanta.repository.UserRepository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    private final HttpServletRequest request;

    @Autowired
    private Environment environment;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UserService(UserRepository userRepository, JavaMailSender javaMailSender, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
        this.request = request;
    }

    public List<User> getUsers(String poolId) {
        return this.userRepository.findAllByPoolId(poolId);
    }

    public List<UserViewDto> getUsersAsViewDtos(String poolId) {
        return transformToViewDtos(getUsers(poolId));
    }

    public User getUser(String userId) {
        final User user = this.userRepository.findOne(userId);
        return user;
    }

    public User saveUser(UserEditDto dto) {
        User user;
        if (StringUtils.isEmpty(dto.getId())) {
            user = new User();
        } else {
            user = userRepository.findOne(dto.getId());
            if (user == null) throw new IllegalStateException("Invalid ID.");
        }

        user.setId(dto.getId());
        user.setPoolId(dto.getPoolId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return saveUser(user);
    }

    public User saveUser(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            userRepository.insert(user);
        } else {
            userRepository.save(user);
        }
        return user;
    }

    private List<UserViewDto> transformToViewDtos(List<User> users) {
        final List<UserViewDto> userViewDtos = new ArrayList<UserViewDto>();
        for (User user : users) {
            userViewDtos.add(new UserViewDto(user));
        }
        return userViewDtos;
    }

    public void inviteUser(final User user) throws MessagingException {
        log.error(environment.getProperty("spring.mail.username"));
        log.error(environment.getProperty("spring.mail.password"));

        final MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setFrom("moso.sender@gmail.com");
        msg.setRecipients(Message.RecipientType.TO, user.getEmail());
        msg.setSubject("Join Secret Santa");
        final String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()));
        msg.setText("Go to " + baseUrl + "/user/" + user.getId());
        javaMailSender.send(msg);
    }

}
