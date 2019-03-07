package exodia.service;

import exodia.domain.models.service.UserServiceModel;
import org.springframework.stereotype.Service;

/**
 * Created by Neycho Damgaliev on 3/5/2019.
 */

public interface UserService {

    boolean registerUser(UserServiceModel model);

    UserServiceModel loginUser(UserServiceModel model);

}
