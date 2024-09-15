package com.example.thirdpartyapis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Collaborator {
    private Long id;
    private String username;
    private String imageUrl;
    private String gitHubUrl;
}
