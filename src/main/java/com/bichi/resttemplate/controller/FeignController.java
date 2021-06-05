package com.bichi.resttemplate.controller;

import com.bichi.resttemplate.config.ApiFeign;
import com.bichi.resttemplate.model.Comments;
import com.bichi.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("feign")
public class FeignController {
    @Autowired
    ApiFeign apiFeign;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return apiFeign.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getAllUser(@PathVariable("id")int id){
        return apiFeign.getUserById(id);
    }

    @GetMapping("/comments/{id}")
    public List<Comments> getComments(@PathVariable("id")int id){
        return apiFeign.getCommentsById(id);
    }

    @PostMapping("/user")
    public User save(@RequestBody User user){
        return apiFeign.save(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return apiFeign.updateUser(user,user.getUserId());
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestBody User user){
        return apiFeign.deleteUser(user,user.getUserId());
    }
}
