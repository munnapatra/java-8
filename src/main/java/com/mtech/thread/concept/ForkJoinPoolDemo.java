package com.mtech.thread.concept;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ForkJoinPoolDemo {
	public static void main(String[] args) {
		ForkJoinPool pool = ForkJoinPool.commonPool();

		pool.submit(() -> IntStream.range(1, 10).parallel()
				.forEach(System.out::println));

	}
}
