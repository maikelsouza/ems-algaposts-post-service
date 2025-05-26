package com.algaworks.algapost.post.service.api.model;

import com.algaworks.algapost.post.service.domain.model.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostInput {

    private String title;

    private String author;

    private String body;

    public static Post convertToModel(PostInput postInput){
        return Post.builder()
                .author(postInput.getAuthor())
                .title(postInput.getTitle())
                .body(postInput.getBody())
                .build();
    }
}
