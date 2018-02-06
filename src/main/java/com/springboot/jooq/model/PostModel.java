package com.springboot.jooq.model;

import com.springboot.sources.tables.pojos.User;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;

@Description(value = "Post model only for demo purpose.")
public class PostModel {

    private Integer id;
    private User user;
    private String title;
    private LocalDateTime publishedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
