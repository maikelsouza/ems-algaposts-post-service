package com.algaworks.algapost.post.service.api.model;

import com.algaworks.algapost.post.service.domain.model.Post;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostOutput {

    private UUID id;

    private String title;

    private String summary;

    private String body;

    private String author;


    public static PostOutput convertToOutput(Post post){
        return PostOutput.builder()
                .id(post.getId())
                .body(post.getBody())
                .author(post.getAuthor())
                .summary(post.getSummary())
                .title(post.getTitle())
                .build();
    }

}
