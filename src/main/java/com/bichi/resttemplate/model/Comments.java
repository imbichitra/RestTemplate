package com.bichi.resttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
