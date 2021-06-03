package com.bichi.resttemplate.controller;

import com.bichi.resttemplate.model.Comments;
import com.bichi.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("exchange")
public class RestTemplateUsingExchange {
    private String url = "https://jsonplaceholder.typicode.com/posts";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public List<User> getAllUser(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity=new HttpEntity<String>(httpHeaders);
        ResponseEntity<User[]> responseEntity=restTemplate.exchange(url, HttpMethod.GET,httpEntity,User[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @GetMapping("/users/{id}")
    public User getAllUser(@PathVariable("id")int id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity=new HttpEntity<String>(httpHeaders);

        ResponseEntity<User> responseEntity=restTemplate.exchange(url+"/{id}",HttpMethod.GET,httpEntity,User.class,id);
        System.out.println(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @GetMapping("/comments/{id}")
    public List<Comments> getComments(@PathVariable("id")int id){
        String url = "https://jsonplaceholder.typicode.com/comments?postId={id}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity=new HttpEntity<String>(httpHeaders);
        ResponseEntity<Comments[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET,httpEntity,Comments[].class,id);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @PostMapping("/user")
    public User save(@RequestBody User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);

        //ResponseEntity<User> responseEntity=restTemplate.postForEntity(url,user,User.class);
        ResponseEntity<User> responseEntity=restTemplate.exchange(url,HttpMethod.POST,httpEntity,User.class);
        return responseEntity.getBody();
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url+"/{id}",HttpMethod.PUT,httpEntity,User.class,user.getUserId());
        System.out.println(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestBody User user){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);
        ResponseEntity<User> responseEntity =restTemplate.exchange(url+"/{id}",HttpMethod.DELETE,httpEntity,User.class,user.getUserId());
        System.out.println(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }
}
