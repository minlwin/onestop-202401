package com.jdc.onestop.hospital.domain;

import java.util.List;

public record PageInfo<T>(List<T> contents, int page, int size, long count) {

	public static<T> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T> {
		private List<T> contents;
		private int page;
		private int size;
		private long count;
		
		public PageInfo<T> build() {
			return new PageInfo<>(contents, page, size, count);
		}

		public Builder<T> contents(List<T> contents) {
			this.contents = contents;
			return this;
		}

		public Builder<T> page(int page) {
			this.page = page;
			return this;
		}

		public Builder<T> size(int size) {
			this.size = size;
			return this;
		}

		public Builder<T> count(long count) {
			this.count = count;
			return this;
		}

	}
}
