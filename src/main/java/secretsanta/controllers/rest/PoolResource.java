package secretsanta.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import secretsanta.dto.UserViewDto;
import secretsanta.model.Pool;
import secretsanta.model.User;
import secretsanta.services.PoolService;
import secretsanta.services.UserService;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/pool")
public class PoolResource {
    private final PoolService poolService;
    private final UserService userService;

    @Autowired
    public PoolResource(PoolService poolService, UserService userService) {
        this.poolService = poolService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{poolId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Pool getPool(@PathVariable("poolId") final String poolId) {
        return poolService.getPool(poolId);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Pool save(@RequestBody Pool pool) {
        return poolService.save(pool);
    }

    @RequestMapping(value = "/{poolId}/draw")
    public void draw(@PathVariable("poolId") final String poolId) {
        poolService.draw(poolId);
    }

    @RequestMapping(
            value = "/{poolId}/user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserViewDto addUser(@PathVariable("poolId") final String poolId,
                               @RequestBody User user) throws MessagingException {
        user.setPoolId(poolId);
        userService.saveUser(user);
        userService.inviteUser(user);
        return new UserViewDto(user);
    }
}
