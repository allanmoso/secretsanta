package secretsanta.config;

import com.github.mongobee.Mongobee;
import com.github.mongobee.exception.MongobeeException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by allan.moso on 11/14/2016.
 */
@Configuration
public class MongoConfig {

    @Bean
    public Mongobee mongobee() throws MongobeeException {
        Mongobee runner = new Mongobee("mongodb://localhost:27017");
        runner.setDbName("secretsanta");  // host must be set if not set in URI
        runner.setChangeLogsScanPackage(
                "secretsanta.mongobee.changesets"); // package to scan for changesets
        runner.setEnabled(true);         // optional: default is true
        runner.execute();
        return runner;
    }
}
