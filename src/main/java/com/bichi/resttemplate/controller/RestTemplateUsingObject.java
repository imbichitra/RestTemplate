package com.bichi.resttemplate.controller;

import com.bichi.resttemplate.model.Comments;
import com.bichi.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("object")
public class RestTemplateUsingObject {
    private String url = "https://jsonplaceholder.typicode.com/posts";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public List<User> getAllUser(){
        User[] users= restTemplate.getForObject(url,User[].class);
        return Arrays.asList(Objects.requireNonNull(users));
    }

    @GetMapping("/users/{id}")
    public User getAllUser(@PathVariable("id")int id){
        User user=restTemplate.getForObject(url+"/{id}",User.class,id);
        return user;
    }

    @GetMapping("/comments/{id}")
    public List<Comments> getComments(@PathVariable("id")int id){
        String url = "https://jsonplaceholder.typicode.com/comments?postId={id}";
        Comments[] comments = restTemplate.getForObject(url,Comments[].class,id);
        return Arrays.asList(Objects.requireNonNull(comments));
    }

    @PostMapping("/user")
    public User save(@RequestBody User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);

        //ResponseEntity<User> responseEntity=restTemplate.postForEntity(url,user,User.class);
        User user1=restTemplate.postForObject(url,httpEntity,User.class);
        return user1;
    }
}
