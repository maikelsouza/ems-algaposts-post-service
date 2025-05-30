package com.algaworks.algapost.post.service.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostProcessingRequestMessage {

    private UUID postId;

    private String postBody;

}
