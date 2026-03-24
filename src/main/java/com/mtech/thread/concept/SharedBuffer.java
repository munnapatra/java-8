package com.mtech.thread.concept;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBuffer {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int value) throws InterruptedException {

        lock.lock();
        try {
            while (buffer.size() == capacity) {
                notFull.await();  // wait if full
            }

            buffer.add(value);
            System.out.println("Produced: " + value);

            notEmpty.signal();  // notify consumer

        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {

        lock.lock();
        try {
            while (buffer.isEmpty()) {
                notEmpty.await();  // wait if empty
            }

            int value = buffer.poll();
            System.out.println("Consumed: " + value);

            notFull.signal();  // notify producer

        } finally {
            lock.unlock();
        }
    }
}