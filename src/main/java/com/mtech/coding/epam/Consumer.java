package com.mtech.coding.epam;

import com.mtech.thread.concept.SharedResource;

public class Consumer implements Runnable {

	private final SharedResource buffer;

	public Consumer(SharedResource buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		try {
			while (true) {
				buffer.consume();
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
