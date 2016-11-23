package secretsanta.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import secretsanta.dto.UserEditDto;
import secretsanta.dto.UserViewDto;
import secretsanta.model.User;
import secretsanta.services.UserService;

import javax.mail.MessagingException;
import java.util.List;

/**
 * {@link User} resource.
 * <p>
 * Created by allan.moso on 11/14/2016.
 */
@RestController
@RequestMapping("/api/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/pool/{poolId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserViewDto> getUsersInPool(@PathVariable("poolId") final String poolId) {
        return userService.getUsersAsViewDtos(poolId);
    }

    @RequestMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEditDto getUser(@PathVariable("userId") final String userId) {
        return new UserEditDto(userService.getUser(userId));
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEditDto saveUser(@RequestBody UserEditDto dto) throws MessagingException {
        User user = userService.saveUser(dto);
        return new UserEditDto(user);
    }
}
