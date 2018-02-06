package com.springboot.jooq.repositories;

import com.springboot.jooq.model.PostModel;
import com.springboot.sources.tables.pojos.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.springboot.sources.tables.Post.POST;
import static com.springboot.sources.tables.User.USER;

@Description(value = "Post repository layer responsible for communicating with database.")
@Repository
public class PostRepository {

    private DSLContext dslContext;

    /**
     * Constructor dependency injector
     * @param dslContext - jooq repository layer dependency
     */
    public PostRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * Method for getting all posts - dto mapping
     *
     * @return list of post model data
     */
    public List<PostModel> findAll()
    {
        return dslContext
                .select(POST.ID, POST.TITLE, POST.PUBLISHED_AT, USER.ID, USER.FIRST_NAME, USER.LAST_NAME, USER.AGE)
                .from(POST)
                .innerJoin(USER).on(POST.USER_ID.eq(USER.ID))
                .fetch().map(this::mapPostRecord);
    }

    /**
     * Method for mapping post model record
     *
     * @param record - current record
     * @return post model object.
     */
    private PostModel mapPostRecord(Record record)
    {
        // generate user data
        User user = new User();
        user.setId(record.getValue(USER.ID));
        user.setFirstName(record.getValue(USER.FIRST_NAME));
        user.setLastName(record.getValue(USER.LAST_NAME));
        user.setAge(record.getValue(USER.AGE));

        // generate post model data
        PostModel postModel = new PostModel();
        postModel.setId(record.getValue(POST.ID).intValue());
        postModel.setTitle(record.getValue(POST.TITLE));
        postModel.setPublishedAt(record.getValue(POST.PUBLISHED_AT).toLocalDateTime());
        postModel.setUser(user);

        return postModel;
    }
}
