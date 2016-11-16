package secretsanta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import secretsanta.model.Pool;

/**
 * Created by allan.moso on 11/14/2016.
 */
public interface PoolRepository extends MongoRepository<Pool, String> {
}
