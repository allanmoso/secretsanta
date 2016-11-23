package secretsanta.dto;

import secretsanta.model.User;

public class UserEditDto {
    private String id;
    private String name;
    private String email;
    private String poolId;
    private UserViewDto recipient;

    @SuppressWarnings("unused")
    public UserEditDto() {
    }

    public UserEditDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.poolId = user.getPoolId();
        if (user.getRecipient() != null) {
            this.recipient = new UserViewDto(user.getRecipient());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserViewDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserViewDto recipient) {
        this.recipient = recipient;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }
}
