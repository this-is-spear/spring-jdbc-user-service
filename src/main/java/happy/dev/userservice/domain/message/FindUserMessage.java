package happy.dev.userservice.domain.message;

import happy.dev.userservice.domain.user.SearchedUser;

import java.util.ArrayList;
import java.util.List;

public class FindUserMessage{

    private boolean success;
    private String response;
    private List<SearchedUser> userList = new ArrayList<>();

    public FindUserMessage(boolean success, String response) {
        this.success = success;
        this.response = response;
    }

    public FindUserMessage(boolean success, String response, List<SearchedUser> userList) {
        this.success = success;
        this.response = response;
        this.userList = userList;
    }

    public List<SearchedUser> getUserList() {
        return userList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "FindUserMessage{" +
                "success=" + success +
                ", response='" + response + '\'' +
                ", userList=" + userList +
                '}';
    }
}
