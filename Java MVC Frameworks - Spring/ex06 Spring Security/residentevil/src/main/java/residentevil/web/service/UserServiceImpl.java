package residentevil.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import residentevil.web.domain.entities.Role;
import residentevil.web.domain.entities.User;
import residentevil.web.domain.models.service.UserServiceModel;
import residentevil.web.repository.RoleRepository;
import residentevil.web.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 3/23/2019.
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;

        this.seedRoles();
    }


    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        this.assignRolesToUser(user);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        try{
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(u->this.modelMapper.map(u,UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
    }

    private void assignRolesToUser(User user) {
        if(user.getAuthorities()==null) {
            user.setAuthorities(new HashSet<>());
        }
        if (this.userRepository.count()==0) {
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_MODERATOR"));
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_ADMIN"));
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_USER"));
        } else {
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_USER"));
        }
    }

    private void seedRoles(){
        if (this.roleRepository.count()==0) {
            Role roleAdmin = new Role();
            roleAdmin.setAuthority("ROLE_ADMIN");

            Role roleModerator = new Role();
            roleModerator.setAuthority("ROLE_MODERATOR");

            Role roleUser = new Role();
            roleUser.setAuthority("ROLE_USER");

            try {
                this.roleRepository.saveAndFlush(roleAdmin);
                this.roleRepository.saveAndFlush(roleModerator);
                this.roleRepository.saveAndFlush(roleUser);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
