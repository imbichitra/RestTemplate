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

import java.util.*;

@RestController
@RequestMapping("entity")
public class RestTemplateUsingEntity {

    private String url = "https://jsonplaceholder.typicode.com/posts";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public List<User> getAllUser(){
         ResponseEntity<User[]> responseEntity=restTemplate.getForEntity(url,User[].class);
         System.out.println(responseEntity.getStatusCode());
         return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @GetMapping("/users/{id}")
    public User getAllUser(@PathVariable("id")int id){
        //if have multiple value then
        /*Map<String,Integer> params = new HashMap<>();
        params.put("id",id);
        ResponseEntity<User> responseEntity=restTemplate.getForEntity(url+"/{id}",User.class,params);*/
        ResponseEntity<User> responseEntity=restTemplate.getForEntity(url+"/{id}",User.class,id);
        System.out.println(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @GetMapping("/comments/{id}")
    public List<Comments> getComments(@PathVariable("id")int id){
        String url = "https://jsonplaceholder.typicode.com/comments?postId={id}";
        ResponseEntity<Comments[]> responseEntity = restTemplate.getForEntity(url,Comments[].class,id);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @PostMapping("/user")
    public User save(@RequestBody User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);

        //ResponseEntity<User> responseEntity=restTemplate.postForEntity(url,user,User.class);
        ResponseEntity<User> responseEntity=restTemplate.postForEntity(url,httpEntity,User.class);
        return responseEntity.getBody();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        restTemplate.put(url+"/{id}",user,user.getUserId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody User user){
        restTemplate.delete(url+"/{id}",user.getUserId());
    }
}
