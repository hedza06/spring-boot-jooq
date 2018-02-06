package com.springboot.jooq.services;

import com.springboot.jooq.model.UserModel;
import com.springboot.jooq.model.UserWithPostModel;
import com.springboot.jooq.repositories.UserRepository;
import com.springboot.sources.tables.pojos.User;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;

@Description(value = "User service layer dependency responsible for processing data.")
@Service
public class UserService {

    private UserRepository userRepository;

    /**
     * Constructor dependency injector.
     * @param userRepository - repository layer dependency.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method for getting all users
     *
     * @return list of users.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method for getting user by id.
     *
     * @param id - user identifier
     * @return user
     */
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    /**
     * Method for getting user by id
     *
     * @param id - provided user identifier
     * @return user model object
     */
    public UserModel findModelOne(Integer id) {
        return userRepository.findModelOne(id);
    }

    /**
     * Method for getting user by id with posts
     *
     * @param id - user identifier
     * @return User with post data
     */
    public UserWithPostModel findOneWithPosts(Integer id) {
        return userRepository.findOneWithPosts(id);
    }

    /**
     * Method for updating user data
     *
     * @param user - provided user data
     * @return updated user object.
     */
    public User update(User user)
    {
        Integer status = userRepository.update(user);
        if (status == -1) {
            return null;
        }
        return user;
    }

    /**
     * Method for storing new user
     *
     * @param user - provided user
     * @return stored user object.
     */
    public User save(User user) {
        return userRepository.save(user);
    }

}
