package com.springboot.jooq.resources;

import com.springboot.jooq.model.UserModel;
import com.springboot.jooq.model.UserWithPostModel;
import com.springboot.jooq.services.UserService;
import com.springboot.sources.tables.pojos.User;
import org.jooq.types.UInteger;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Description(value = "Resource (controller) for user related APIs.")
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    /**
     * Constructor dependency injector.
     * @param userService - user service layer dependency
     */
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id)
    {
        User user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/dto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getModelUserById(@PathVariable(value = "id") Integer id)
    {
        UserModel userModel = userService.findModelOne(id);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

    @GetMapping(value = "/with-posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWithPostModel> getUserWithPost(@PathVariable(value = "id") Integer userId)
    {
        UserWithPostModel userWithPostModel = userService.findOneWithPosts(userId);
        return new ResponseEntity<>(userWithPostModel, HttpStatus.OK);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer id, @RequestBody User user)
    {
        if (user.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setId(UInteger.valueOf(id));
        User updatedUser = userService.update(user);

        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> storeUser(@RequestBody User user)
    {
        User insertedUser = userService.save(user);
        return new ResponseEntity<>(insertedUser, HttpStatus.CREATED);
    }
}
