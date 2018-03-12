package com.springboot.jooq.utils.model;

import org.springframework.context.annotation.Description;

@Description(value = "Page description class.")
public class Page
{
	private final int number;
	private final int size;
	private final int offset;
	private final int limit;

	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int DEFAULT_PAGE_NUMBER = 1;

	public Page()
	{
		this.number = DEFAULT_PAGE_NUMBER;
		this.size = DEFAULT_PAGE_SIZE;
		limit = Math.max(1, this.size);
		offset = Math.max(0, (this.number - 1) * limit);
	}
	
	public static Page ofPageNumber(int pageNumber)
	{
		return new Page(pageNumber, DEFAULT_PAGE_SIZE);
	}
	
	public Page(Integer page, Integer size)
	{
		this.number = page != null ? page : DEFAULT_PAGE_NUMBER;
		this.size = size != null ? size : DEFAULT_PAGE_SIZE;
		limit = Math.max(1, this.size);
		offset = Math.max(0, (this.number - 1) * limit);
	}

	public int getPage()
	{
		return number;
	}

	public int getSize()
	{
		return size;
	}

	public int getOffset()
	{
		return offset;
	}

	public int getLimit()
	{
		return limit;
	}

	@Override
	public String toString() {
		return String.format("{page = %d, size = %d, limit = %d, offset = %d", number, size, limit, offset);
	}
}