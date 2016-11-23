package secretsanta.model;

import org.springframework.data.annotation.Id;
import secretsanta.dto.UserEditDto;

import java.util.List;

public class Pool {
    @Id
    private String id;

    private String name;

    private List<UserEditDto> users;


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

    public List<UserEditDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserEditDto> users) {
        this.users = users;
    }
}
