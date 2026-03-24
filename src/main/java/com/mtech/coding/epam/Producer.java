package com.mtech.coding.epam;

import com.mtech.thread.concept.SharedResource;

public class Producer implements Runnable {

	private final SharedResource buffer;

    public Producer(SharedResource buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 1;
        try {
            while (true) {
                buffer.produce(value++);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
