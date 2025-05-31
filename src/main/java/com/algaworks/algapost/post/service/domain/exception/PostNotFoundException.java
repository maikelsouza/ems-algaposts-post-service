package com.algaworks.algapost.post.service.domain.exception;

public class PostNotFoundException extends BusinessException{

    public PostNotFoundException() {
        super("Post not found");
    }
}
