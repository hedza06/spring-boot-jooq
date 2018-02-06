package com.springboot.jooq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Description;

import java.util.List;

@Description(value = "User model with posts - only for demo purpose.")
public class UserWithPostModel {

    @JsonProperty("user")
    private UserModel userModel;

    @JsonProperty("posts")
    private List<PostModel> postModelList;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<PostModel> getPostModelList() {
        return postModelList;
    }

    public void setPostModelList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
    }
}
