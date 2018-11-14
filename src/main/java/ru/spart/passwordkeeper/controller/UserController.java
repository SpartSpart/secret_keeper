package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spart.passwordkeeper.controller.model.User;
import ru.spart.passwordkeeper.service.UserRegisterService;

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
//        else
//            return ResponseEntity.badRequest().build();

    }


//    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody User user){
//        userRegisterService.update(id,user);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(value = "/get/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") long id){
//        return ResponseEntity
//                .ok()
//                .body(userRegisterService.getUser(id));
//    }

//    @GetMapping(value = "/getAll")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity
//                .ok()
//                .body(userRegisterService.getAllUsers());
//    }

//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
//        userRegisterService.deleteSecret(id);
//        return ResponseEntity.ok().build();
//    }

}
