package com.springboot.jooq.resources;

import com.springboot.jooq.factories.PageRequestFactory;
import com.springboot.jooq.model.PostModel;
import com.springboot.jooq.services.PostService;
import com.springboot.jooq.utils.model.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostResource.class);

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


    @GetMapping(value = "/filtered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getFilteredPosts(@RequestParam Map<String, String> requestParams)
    {
        PageRequest pageRequest = PageRequestFactory.fromParameterMap(requestParams);

        List<PostModel> postList = postService.findFiltered(pageRequest);
        Integer totalRecords = postService.countFiltered(pageRequest);

        Map<String, Object> responsePayload = new HashMap<>(2);
        responsePayload.put("data", postList);
        responsePayload.put("count", totalRecords);

        return new ResponseEntity<>(responsePayload, HttpStatus.OK);
    }


    @PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostModel> transactionExample(@RequestBody @Valid PostModel postModel)
    {
        PostModel postModelInserted = postService.storeTransactionalPost(postModel);
        if (postModelInserted == null)
        {
            LOGGER.error("Transactional exception occurred. Returning internal server error.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postModelInserted, HttpStatus.CREATED);
    }


    @PostMapping(value = "/procedure-call", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> storeProcExample(@RequestBody Map<String, Object> postParams)
    {
        try
        {
            String postTitle = String.valueOf(postParams.get("title"));
            Integer numberOfPosts = postService.handleProcedureCall(postTitle);

            return new ResponseEntity<>(numberOfPosts, HttpStatus.OK);
        }
        catch (NullPointerException e)
        {
            LOGGER.error("Null pointer exception occurred. Cause: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/native-query/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostModel> nativeQueryExample(@PathVariable(value = "id") Integer id)
    {
        PostModel postModel = postService.findOneByNative(id);
        return Optional.ofNullable(postModel)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
