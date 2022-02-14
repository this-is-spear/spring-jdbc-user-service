package happy.dev.userservice.domain.message;

public class JoinUserMessage{

    private boolean success;
    private String response;

    public JoinUserMessage(boolean success, String response) {
        this.success = success;
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "JoinUserMessage{" +
                "success=" + success +
                ", response='" + response + '\'' +
                '}';
    }
}
