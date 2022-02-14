package happy.dev.userservice.domain.user;

public interface SignPattern {
    String PRINCIPAL_PATTERN= "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    String CREDENTIALS_PATTERN= "^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$";
}
