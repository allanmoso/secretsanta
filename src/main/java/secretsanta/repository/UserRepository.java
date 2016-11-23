package secretsanta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import secretsanta.model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findAllByPoolId(String poolId);
}
