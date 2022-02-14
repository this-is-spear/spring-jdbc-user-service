package happy.dev.userservice.domain.user;


import java.time.LocalDateTime;

public class SearchedUser {
    private String email;
    private LocalDateTime create_at;

    public SearchedUser(String email, LocalDateTime create_at) {
        this.email = email;
        this.create_at = create_at;
    }

    public String getEmail() {
        return email;
    }
}
