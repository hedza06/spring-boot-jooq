package com.springboot.jooq.repositories;

import com.springboot.jooq.model.PostModel;
import com.springboot.jooq.model.UserModel;
import com.springboot.jooq.model.UserWithPostModel;
import com.springboot.sources.tables.pojos.User;
import com.springboot.sources.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.springboot.sources.tables.Post.POST;
import static com.springboot.sources.tables.User.USER;

@Description(value = "User repository layer responsible for communicating with database.")
@Repository
public class UserRepository {

    private DSLContext dslContext;

    /**
     * Constructor dependency injector
     * @param dslContext - dsl jooq repository dependency
     */
    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    /**
     * Method for getting all users
     *
     * @return list of users
     */
    public List<User> findAll()
    {
        return dslContext
                .select(USER.ID, USER.FIRST_NAME, USER.LAST_NAME,
                        USER.AGE, USER.CREATED_AT, USER.IS_ACTIVE)
                .from(USER)
                .fetchInto(User.class);
    }

    /**
     * Method for getting specific user by identifier.
     *
     * @param id - user identifier
     * @return User object
     */
    public User findOne(Integer id)
    {
        return dslContext
                .select(USER.ID, USER.FIRST_NAME, USER.LAST_NAME,
                        USER.AGE, USER.CREATED_AT, USER.IS_ACTIVE)
                .from(USER)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .fetchOneInto(User.class);
    }

    /**
     * Method for getting user model data by id
     *
     * @param id - user identifier
     * @return user model data.
     */
    public UserModel findModelOne(Integer id)
    {
        return dslContext
                .select(USER.ID, USER.FIRST_NAME, USER.LAST_NAME)
                .from(USER)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .fetchOneInto(UserModel.class);
    }

    /**
     * Method for getting user with its posts
     *
     * @param id - user identifier
     * @return User with post model data
     */
    public UserWithPostModel findOneWithPosts(Integer id)
    {
        Map<UserModel, List<PostModel>> result =
                dslContext
                    .select(USER.ID, USER.FIRST_NAME, USER.LAST_NAME, POST.ID, POST.TITLE, POST.PUBLISHED_AT)
                    .from(USER)
                    .leftJoin(POST).on(USER.ID.eq(POST.USER_ID))
                    .where(USER.ID.eq(UInteger.valueOf(id)))
                    .fetchGroups(
                            record -> record.into(USER.fields()).into(UserModel.class),
                            record -> record.into(POST.fields()).into(PostModel.class)
                    );

        // map provided data
        return mapUserWithPost(result);
    }

    /**
     * Method for updating user data
     *
     * @param user - provided user
     * @return affected rows.
     */
    public Integer update(User user)
    {
        return dslContext
                .update(USER)
                    .set(USER.FIRST_NAME, user.getFirstName())
                    .set(USER.LAST_NAME, user.getLastName())
                    .set(USER.AGE, user.getAge())
                    .execute();
    }

    /**
     * Method for storing new user data
     *
     * @param user - provided user
     * @return saved user object.
     */
    public User save(User user)
    {
        UserRecord userRecord = dslContext
                .insertInto(USER, USER.FIRST_NAME, USER.LAST_NAME, USER.AGE)
                .values(user.getFirstName(), user.getLastName(), user.getAge())
                .returning()
                .fetchOne();

        // setting user id
        user.setId(userRecord.getId());
        return user;
    }

    /**
     * Method for mapping user with its posts
     *
     * @param data - provided linked map
     * @return User With Post Data Object.
     */
    private UserWithPostModel mapUserWithPost(Map<UserModel, List<PostModel>> data)
    {
        UserWithPostModel userWithPostModel = new UserWithPostModel();
        userWithPostModel.setUserModel((UserModel) data.keySet().toArray()[0]);

        List<PostModel> postModelList = new ArrayList<>();
        data.values().forEach(postList -> postModelList.addAll(postList));

        userWithPostModel.setPostModelList(postModelList);

        return userWithPostModel;
    }
}
