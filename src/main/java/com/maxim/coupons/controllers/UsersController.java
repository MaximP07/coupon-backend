package com.maxim.coupons.controllers;

import com.maxim.coupons.dto.User;
import com.maxim.coupons.dto.UserLoginData;
import com.maxim.coupons.exceptions.ApplicationException;
import com.maxim.coupons.logic.UsersLogic;
import com.maxim.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersLogic usersLogic;

    @Autowired
    public UsersController(UsersLogic usersLogic) {
        this.usersLogic = usersLogic;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestHeader("Authorization") String token) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.usersLogic.getAllUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user) throws ApplicationException {
        usersLogic.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        usersLogic.updateUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@RequestHeader("Authorization") String token, @PathVariable("userId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return usersLogic.getUser(id);
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String token, @PathVariable("userId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        usersLogic.removeUser(id);
    }

    @GetMapping("/companyId")
    public List<User> getByCompanyId(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.usersLogic.getByCompanyId(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginData loginDetails) throws ApplicationException {
        return usersLogic.login(loginDetails);
    }
}
 