package com.mtech.thread.concept;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	
	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();

		try {
			lock.lock();
			// critical section
		} finally {
			lock.unlock();
		}
		
		//TryLock (Deadlock Prevention)
		if (lock.tryLock(1, TimeUnit.SECONDS)) {
		    try {
		        // work
		    } finally {
		        lock.unlock();
		    }
		}

	}
}
