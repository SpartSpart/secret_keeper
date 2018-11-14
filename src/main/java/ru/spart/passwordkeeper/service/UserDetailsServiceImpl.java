package ru.spart.passwordkeeper.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spart.passwordkeeper.controller.model.User;
import ru.spart.passwordkeeper.repository.UserDataRepository;
import ru.spart.passwordkeeper.repository.model.UserData;
import ru.spart.passwordkeeper.service.model.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDataRepository userDataRepository;


    public UserDetailsServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;

    }

    @Transactional
    public void add(UserDetailsImpl userDetails) {

        UserData newUser = new UserData();
        newUser.setUserLogin(userDetails.getUsername());
        newUser.setUserPassword(userDetails.getPassword());
        newUser.setUserEmail(userDetails.getUseremail());
        userDataRepository.saveAndFlush(newUser);
    }


    public void update(long id, User user) {
        Optional<UserData> newUser = userDataRepository.findById(id);

        newUser.get().setUserPassword(user.getPassword());
        newUser.get().setUserLogin(user.getLogin());

        userDataRepository.saveAndFlush(newUser.get());
    }

//    public User getUser(String login) {
//        Optional<UserData> userData = ;
//        User user = new User();
//
//        user.setId(userData.get().getId());
//        user.setLogin(userData.get().getUserLogin());
//        user.setPassword(userData.get().getUserPassword());
//
//        return user;
//    }


    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        List<UserData> allUsersData = userDataRepository.findAll();

        for (UserData user : allUsersData) {
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setLogin(user.getUserLogin());
            newUser.setPassword(user.getUserPassword());
            allUsers.add(newUser);
        }

        return allUsers;
    }

    public void deleteSecret(long id) {
        userDataRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserData userData = userDataRepository.findByUserLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserDetails userDetails = new UserDetailsImpl(login, userData.getUserPassword(), userData.getUserEmail());
        return userDetails;
    }

    public UserDetails getCurrent() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isUser(String login){
        if(!userDataRepository.findByUserLogin(login).equals(null))
            return true;
        return false;
    }


}