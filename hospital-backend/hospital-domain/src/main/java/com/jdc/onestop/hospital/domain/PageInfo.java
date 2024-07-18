package com.jdc.onestop.hospital.domain;

import java.util.ArrayList;
import java.util.List;

public record PageInfo<T>(List<T> contents, int page, int size, long count) {

	public Long getTotalPages() {
		return count % size == 0 ? count / size : (count / size) + 1;
	}
	
	public List<Integer> getPageList() {
		
		List<Integer> pages = new ArrayList<Integer>();
		pages.add(page);
		
		while(pages.getFirst() > 0 && pages.size() < 2) {
			pages.addFirst(pages.getFirst() - 1);
		}
		
		while(pages.size() < 5 && pages.getLast() < getTotalPages() - 1) {
			pages.add(pages.getLast() + 1);
		}

		while(pages.size() < 5 && pages.getFirst() > 0) {
			pages.addFirst(pages.getFirst() - 1);
		}

		return pages;
	}
}
