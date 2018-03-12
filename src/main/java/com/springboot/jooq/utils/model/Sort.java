package com.springboot.jooq.utils.model;

import org.springframework.context.annotation.Description;

@Description(value = "Sort class definition.")
public class Sort
{
	private String sortBy;
	private SortDirection sortDirection;

	public Sort() {
	    // default constructor.
    }

	public Sort(String sortBy)
	{
		this.sortBy = sortBy;
		this.sortDirection = SortDirection.ASC;
	}

	public Sort(String sortBy, SortDirection sortDirection)
	{
		this.sortBy = sortBy;
		this.sortDirection = sortDirection;
	}

	public String getSortBy()
	{
		return sortBy;
	}

	public void setSortBy(String sortBy)
	{
		this.sortBy = sortBy;
	}

	public SortDirection getSortDirection()
	{
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection)
	{
		this.sortDirection = sortDirection;
	}

}