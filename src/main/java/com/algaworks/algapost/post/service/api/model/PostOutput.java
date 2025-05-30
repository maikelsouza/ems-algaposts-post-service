package com.algaworks.algapost.post.service.api.model;

import com.algaworks.algapost.post.service.domain.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostOutput {

    private UUID id;

    private String title;

    private String body;

    private String author;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int wordCount;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double calculatedValue;


    public static PostOutput convertToOutput(Post post){
        return PostOutput.builder()
                .id(post.getId())
                .body(post.getBody())
                .author(post.getAuthor())
                .title(post.getTitle())
                .wordCount(post.getWordCount())
                .calculatedValue(post.getCalculatedValue())
                .build();
    }

}
