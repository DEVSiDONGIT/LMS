package com.te.lmsproject.lmsresponce;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResponce {

	private List<Object> content;

	private int pageNumber;

	private int pageSize;

	private long totalElements;

	private int totalPages;

	private boolean lastPage;
}
