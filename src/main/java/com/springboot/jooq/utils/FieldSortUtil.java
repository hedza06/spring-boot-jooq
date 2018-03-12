package com.springboot.jooq.utils;

import com.springboot.jooq.utils.model.SortDirection;
import org.jooq.Field;
import org.jooq.SortField;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FieldSortUtil {

    private SortDirection sortDirection;

    public FieldSortUtil()
    {
        this.sortDirection = SortDirection.ASC;
    }

    public SortDirection getSortDirection()
    {
        return this.sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection)
    {
        this.sortDirection = sortDirection;
    }

    public SortField<?> createSortedTableField(Field<?> field) {
        return this.sortDirection == SortDirection.ASC ? field.asc() : field.desc();
    }
}
