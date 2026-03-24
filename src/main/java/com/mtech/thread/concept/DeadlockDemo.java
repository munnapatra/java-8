package com.mtech.thread.concept;

public class DeadlockDemo {
	static final Object A = new Object();
	static final Object B = new Object();

	public static void main(String[] args) {
		new Thread(() -> {
			synchronized (A) {
				synchronized (B) {
					System.out.println("Thread 1");
				}
			}
		}).start();

		new Thread(() -> {
			synchronized (B) {
				synchronized (A) {
					System.out.println("Thread 2");
				}
			}
		}).start();
	}
	
	//✅ How to avoid? ✔ Lock ordering, ✔ TryLock, ✔ Timeout locks
}