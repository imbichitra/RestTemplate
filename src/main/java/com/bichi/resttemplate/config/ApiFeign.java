package com.bichi.resttemplate.config;

import com.bichi.resttemplate.model.Comments;
import com.bichi.resttemplate.model.User;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user",url = "https://jsonplaceholder.typicode.com/")
public interface ApiFeign {

    @GetMapping("/posts")
    List<User> getAllUser();

    @GetMapping("/posts/{id}")
    User getUserById(@PathVariable("id")int id);

    @GetMapping("/comments")
    List<Comments> getCommentsById(@RequestParam("postId")int id);

    @PostMapping("/posts")
    User save(@RequestBody User user);

    @PutMapping("/posts/{id}")
    User updateUser(@RequestBody User user,@PathVariable int id);

    @DeleteMapping("/posts/{id}")
    User deleteUser(@RequestBody User user,@PathVariable int id);
}
