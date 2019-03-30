package residentevil.web.domain.models.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/24/2019.
 */
public class UserListViewModel {
    private String username;
    private List<String> authorities;

    public UserListViewModel() {
        this.authorities = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
