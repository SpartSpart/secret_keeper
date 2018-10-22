package ru.spart.passwordkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.controller.model.User;
import ru.spart.passwordkeeper.repository.UserDataRepository;
import ru.spart.passwordkeeper.repository.model.UserData;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDataRepository userDataRepository;

    public void add(User user) {
        UserData newUser = new UserData();

        newUser.setUser_login(user.getLogin());
        newUser.setUser_password(user.getPassword());

        userDataRepository.saveAndFlush(newUser);
    }

    public void update(long id, User user) {
        Optional<UserData> newUser = userDataRepository.findById(id);

        newUser.get().setUser_password(user.getPassword());
        newUser.get().setUser_login(user.getLogin());

        userDataRepository.saveAndFlush(newUser.get());
    }

    public User getUser(long id) {
        Optional<UserData> userData = userDataRepository.findById(id);
        User user = new User();

        user.setId(userData.get().getId());
        user.setLogin(userData.get().getUser_login());
        user.setPassword(userData.get().getUser_password());

        return user;
    }


    public List<User> getAllUsers() {
        List<User> allUsers = null;
        List<UserData> allUsersData = userDataRepository.findAll();

        for (UserData user : allUsersData) {
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setLogin(user.getUser_login());
            newUser.setPassword(user.getUser_password());
            allUsers.add(newUser);
        }

        return allUsers;
    }

    public void deleteSecret(long id) {
        userDataRepository.deleteById(id);
    }
}
