package com.algaworks.algapost.post.service.api.model;

import com.algaworks.algapost.post.service.domain.model.Post;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostSummaryOutput {

    private UUID id;

    private String title;

    private String summary;

    private String author;


    public static PostSummaryOutput convertToSummaryOutput(Post post){
        return PostSummaryOutput.builder()
                .id(post.getId())
                .summary(post.getSummary())
                .author(post.getAuthor())
                .title(post.getTitle())
                .build();
    }
}
