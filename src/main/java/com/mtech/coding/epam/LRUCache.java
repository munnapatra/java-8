package com.mtech.coding.epam;

import java.io.Serial;
import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	@Serial
	private static final long serialVersionUID = 1L;

	private final int capacity;

	public LRUCache(int capacity) {
		super(capacity, 0.75f, false); // accessOrder = true
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > capacity;
	}

	public V getValue(K key) {
		return super.get(key);
	}

	public void putValue(K key, V value) {
		super.put(key, value);
	}
}
