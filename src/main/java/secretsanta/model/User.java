package secretsanta.model;

import org.springframework.data.annotation.Id;

/**
 * Created by allan.moso on 11/14/2016.
 */
public class User {

    @Id
    private String id;

    private String email;

    private String poolId;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
