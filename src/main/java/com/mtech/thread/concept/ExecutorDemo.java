package com.mtech.thread.concept;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		executor.submit(() -> {
			Thread.currentThread().setName("main-thread");
			System.out.println(Thread.currentThread().getName());
		});

		executor.shutdown();

		// Custom ThreadPoolExecutor
		ExecutorService executor1 = new ThreadPoolExecutor(2, 4, 10,
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		executor1.submit(() -> {
			Thread.currentThread().setName("custom-thread");
			System.out.println(Thread.currentThread().getName());
		});

		executor1.shutdown();

	}
}
