package happy.dev.userservice.domain.user;

import java.sql.Date;

public class SearchedUser {
    private String email;
    private String create_at;

    public SearchedUser(String email, String create_at) {
        this.email = email;
        this.create_at = create_at;
    }

    public String getEmail() {
        return email;
    }
}
