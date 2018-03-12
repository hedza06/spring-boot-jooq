package com.springboot.jooq.services;

import com.springboot.jooq.model.PostModel;
import com.springboot.jooq.repositories.PostRepository;
import com.springboot.jooq.utils.model.*;
import com.springboot.jooq.utils.FieldSortUtil;
import org.jooq.Condition;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.springboot.sources.tables.Post.POST;

@Description(value = "Post service layer dependency responsible for processing data.")
@Service
public class PostService {

    private PostRepository postRepository;
    private FieldSortUtil fieldSortUtil;


    /**
     * Constructor dependency injector
     * @param postRepository - repository layer dependency
     */
    public PostService(PostRepository postRepository, FieldSortUtil fieldSortUtil) {
        this.postRepository = postRepository;
        this.fieldSortUtil = fieldSortUtil;
    }

    /**
     * Method for getting all post model data
     *
     * @return list of custom DTO post data
     */
    public List<PostModel> findAll() {
        return postRepository.findAll();
    }

    /**
     * Method for getting filtered posts.
     *
     * @param pageRequest - provided page request.
     * @return List of Post Models
     */
    public List<PostModel> findFiltered(PageRequest pageRequest)
    {
        List<SortField<?>> sort = generateSortFields(pageRequest.getSort());
        List<Condition> filters = generateFilterFields(pageRequest.getFilters());
        Page page = pageRequest.getPage();

        PageableModel pageableModel = new PageableModel(page, sort, filters);
        return postRepository.findFiltered(pageableModel);
    }

    /**
     * Method for counting filtered rows by provided filters
     *
     * @param pageRequest - page request params
     * @return total records
     */
    public Integer countFiltered(PageRequest pageRequest)
    {
        List<Condition> filters = generateFilterFields(pageRequest.getFilters());
        return postRepository.countFiltered(filters);
    }

    /**
     * Method for storing transactional post
     *
     * @param postModel - provided post data
     * @return inserted post model (on success)
     */
    public PostModel storeTransactionalPost(PostModel postModel) {
        return postRepository.storeTransactional(postModel);
    }

    /**
     * Method for executing stored procedure
     *
     * @param postTitle - provided post title
     * @return number of posts with provided title.
     */
    public Integer handleProcedureCall(String postTitle) {
        return postRepository.handleProcedure(postTitle);
    }

    /**
     * Method for generating sort fields
     *
     * @param sort - provided sort object
     * @return list of sorted fields.
     */
    private List<SortField<?>> generateSortFields(Sort sort)
    {
        List<SortField<?>> sortFields = new ArrayList<>();
        if (sort != null)
        {
            String sortBy = sort.getSortBy();
            fieldSortUtil.setSortDirection(sort.getSortDirection());

            switch (sortBy)
            {
                case "title":
                    sortFields.add(fieldSortUtil.createSortedTableField(POST.TITLE));
                    break;

                case "userId":
                    sortFields.add(fieldSortUtil.createSortedTableField(POST.USER_ID));
                    break;

                default:
                    sortFields.add(fieldSortUtil.createSortedTableField(POST.ID));
            }
        }
        return sortFields;
    }

    /**
     * Method for generating filter fields.
     *
     * @param filters - list of provided filters
     * @return List of JOOQ Condition objects
     */
    private List<Condition> generateFilterFields(List<Filter> filters)
    {
        List<Condition> filterFields = new ArrayList<>();
        for (Filter filter : filters) {
            filterFields.add(createCondition(filter));
        }
        return filterFields;
    }

    /**
     * Method for creating dynamic where clause.
     *
     * @param filter - filter data
     * @return JOOQ Condition
     */
    private Condition createCondition(Filter filter)
    {
        if (filter.getFilterBy() == null) {
            return DSL.trueCondition();
        }

        Condition condition = DSL.trueCondition();
        String filterValue = filter.getFilterValue();
        switch (filter.getFilterBy())
        {
            case "title":
                condition = condition.and(POST.TITLE.contains(filterValue));
                break;

            case "userId":
                condition = condition.and(POST.USER_ID.eq(UInteger.valueOf(filterValue)));
                break;

            default:
                // nothing here
        }
        return condition;
    }
}
