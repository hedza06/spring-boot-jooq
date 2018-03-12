package com.springboot.jooq.utils.model;

import org.springframework.context.annotation.Description;

@Description(value = "Filter Model.")
public class Filter
{
	private String filterBy;
	private String filterValue;

	public Filter(String filterBy, String filterValue)
	{
		this.filterBy = filterBy;
		this.filterValue = filterValue;
	}

	public String getFilterBy()
	{
		return filterBy;
	}

	public void setFilterBy(String filterBy)
	{
		this.filterBy = filterBy;
	}

	public String getFilterValue()
	{
		return filterValue;
	}

	public void setFilterValue(String filterValue)
	{
		this.filterValue = filterValue;
	}
	
	@Override
	public String toString()
	{
		return "filterBy = " + filterBy + ", filterValue = " + filterValue;
	} 
}
