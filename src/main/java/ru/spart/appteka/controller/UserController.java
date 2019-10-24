package ru.spart.appteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spart.appteka.controller.model.User;
import ru.spart.appteka.service.UserRegisterService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRegisterService userRegisterService;

    @Autowired
    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
       userRegisterService.register(user);
            return ResponseEntity.ok().build();

    }

}
