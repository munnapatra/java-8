package com.mtech.coding.epam;

import com.mtech.thread.concept.SharedResource;

public class ProducerConsumerTest {
	public static void main(String[] args) {
		SharedResource buffer = new SharedResource(2);

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
	}

}
