package secretsanta.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import secretsanta.UserViewDto;
import secretsanta.model.User;
import secretsanta.repository.UserRepository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link User} resource.
 * <p>
 * Created by allan.moso on 11/14/2016.
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    @Autowired
    private HttpServletRequest request;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UserResource(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping(value = "/pool/{poolId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserViewDto> getUsersInPool(@PathVariable("poolId") String poolId) {
        return transformToViewDtos(userRepository.findAllByPoolId(poolId));
    }

    @RequestMapping(value = "/pool/{poolId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserViewDto> addUserToPool(@PathVariable("poolId") String poolId, @RequestBody User user) throws MessagingException {
        user.setPoolId(poolId);
        userRepository.insert(user);
        inviteUser(user);
        return transformToViewDtos(userRepository.findAllByPoolId(poolId));
    }

    private List<UserViewDto> transformToViewDtos(List<User> users) {
        final List<UserViewDto> userViewDtos = new ArrayList<UserViewDto>();
        for (User user : users) {
            userViewDtos.add(new UserViewDto(user));
        }
        return userViewDtos;
    }

    private void inviteUser(final User user) throws MessagingException {
        final MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setFrom("moso.sender@gmail.com");
        msg.setRecipients(Message.RecipientType.TO, user.getEmail());
        msg.setSubject("Join Secret Santa");
        final String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/api/user"));
        msg.setText("Go to " + baseUrl + "/user/" + user.getId());
        javaMailSender.send(msg);
    }
}
