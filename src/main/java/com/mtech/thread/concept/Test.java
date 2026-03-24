package com.mtech.thread.concept;

import java.util.concurrent.CompletableFuture;

public class Test {
	public static void main(String[] args) {
		CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			return 42;
		});
		
		System.out.println(supplyAsync.join());
		
		System.out.println(Runtime.getRuntime().availableProcessors()-1);
	}
}