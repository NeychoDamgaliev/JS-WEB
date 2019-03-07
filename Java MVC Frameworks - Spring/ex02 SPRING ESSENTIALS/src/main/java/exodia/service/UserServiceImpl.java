package exodia.service;

import exodia.domain.entities.User;
import exodia.domain.models.service.UserServiceModel;
import exodia.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.Validator;

/**
 * Created by Neycho Damgaliev on 3/5/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Validator validator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean registerUser(UserServiceModel model) {
        User user = this.modelMapper.map(model,User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

        try {
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return false;
        }
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel model) {
        User user = this.userRepository.findByUsername(model.getUsername()).orElse(null);

        if(user == null || user.getPassword().equals(DigestUtils.sha256Hex(model.getPassword()))) {
            throw new IllegalArgumentException("Wrong username or password!");
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
