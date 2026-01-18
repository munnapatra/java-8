package com.mtech.stream.coding;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringSteamCoding {

	private static List<String> names = Arrays.asList("Venugopal", "Alice",
			"Bob", "Annie", "Alex", "radar", "level");
	private static List<String> words = Arrays.asList("Java", null, "Stream",
			null, "API");
	private static String input = "success";

	public static void main(String[] args) {
		System.out.println("==========================================");
		// Count strings starting with a specific prefix, e.g., “A”.
		long count = names.stream().filter(w -> w.startsWith("A")).count();
		System.out.println("Count of strings starting with A: " + count);

		System.out.println("==========================================");
		// 6. Convert all strings in a list to upper case.
		List<String> upperCaseList = names.stream().map(String::toUpperCase)
				.toList();
		System.out.println("Converted to uppercase: " + upperCaseList);

		System.out.println("==========================================");
		// 8. Check if any string in a list contains “An”.
		boolean anyMatch = names.stream().anyMatch(w -> w.contains("An"));
		System.out.println("Any match with word An: " + anyMatch);

		System.out.println("==========================================");
		// 10. Group a list of strings based on their length.
		Map<Integer, List<String>> groupedString = names.stream()
				.collect(Collectors.groupingBy(String::length));
		System.out.println(
				"Grouped string based on their length: " + groupedString);

		System.out.println("==========================================");
		// Concatenate all strings in a list into a single string.
		String concat = names.stream().reduce("", (x, y) -> x + " " + y).trim();
		System.out.println("Concating all string: " + concat);

		System.out.println("==========================================");
		// Find the longest string in a list.
		String longString = names.stream()
				.reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2).get();
		System.out.println("Longest string: " + longString);

		System.out.println("==========================================");
		// 5. Find the first non-repeated character in a string.
		Character key = input.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1).findFirst()
				.get().getKey();
		System.out.println("First non repeating charachter: " + key);

		// Find all non-repeating characters in a string.
		List<Character> allNonRepeatingChar = input.chars()
				.mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1)
				.map(Map.Entry::getKey).toList();
		System.out.println("All non repeating chars: " + allNonRepeatingChar);

		List<Character> nonRepeating = input.chars().mapToObj(c -> (char) c)
				.filter(c -> input.chars().filter(ch -> ch == c).count() == 1)
				.toList();

		System.out.println("All non repeating chars: " + nonRepeating);

		System.out.println("==========================================");
		// Count the frequency of each character in a string.
		Map<Character, Long> frequency = input.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		System.out.println("Frequency of eac charachter: " + frequency);

		System.out.println("==========================================");
		// Create a custom collector to join strings with a delimiter.
		String toSingleStr = names.stream().collect(Collectors.joining(", "));
		System.out.println("Join strings: " + toSingleStr);

		System.out.println("==========================================");
		// Remove null values from a list using filter.
		List<String> nonNullValues = words.stream().filter(Objects::nonNull)
				.toList();
		System.out.println("Not null values: " + nonNullValues);

		System.out.println("==========================================");
		// Convert a list of strings into a map with the string as the key and
		// its
		// length as the value.
		Map<String, Integer> collect = names.stream()
				.collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println("Map of string with its length: " + collect);

		System.out.println("==========================================");
		// Find all strings that are palindromes in a list.
		List<String> palindromes = names.stream()
				.filter(w -> w.equals(new StringBuffer(w).reverse().toString()))
				.toList();
		System.out.println("Palindromes strings: " + palindromes);

		System.out.println("==========================================");
		// Reverse each string in a list.
		List<String> reversedString = names.stream()
				.map(w -> new StringBuffer(w).reverse().toString()).toList();
		System.out.println("Reversed strings: " + reversedString);

		System.out.println("==========================================");
		// Given a map, filter entries with values greater than 10 and collect
		// keys into
		// a list.
		Map<String, Integer> map = Map.of("A", 5, "B", 15, "C", 10, "D", 20);
		List<String> values = map.entrySet().stream()
				.filter(e -> e.getValue() > 10).collect(Collectors
						.mapping(Map.Entry::getKey, Collectors.toList()));
		System.out.println("Strings greater that value 10: " + values);
		List<String> list = map.entrySet().stream()
				.filter(e -> e.getValue() > 10).map(Map.Entry::getKey).toList();
		System.out.println("Strings greater that value 10: " + list);
		System.out.println("==========================================");
		// Convert a map to a list of “key=value” strings.
		List<String> maptToList = map.entrySet().stream()
				.map(e -> e.getKey() + "=" + e.getValue()).toList();
		System.out.println("List of “key=value”: " + maptToList);

		System.out.println("==========================================");
		// Find the most frequent character in a string.
		Entry<Character, Long> maxFreq1 = input.chars()
				.mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream()
				.collect(Collectors.maxBy((Map.Entry.comparingByValue())))
				.get();
		System.out.println("Max frequency char: " + maxFreq1);
		Character maxFreq2 = input.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey).get();
		System.out.println("Max frequency char: " + maxFreq2);

		System.out.println("==========================================");
		// Partition strings into palindromes and non-palindromes.
		Map<Boolean, List<String>> collect2 = names.stream()
				.collect(Collectors.partitioningBy(w -> w.equalsIgnoreCase(
						new StringBuffer(w).reverse().toString())));
		System.out.println(collect2);

		System.out.println("==========================================");
		// Find the longest word in a sentence using Streams
		String max = Arrays.stream(sentence.split(" "))
				.max(Comparator.comparing(String::length)).get();
		System.out.println("Longest string: " + max);

		System.out.println("==========================================");
		// Group a list of strings by their first character.
		Map<Character, List<String>> groupByChar = names.stream()
				.collect(Collectors.groupingBy(w -> w.charAt(0)));
		System.out.println(groupByChar);

		System.out.println("==========================================");
		// Concatenate strings in reverse order using reduce.
		Optional<String> reverOrder = names.stream()
				.reduce((w1, w2) -> w2 + " " + w1);
		System.out.println(reverOrder.get());

		System.out.println("==========================================");
		// Find the word with the most vowels in a list.
		String strWithMaxVowels = names.stream()
				.max(Comparator.comparingInt(word -> (int) word.toLowerCase()
						.chars().filter(c -> VOWELS.contains((char) c))
						.count()))
				.orElse(null);
		System.out.println(strWithMaxVowels);

		System.out.println("==========================================");
		// Group words by their length and sort each group alphabetically.
		Map<Integer, List<String>> lengthSortedAplha = names.stream()
				.collect(Collectors.groupingBy(String::length,
						Collectors.collectingAndThen(Collectors.toList(),
								lst -> lst.stream().sorted().toList())));
		System.out.println(lengthSortedAplha);

		System.out.println("==========================================");
		// Detect all anagrams in a list of strings.
		Map<String, List<String>> anagramsList = wordsForAnagrams.stream()
				.collect(Collectors.groupingBy(word -> word.chars().sorted()
						.mapToObj(c -> String.valueOf((char) c))
						.collect(Collectors.joining())));

		Map<String, List<String>> anagramsList1 = wordsForAnagrams.stream()
				.collect(Collectors.groupingBy(w -> {
					char[] c = w.toCharArray();
					Arrays.sort(c);
					return new String(c);
				}));
		System.out.println(anagramsList);
		System.out.println(anagramsList1);

		System.out.println("==========================================");
		// Generate all subsequences of a string.
		String str = "abc";
		List<String> subsequences = IntStream.range(0, 1 << str.length())
				.mapToObj(i -> IntStream.range(0, str.length())
						.filter(j -> (i & (1 << j)) != 0)
						.mapToObj(j -> String.valueOf(str.charAt(j)))
						.collect(Collectors.joining()))
				.collect(Collectors.toList());
		System.out.println(subsequences);
	}

	private static List<String> wordsForAnagrams = Arrays.asList("listen",
			"silent", "enlist", "google", "elbow", "below");
	private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o',
			'u');
	private static String sentence = "Java Stream API is very powerful";
}
