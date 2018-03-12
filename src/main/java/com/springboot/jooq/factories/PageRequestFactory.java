package com.springboot.jooq.factories;

import com.springboot.jooq.utils.model.*;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Description(value = "Page Request Factory.")
public class PageRequestFactory
{
	private static final String PAGE_NUMBER_KEY    = "page";
	private static final String PAGE_SIZE_KEY      = "page_size";
	private static final String SORT_BY_KEY        = "sort_by";
	private static final String SORT_DIRECTION_KEY = "sort_direction";

    /**
     * Private constructor - can not be instantiated.
     */
	private PageRequestFactory() { }

	public static PageRequest defaultRequest()
	{
		return fromParameterMap(null);
	}

	public static PageRequest fromParameterMap(Map<String, String> parameters)
	{
		PageRequest pageRequest = new PageRequest();

		Page page = preparePage(parameters);
		pageRequest.setPage(page);
		removePageParameters(parameters);

		Sort sort = prepareSort(parameters);
		pageRequest.setSort(sort);
		removeSortParameters(parameters);

		List<Filter> filters = prepareFilters(parameters);
		pageRequest.setFilters(filters);

		return pageRequest;
	}

	private static List<Filter> prepareFilters(Map<String, String> parameters)
	{
		List<Filter> filters = new ArrayList<>();
		String parameterValue;

		if (parameters != null)
		{
			for (Map.Entry<String, String> paramEntry : parameters.entrySet())
			{
				parameterValue = paramEntry.getValue();
				if (parameterValue != null) {
					filters.add(new Filter(paramEntry.getKey(), parameterValue));
				}
			}
		}
		return filters;
	}

	private static void removeSortParameters(Map<String, String> parameters)
	{
		removeParameterByKey(parameters, SORT_BY_KEY);
		removeParameterByKey(parameters, SORT_DIRECTION_KEY);
	}

	private static void removePageParameters(Map<String, String> parameters)
	{
		removeParameterByKey(parameters, PAGE_NUMBER_KEY);
		removeParameterByKey(parameters, PAGE_SIZE_KEY);
	}

	private static void removeParameterByKey(Map<String, String> parameters, String pageKey)
	{
		if (parameters != null) {
			parameters.remove(pageKey);
		}
	}

	private static Page preparePage(Map<String, String> parameters)
	{
		Integer pageNumber = null;
		Integer pageSize = null;

		if (parameters != null)
		{
			pageNumber = parameters.containsKey(PAGE_NUMBER_KEY) ? Integer.parseInt(parameters.get(PAGE_NUMBER_KEY))
					: null;
			pageSize = parameters.containsKey(PAGE_SIZE_KEY) ? Integer.parseInt(parameters.get(PAGE_SIZE_KEY)) : null;
		}
		return new Page(pageNumber, pageSize);
	}

	private static Sort prepareSort(Map<String, String> parameters)
	{
		Sort sort = null;
		if (parameters != null && parameters.containsKey(SORT_BY_KEY)) {
			sort = createSort(parameters.get(SORT_BY_KEY), parameters.get(SORT_DIRECTION_KEY));
		}
		return sort;
	}

	private static Sort createSort(String sortBy, String sortDirection)
	{
		// If sortBy is null or empty, there is no sort
		if (sortBy == null || sortBy.isEmpty()) {
			return null;
		}

		// Default is sort Ascending
		SortDirection direction = SortDirection.ASC;

		// If sortDirection is NOT null and empty, try to create enums
		if (sortDirection != null && !sortDirection.isEmpty()) {
			direction = SortDirection.valueOf(sortDirection.toUpperCase());
		}
		return new Sort(sortBy, direction);
	}
}
