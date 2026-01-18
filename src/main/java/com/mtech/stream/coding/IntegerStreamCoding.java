package com.mtech.stream.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerStreamCoding {
	private static List<Integer> numbers = Arrays.asList(9, 17, 66, 5, 10, 9,
			11, 24, 3, 14, 5, 6);

	public static void main(String[] args) {
		System.out.println("========================================");
		// Given a list of integers, filter the even numbers.
		List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0)
				.toList();
		System.out.println(evenNumbers);
		System.out.println("========================================");
		// Remove duplicates from a list using distinct().
		List<Integer> uniqueNumbers = numbers.stream().distinct().toList();
		System.out.println(uniqueNumbers);
		System.out.println("========================================");
		// Find the maximum number from a list of integers.
		Integer max = numbers.stream().max(Integer::compare).get();
		System.out.println(max);
		System.out.println("========================================");
		// Sort a list of integers in descending order.
		List<Integer> descSort = numbers.stream()
				.sorted(Comparator.reverseOrder()).toList();
		System.out.println(descSort);

		List<Integer> ascSort = numbers.stream().sorted().toList();
		System.out.println(ascSort);
		System.out.println("========================================");
		// Calculate the sum of all numbers in a list.
		int sum = numbers.stream().mapToInt(Integer::intValue).sum();
		System.out.println(sum);
		System.out.println("========================================");
		// Identify duplicate elements in a list.
		HashSet<Integer> unique = new HashSet<>();
		List<Integer> dupList = numbers.stream()
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() > 1)
				.map(Map.Entry::getKey).toList();
		System.out.println("Duplicate elements are:" + dupList);
		Set<Integer> duplicates = numbers.stream().filter(n -> !unique.add(n))
				.collect(Collectors.toSet());
		System.out.println("Duplicate elements are:" + duplicates);
		System.out.println("Unique elements are:" + unique);

		Set<Integer> duplicatesN = numbers.stream()
				.filter(n -> Collections.frequency(numbers, n) > 1)
				.collect(Collectors.toSet());
		System.out.println("Duplicate elements are:" + duplicatesN);
		System.out.println("========================================");
		// Given a list of lists, flatten it into a single list.
		List<List<Integer>> listOfLists = Arrays.asList(Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5), Arrays.asList(6, 7, 8, 9));
		List<Integer> list = listOfLists.stream().flatMap(List::stream)
				.toList();
		System.out.println(list);
		System.out.println("========================================");

		// Use parallel streams to compute the sum of numbers.
		long sum2 = numbers.parallelStream().mapToLong(Integer::intValue).sum();
		System.out.println(sum2);
		Integer sum3 = numbers.parallelStream().reduce(Integer::sum).get();
		System.out.println(sum3);
		System.out.println("========================================");

		// Calculate the average of a list of integers.
		Double average = numbers.stream().mapToInt(Integer::intValue).average()
				.orElse(0.0);
		System.out.println(average);
		System.out.println("========================================");
		// Partition a list of numbers into even and odd.
		Map<Boolean, List<Integer>> oddEven = numbers.stream()
				.collect(Collectors.partitioningBy(n -> n % 2 == 0));
		System.out.println(oddEven);
		System.out.println("========================================");

		// Find the nth largest element in a list. example n=3
		Integer thirdLargestNumber = numbers.stream()
				.sorted(Comparator.reverseOrder()).skip(2).findFirst().get();
		System.out.println(thirdLargestNumber);
		System.out.println("========================================");
		// Find common elements between two lists.
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
		List<Integer> commonElements = list1.stream().filter(list2::contains)
				.toList();
		System.out.println(commonElements);
		System.out.println("========================================");
		// Calculate the sum of squares of all even numbers in a list.
		int sumOfEvenSqures = numbers.stream().filter(n -> n % 2 == 0)
				.mapToInt(n -> n * n).sum();
		System.out.println(sumOfEvenSqures);
		System.out
				.println("==================================================");

		// Skip the first 3 elements and limit the result to the next 2.
		List<Integer> list3 = numbers.stream().skip(3).limit(2).toList();
		System.out.println(list3);
		System.out.println("========================================");
		// Compute the Cartesian product of two lists of integers.
		List<String> list4 = list1.stream()
				.flatMap(i -> list2.stream().map(j -> "(" + i + "," + j + ")"))
				.toList();
		System.out.println(list4);
		System.out.println("========================================");
		// Find the median of a list of integers.
		List<Integer> sortedList = numbers.stream().sorted().toList();
		System.out.println(sortedList);
		double median = sortedList.size() % 2 == 0
				? (sortedList.get(sortedList.size() / 2 - 1)
						+ sortedList.get(sortedList.size() / 2)) / 2.0
				: sortedList.get(sortedList.size() / 2);
		System.out.println(median);
		System.out.println("========================================");
		// Compute the running sum of a list of integers.
		AtomicInteger atomicSum = new AtomicInteger(0);
		List<Integer> runningSum = numbers.stream().map(atomicSum::addAndGet)
				.toList();
		System.out.println(runningSum);

		ArrayList<Integer> reduce = numbers.stream().collect(ArrayList::new,
				(li, n) -> {
					int next = li.isEmpty() ? n : li.get(li.size() - 1) + n;
					li.add(next);
				}, List::addAll);
		System.out.println(reduce);
		System.out.println("========================================");
		// Generate the first N numbers in the Fibonacci sequence. let n=10
		List<Integer> fibonacci = Stream
				.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
				.limit(10).map(t -> t[0]).collect(Collectors.toList());
		System.out.println(fibonacci);
		System.out.println("========================================");
		// Calculate the product of all numbers in a list using reduce.
		Integer product = smallList.stream().reduce(1, (a, b) -> a * b);
		System.out.println(product);
		System.out.println("========================================");
		// Create a sliding window of size 3 for a list of integers.
		List<List<Integer>> slidingwindow3 = IntStream
				.range(0, smallList.size() - 2)
				.mapToObj(i -> smallList.subList(i, i + 3)).toList();
		System.out.println(slidingwindow3);
		System.out.println("========================================");
		// Generate a pyramid of numbers using Streams.
		IntStream.rangeClosed(1, 5)
				.mapToObj(i -> " ".repeat(5 - i)
						+ IntStream.rangeClosed(1, i).mapToObj(String::valueOf)
								.collect(Collectors.joining(" ")))
				.collect(Collectors.toList()).forEach(System.out::println);
		System.out.println("========================================");
		// Given a triangle of numbers, find the maximum path sum using Streams.
		Integer maxPathSum = triangle.stream().reduce((prevRow,
				currRow) -> IntStream.range(0, currRow.size()).mapToObj(i -> {
					int left = i > 0 ? prevRow.get(i - 1) : 0;
					int right = i < prevRow.size() ? prevRow.get(i) : 0;
					return currRow.get(i) + Math.max(left, right);
				}).collect(Collectors.toList())).get().stream()
				.max(Integer::compareTo).orElse(0);
		System.out.println(maxPathSum);

		System.out.println("========================================");
		// Verify if a list is sorted in ascending order.
		boolean isSorted = IntStream.range(0, numbers.size() - 1)
				.allMatch(i -> numbers.get(i) <= numbers.get(i + 1));
		System.out.println(isSorted);
		boolean isSorted1 = IntStream.range(0, values.size() - 1)
				.allMatch(i -> values.get(i) <= values.get(i + 1));
		System.out.println(isSorted1);

		System.out.println("========================================");
		// Generate all contiguous subarrays of a list
		List<List<Integer>> result = IntStream.range(0, values.size()).boxed()
				.flatMap(start -> IntStream.range(start + 1, values.size() + 1)
						.mapToObj(end -> new ArrayList<>(
								values.subList(start, end))))
				.collect(Collectors.toList());
		System.out.println(result);

		System.out.println("========================================");
		// Combine two lists into a map where one is keys and the other values.
		Map<String, Integer> map = IntStream.range(0, keys.size()).boxed()
				.collect(Collectors.toMap(keys::get, values::get));
		System.out.println(map);

		System.out.println("========================================");
		// Find all pairs of numbers from a list that add up to a given sum.
		int targetSum = 6;
		List<List<Integer>> allPairs = smallList.stream()
				.flatMap(a -> smallList.stream()
						.filter(b -> a + b == targetSum && a < b)
						.map(b -> Arrays.asList(a, b)))
				.toList();
		System.out.println(allPairs);

		System.out.println("========================================");
		// Generate a list of random numbers using Stream.
		List<Integer> list5 = new Random().ints(5, 1, 100).boxed().toList();
		System.out.println(list5);

		System.out.println("========================================");
		// Find the longest increasing subsequence in a list.
		List<Integer> numbers = Arrays.asList(10, 9, 2, 5, 3, 7, 101, 18);
		List<Integer> lis = new ArrayList<>();
		numbers.forEach(num -> {
			int pos = Collections.binarySearch(lis, num);
			if (pos < 0)
				pos = -(pos + 1);
			if (pos < lis.size())
				lis.set(pos, num);
			else
				lis.add(num);
		});
		System.out.println(lis);

		System.out.println("========================================");
		// Sort a list based on the frequency of elements in descending order.
		List<Integer> numberss = Arrays.asList(4, 5, 6, 5, 4, 3);
		List<Integer> list6 = numberss.stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
				.map(Map.Entry::getKey)
				//.flatMap(e -> Collections.nCopies(e.getValue().intValue(), e.getKey()).stream())
				.toList();
		System.out.println(list6);
		
		List<Integer> sortedByFrequency = numberss.stream()
			    .sorted(Comparator.comparingInt(n -> - Collections.frequency(numberss, n)))
			    .distinct()
			    .collect(Collectors.toList());
		System.out.println(sortedByFrequency);

	}

	private static List<String> keys = Arrays.asList("A", "B", "C");
	private static List<Integer> values = Arrays.asList(1, 2, 3);
	private static List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5);

	private static List<List<Integer>> triangle = Arrays.asList(
			Arrays.asList(3), Arrays.asList(3, 4), Arrays.asList(6, 5, 7),
			Arrays.asList(4, 1, 8, 3));
}
