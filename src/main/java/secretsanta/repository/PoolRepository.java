package secretsanta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import secretsanta.model.Pool;

public interface PoolRepository extends MongoRepository<Pool, String> {
}
