package com.mtech.thread.concept;
public class Demo extends Thread {
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		Demo t = new Demo();

		t.run(); // ❌ NOT a new thread
		t.start(); // ✅ New thread created
	}
}