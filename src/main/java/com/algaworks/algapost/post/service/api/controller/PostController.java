package com.algaworks.algapost.post.service.api.controller;

import com.algaworks.algapost.post.service.api.model.PostInput;
import com.algaworks.algapost.post.service.api.model.PostOutput;
import com.algaworks.algapost.post.service.domain.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService service;


    @PostMapping
    public ResponseEntity<PostOutput> create(@Valid @RequestBody PostInput postInput){
        PostOutput postOutput = service.create(postInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(postOutput);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostOutput> findById(@PathVariable String id){
        PostOutput postOutput = service.findById(id);
        return ResponseEntity.ok(postOutput);
    }

    @GetMapping
    public ResponseEntity<Page<PostOutput>> search(Pageable pageable){
            Page<PostOutput> page = service.findAllPaged(pageable);
            return ResponseEntity.ok(page);
    }

}
