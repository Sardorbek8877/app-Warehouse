package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.User;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.payload.UserDto;
import uz.com.appwarehause.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //ADD USER
    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    //GET USERS
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    //GET USER BY ID
    @DeleteMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    //DELETE USER
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

    //EDIT USER
    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.editUser(id, userDto);
    }
}
