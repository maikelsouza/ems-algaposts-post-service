package com.algaworks.algapost.post.service.domain.service;

import com.algaworks.algapost.post.service.api.model.PostInput;
import com.algaworks.algapost.post.service.api.model.PostOutput;
import com.algaworks.algapost.post.service.api.model.PostProcessingRequestMessage;
import com.algaworks.algapost.post.service.api.model.PostSummaryOutput;
import com.algaworks.algapost.post.service.domain.model.Post;
import com.algaworks.algapost.post.service.domain.repository.PostRepository;
import com.algaworks.algapost.post.service.infrastructure.rabbitmq.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository repository;

    private final RabbitTemplate template;


    @Transactional
    public PostOutput create(PostInput postInput){
        Post post = PostInput.convertToModel(postInput);
        post.setSummary(getThreeFirstLines(post.getBody()));
        post = repository.save(post);
        return PostOutput.convertToOutput(post);
    }

    public void calculatePostValeu(PostOutput postOutput){

        if (postOutput.getId() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostProcessingRequestMessage postProcessingRequestMessage = PostProcessingRequestMessage
                .builder()
                .postId(postOutput.getId())
                .postBody(postOutput.getBody())
                .build();

        template.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_POST_PROCESSING, "", postProcessingRequestMessage);
    }

    public PostOutput findById(String uuid){
       return repository.findById(UUID.fromString(uuid))
                .map(PostOutput::convertToOutput)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

    }

    public Page<PostSummaryOutput> findAllPaged(Pageable pageable){
        return repository.findAll(pageable)
                .map(PostSummaryOutput::convertToSummaryOutput);
    }

    private String getThreeFirstLines(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        String[] lines = text.split("\\r?\\n");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < Math.min(3, lines.length); i++) {
            result.append(lines[i]);
            if (i < Math.min(3, lines.length) - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
