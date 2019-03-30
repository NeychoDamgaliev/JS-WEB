package residentevil.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import residentevil.web.domain.models.service.UserServiceModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/23/2019.
 */
public interface UserService extends UserDetailsService {

    boolean registerUser(UserServiceModel userServiceModel);
    List<UserServiceModel> findAllUsers();

}
