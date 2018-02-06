package com.springboot.jooq.services;

import com.springboot.jooq.model.PostModel;
import com.springboot.jooq.repositories.PostRepository;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;

@Description(value = "Post service layer dependency responsible for processing data.")
@Service
public class PostService {

    private PostRepository postRepository;

    /**
     * Constructor dependency injector
     * @param postRepository - repository layer dependency
     */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Method for getting all post model data
     *
     * @return list of custom DTO post data
     */
    public List<PostModel> findAll() {
        return postRepository.findAll();
    }
}
