package com.mtech.thread.concept;

public class FlagExample {
	//❌ NOT thread-safe: volatile int count = 0; count++; // not atomic
	private volatile boolean running = true;

	public void stop() {
		running = false;
	}

	public void run() {
		while (running) {
			// do work
		}
	}
}