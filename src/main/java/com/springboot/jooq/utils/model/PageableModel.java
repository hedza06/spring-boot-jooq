package com.springboot.jooq.utils.model;

import org.jooq.Condition;
import org.jooq.SortField;
import org.springframework.context.annotation.Description;

import java.util.List;

@Description(value = "Pageable model similar JPA Pageable.")
public class PageableModel {

    private Page page;
    private List<SortField<?>> sortFields;
    private List<Condition> filters;

    public PageableModel(Page page, List<SortField<?>> sortFields, List<Condition> filters)
    {
        this.page = page;
        this.sortFields = sortFields;
        this.filters = filters;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<SortField<?>> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<SortField<?>> sortFields) {
        this.sortFields = sortFields;
    }

    public List<Condition> getFilters() {
        return filters;
    }

    public void setFilters(List<Condition> filters) {
        this.filters = filters;
    }
}
