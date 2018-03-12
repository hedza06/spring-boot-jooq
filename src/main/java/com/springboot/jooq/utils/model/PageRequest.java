package com.springboot.jooq.utils.model;

import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;

@Description(value = "Page Request Model.")
public class PageRequest
{
	private Page page;
	private Sort sort;
	private List<Filter> filters;

	public Page getPage()
	{
		return page;
	}

	public void setPage(Page page)
	{
		this.page = page;
	}

	public Sort getSort()
	{
		return sort;
	}

	public void setSort(Sort sort)
	{
		this.sort = sort;
	}

	public List<Filter> getFilters()
	{
		return filters;
	}

	public void setFilters(List<Filter> filters)
	{
		this.filters = filters;
	}

	public void addFilter(Filter filter)
	{
		if (filters == null) {
			filters = new ArrayList <> ();
		}
		this.filters.add(filter);
	}
}
