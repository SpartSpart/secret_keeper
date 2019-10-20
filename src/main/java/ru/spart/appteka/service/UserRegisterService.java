package ru.spart.appteka.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spart.appteka.controller.model.User;
import ru.spart.appteka.service.model.UserDetailsImpl;

@Service
public class UserRegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public UserRegisterService(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    public void register(User user){
        UserDetailsImpl userDetails =new UserDetailsImpl(user.getLogin(),passwordEncoder.encode(user.getPassword()),user.getEmail());
        userDetailsServiceImpl.add(userDetails);

    }

}
