package com.springboot.jooq.repositories;

import com.springboot.jooq.model.PostModel;
import com.springboot.jooq.utils.model.PageableModel;
import com.springboot.sources.Routines;
import com.springboot.sources.tables.pojos.User;
import com.springboot.sources.tables.records.PostRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.ResultQuery;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
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
     * Method for getting filtered posts
     *
     * @param pageableModel - contains page, sort and filter data
     * @return list of post models
     */
    public List<PostModel> findFiltered(PageableModel pageableModel)
    {
        return dslContext
                .select(POST.ID, POST.TITLE, POST.PUBLISHED_AT, USER.ID, USER.FIRST_NAME, USER.LAST_NAME, USER.AGE)
                .from(POST)
                .innerJoin(USER).on(POST.USER_ID.eq(USER.ID))
                .where(pageableModel.getFilters())
                .orderBy(pageableModel.getSortFields())
                .limit(pageableModel.getPage().getLimit())
                .offset(pageableModel.getPage().getOffset())
                .fetch().map(this::mapPostRecord);
    }

    /**
     * Method for counting filtered methods.
     *
     * @param filters - dynamic where parameters
     * @return total count of filtered rows.
     */
    public Integer countFiltered(List<Condition> filters)
    {
        return dslContext
                .selectCount()
                .from(POST)
                .innerJoin(USER).on(POST.USER_ID.eq(USER.ID))
                .where(filters)
                .fetchOneInto(Integer.class);
    }

    /**
     * Method for storing post in transaction.
     *
     * @param postModel - post data
     * @return post model or null
     */
    public PostModel storeTransactional(PostModel postModel)
    {
        try
        {
            dslContext.transaction(configuration -> {

                // insert post record
                PostRecord postRecord = DSL.using(configuration)
                        .insertInto(POST, POST.TITLE, POST.USER_ID)
                        .values(postModel.getTitle(), postModel.getUser().getId())
                        .returning()
                        .fetchOne();

                // dummy transaction that makes error
                DSL.using(configuration)
                        .insertInto(USER, USER.ID)
                        .values(UInteger.valueOf(20))
                        .execute();

                // setting post model new id.
                postModel.setId(postRecord.getId().intValue());
            });
            return postModel;
        }
        catch (Exception e)
        {
            // log exception if needed
            return null;
        }
    }

    /**
     * Method for getting post count from provided post title
     *
     * @param postTitle - provided post title
     * @return post count by provided title
     */
    public Integer handleProcedure(String postTitle) {
        return Routines.postProcedure(dslContext.configuration(), postTitle);
    }

    /**
     * Method for getting all posts by native query
     *
     * @return list of post model objects
     */
    public List<PostModel> findAllNative()
    {
        String rawQuery = "SELECT post.id, post.title, post.published_at, user.id, " +
                "user.first_name, user.last_name, user.age " +
                "FROM post JOIN user ON post.user_id = user.id";

        ResultQuery<Record> resultQuery = dslContext.resultQuery(rawQuery);
        return resultQuery
                .fetch()
                .map(this::mapPostRecord);
    }

    /**
     * Method for getting post by native query
     *
     * @param id - post identifier
     * @return PostModel
     */
    public PostModel findOneByNative(Integer id)
    {
        String rawQuery = "SELECT post.id, post.title, post.published_at, user.id, " +
                "user.first_name, user.last_name, user.age " +
                "FROM post JOIN user ON post.user_id = user.id " +
                "WHERE post.id = :id";

        ResultQuery<Record> resultQuery = dslContext.resultQuery(rawQuery, DSL.param("id"));
        resultQuery.bind("id", UInteger.valueOf(id));

        return resultQuery
                .fetchOne()
                .map(this::mapPostRecord);
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
