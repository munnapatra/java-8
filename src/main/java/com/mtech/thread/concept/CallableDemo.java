package com.mtech.thread.concept;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableDemo {
	public static void main(String[] args)
			throws InterruptedException, ExecutionException {

		// Callable & Future
		Callable<Integer> task = () -> {
			Thread.sleep(1000);
			return 10;
		};

		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(task);

		System.out.println(future.get());
		executor.shutdown();

		// CompletableFuture
		CompletableFuture.supplyAsync(() -> 10).thenApply(x -> x * 2)
				.thenAccept(System.out::println);

		// thenApply vs thenCompose
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 10)
				.thenCompose(x -> CompletableFuture.supplyAsync(() -> x * 2));
		System.out.println(cf.get());

		// ✅ Java 8 uses CAS + synchronized on bins, not whole map.
		Map<String, Integer> map = new ConcurrentHashMap<>();
		map.put("A", 1);
		map.compute("A", (k, v) -> v + 1);

		// AtomicInteger & CAS
		AtomicInteger count = new AtomicInteger(0);
		count.incrementAndGet();
		count.compareAndSet(1, 5);
		
		//ThreadLocal (Very Important)
		ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
		threadLocal.set(100);
		System.out.println(threadLocal.get());
		threadLocal.remove();		//⚠ Memory Leak Risk in thread pools if remove() is missed.

	}
}
