package secretsanta;

import secretsanta.model.User;

/**
 * Created by allan.moso on 11/19/2016.
 */
public class UserViewDto {

    private String name;
    private String email;

    public UserViewDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
