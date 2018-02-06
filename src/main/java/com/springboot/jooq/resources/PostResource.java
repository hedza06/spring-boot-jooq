package com.springboot.jooq.resources;

import com.springboot.jooq.model.PostModel;
import com.springboot.jooq.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostResource {

    private PostService postService;

    /**
     * Constructor dependency injector
     * @param postService - service layer dependency
     */
    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostModel>> getAllPosts()
    {
        List<PostModel> postList = postService.findAll();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}
