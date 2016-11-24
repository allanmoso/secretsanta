package secretsanta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import secretsanta.dto.UserEditDto;
import secretsanta.model.Pool;
import secretsanta.model.User;
import secretsanta.repository.PoolRepository;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PoolService {
    private final PoolRepository poolRepository;

    private final UserService userService;

    @Autowired
    public PoolService(PoolRepository poolRepository, UserService userService) {
        this.poolRepository = poolRepository;
        this.userService = userService;
    }

    public Pool save(Pool pool) {
        if (StringUtils.isEmpty(pool.getId())) {
            return poolRepository.insert(pool);
        } else {
            return poolRepository.save(pool);
        }
    }

    public Pool getPool(String poolId) {
        Pool pool = poolRepository.findOne(poolId);
        if (pool == null) {
            pool = new Pool();
            save(pool);
        }

        final List<UserEditDto> users = new ArrayList<UserEditDto>();

        for (User user : userService.getUsers(poolId)) {
            final UserEditDto dto = new UserEditDto(user);
            users.add(dto);
        }

        pool.setUsers(users);
        return pool;
    }

    public void draw(String poolId) throws MessagingException {
        List<User> users = userService.getUsers(poolId);

        Collections.shuffle(users);

        for (int i = 0; i < users.size(); i++) {
            final User user = users.get(i);
            final User recipient = users.get(i == users.size() - 1 ? 0 : i + 1);
            user.setRecipient(recipient);
            userService.saveUser(user);
            userService.sendRecipient(user);
        }
    }
}
