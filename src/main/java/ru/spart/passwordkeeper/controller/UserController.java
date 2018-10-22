package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spart.passwordkeeper.controller.model.User;
import ru.spart.passwordkeeper.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody User user){
        userService.update(id,user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        return ResponseEntity
                .ok()
                .body(userService.getUser(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity
                .ok()
                .body(userService.getAllUsers());
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        userService.deleteSecret(id);
        return ResponseEntity.ok().build();
    }

}
