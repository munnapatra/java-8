package com.mtech.thread.concept;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
	private final Queue<Integer> buffer = new LinkedList<>();
	private final int capacity;

	public SharedResource(int capacity) {
		this.capacity = capacity;
	}

	public synchronized void produce(int value) throws InterruptedException   {

		while (buffer.size() == capacity) {
			wait(); // release lock and wait
		}

		buffer.add(value);
		System.out.println("Produced: " + value);

		notifyAll(); // notify waiting consumers
	}

	public synchronized int consume() throws InterruptedException {

		while (buffer.isEmpty()) {
			wait(); // release lock and wait
		}

		int value = buffer.poll();
		System.out.println("Consumed: " + value);

		notifyAll(); // notify waiting producers
		return value;
	}
}