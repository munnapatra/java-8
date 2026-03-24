package com.mtech.thread.concept;

public class Counter {

	private int count = 0;
	
	// Static Synchronization (Class Lock) 🔹 Lock → Counter.class
	public static synchronized void print() {
		System.out.println("Static synchronized method");
	}

	// Synchronization (Object Lock)
	public synchronized void increment() {
		count++;
	}

	public int getCount() {
		return count;
	}

	// Synchronized Block (Preferred)
	public void decrement() {
		synchronized (this) {
			count--;
		}
	}
}