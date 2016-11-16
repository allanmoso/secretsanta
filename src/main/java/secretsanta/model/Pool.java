package secretsanta.model;

import org.springframework.data.annotation.Id;

/**
 * Created by allan.moso on 11/14/2016.
 */
public class Pool {
    @Id
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
