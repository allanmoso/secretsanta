package secretsanta.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import secretsanta.model.User;
import secretsanta.repository.UserRepository;

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

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/pool/{poolId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsersInPool(@PathVariable("poolId") String poolId) {
        return userRepository.findAllByPoolId(poolId);
    }

    @RequestMapping(value = "/pool/{poolId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> addUserToPool(@PathVariable("poolId") String poolId, @RequestBody User user) {
        user.setPoolId(poolId);
        userRepository.insert(user);
        return userRepository.findAllByPoolId(poolId);
    }
}
