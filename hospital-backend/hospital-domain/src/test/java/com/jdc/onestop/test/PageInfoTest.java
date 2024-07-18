package com.jdc.onestop.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.onestop.hospital.domain.PageInfo;

public class PageInfoTest {

	@ParameterizedTest
	@CsvSource({
		"0,10,55",
		"1,10,55",
		"2,10,55",
		"3,10,55",
		"4,10,55",
		"5,10,55",
		"6,10,55",
		"0,10,11",
	})
	void test(int page, int size, long count) {
		
		var pageInfo = new PageInfo<String>(null, page, size, count);
		System.out.println(page);
		System.out.println(pageInfo.getTotalPages());
		System.out.println(pageInfo.getPageList());
	}
}
