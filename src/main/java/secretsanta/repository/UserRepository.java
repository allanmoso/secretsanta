package secretsanta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import secretsanta.model.User;

import java.util.List;

/**
 * Created by allan.moso on 11/14/2016.
 */
public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findAllByPoolId(String poolId);
}
