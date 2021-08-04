package pl.kuba.api.dto.response.user;

public class UserView {
   private String id;
   private String username;
   private String role;

    public UserView(String id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserView() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
