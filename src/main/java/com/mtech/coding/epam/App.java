package com.mtech.coding.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
	public static void main(String[] args) {
		List<String> asList = Arrays.asList("a","b","a");
		Map<Character, String> map = asList.stream().collect(Collectors.toMap(s -> s.charAt(0), s -> s, (a,b)->a));
		map.entrySet().stream().forEach(System.out::println);
		
		int counter[] = {0};
		
		Stream.of("apple", "banana", "cherry").filter( s -> {
			counter[0]++;
			return s.startsWith("a");
		}).count();
		System.out.println("Counter: "+ counter[0]);
		
		
		// Write a program to find the second highest number in an array.
		int[] number = {2, 7, 9, 11, 8, 1, 3, 45, 23, 6};
		Integer orElse = Arrays.stream(number).boxed().distinct()
				.sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.orElse(0);
		System.out.println(orElse);

		// Reverse a linked list iteratively and recursively.
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(4);
		list.add(3);
		list.add(6);
		list.add(7);

		// Given a string, find the first non-repeated character.
		String str = "swiss";
		Character key = str.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1).findFirst()
				.get().getKey();
		System.out.println(key);

		LRUCache<String, String> cache = new LRUCache<>(3);
		cache.put("A", "Apple");
		cache.put("B", "Banana");
		cache.put("C", "Cherry");
		cache.get("A"); // A becomes most recently used
		cache.put("D", "Date"); // Removes B (least recently used)

		// Find all pairs of integers whose sum equals a given target.
		Map<Integer, Integer> pairs = findPairs(number, 9);
		System.out.println(pairs);

	}
	private static Map<Integer, Integer> findPairs(int[] number, int target) {
		Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
		List<Integer> collect = new ArrayList<Integer>();
		for (int i = 0; i < number.length; i++) {
			int pair = target- number[i];
			if(!collect.contains(pair)) {
				collect.add(number[i]);
			}else {
				pairs.put(pair, number[i]);
			}
			
		}
		
		return pairs;

	}
	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		ListNode current = head;

		while (current != null) {
			ListNode next = current.next; // store next node
			current.next = prev; // reverse pointer
			prev = current; // move prev
			current = next; // move current
		}

		return prev; // new head
	}
	public ListNode reverseRecursive(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseRecursive(head.next);
		head.next.next = head; // reverse link
		head.next = null; // break original link
		return newHead;
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}
}
