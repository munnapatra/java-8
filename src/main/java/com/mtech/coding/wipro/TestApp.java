package com.mtech.coding.wipro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mtech.models.Employee;
import com.mtech.models.Student;

public class TestApp {
	public static void main(String[] args) {

		// Write a Java 8 stream code to group employees by department and get
		// the highest salary per department
		Employee.getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.maxBy(
								Comparator.comparing(Employee::getSalary))))
				.entrySet().stream().forEach(e -> System.out.println(
						e.getKey() + " - " + e.getValue().get().getSalary()));

		// How can you Remove all occurrences of an element from a List in Java
		List<Integer> numbers = new ArrayList<>(
				Arrays.asList(2, 4, 5, 6, 2, 3, 7, 8, 9));
		int element = 2;
		List<Integer> list = numbers.stream().filter(n -> !n.equals(element))
				.toList();
		System.out.println(list);

		// How can you find a missing number in list ?
		List<Integer> listOfNumber = new ArrayList<>(
				Arrays.asList(1, 2, 5, 6, 3, 7, 8, 9));
		int size = listOfNumber.size(), actualSize = size + 1;
		Integer sum = listOfNumber.stream().reduce(0, Integer::sum);
		Integer actualSum = (actualSize * (actualSize + 1)) / 2;
		System.out.println("Mising number is: " + (actualSum - sum));

		// Find the average marks of students for each department
		Map<String, Double> collect = Student.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getDepartment,
						Collectors.averagingDouble(Student::getScore)));
		System.out.println(collect);

		// List of employees working in HR Department
		List<Employee> list2 = Employee.getEmployees().stream()
				.filter(e -> e.getDepartment().equals("HR")).toList();
		System.out.println(list2);

		// Get unique numbers from the list of integers using Streams
		List<Integer> numb = new ArrayList<>(
				Arrays.asList(2, 4, 5, 6, 2, 3, 7, 8, 9, 4, 9));

		List<Integer> uniqueNumbers = numb.stream()
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1)
				.map(Map.Entry::getKey).toList();
		System.out.println(uniqueNumbers);

		// Write a Java function to check if a string is a palindrome
		System.out.println(isPalindrome("madam"));

		// Given two strings, determine if one is an anagram of the other
		System.out.println(isAnagramWithSorting("listen", "silent"));
		System.out.println(isAnagram("listen", "silent"));

		// write a program to find duplicate characters in a given string
		String input = "success";
		List<Character> duplicateCharacters = input.chars()
				.mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() > 1)
				.map(Map.Entry::getKey).toList();
		System.out.println(duplicateCharacters);

		// Remove special characters, spaces and numbers and then print the
		// count of each alphabet
		String str = "aabcx1 @cd";
		Map<Character, Long> collect2 = str.chars().mapToObj(ch -> (char) ch)
				.filter(e -> Character.isAlphabetic(e))
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		System.out.println(collect2);

		// Change the case of all charachters and print the output.
		String abc = "mY nAme iS mUnEeB";
		char[] arr = abc.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (Character.isUpperCase(arr[i])) {
				arr[i] = Character.toLowerCase(arr[i]);
			} else if (Character.isLowerCase(arr[i])) {
				arr[i] = Character.toUpperCase(arr[i]);
			}
		}

		System.out.println(new String(arr));

		// Given a string you have to count number of pallindromes and print
		// them
		String statement = "Hello madam how are you, do you like racecar in radar";
		System.out.println(countWordPalindromes(statement));

		// Given an array and you have to print it in sorted order using Java 8
		// and also print the count of each number
		List<Integer> numbe = new ArrayList<>(
				Arrays.asList(2, 4, 5, 6, 2, 3, 7, 8, 9, 4, 9));
		Map<Integer, Long> collect3 = numbe.stream().sorted().collect(Collectors
				.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(collect3);
		
		//Remove duplicate from the list of string
		List<String> strings = new ArrayList<>(Arrays.asList("apple", "mango", "banana", "grape", "mango", "apple"));
		LinkedHashSet<String> collect4 = strings.stream().collect(Collectors.toCollection(LinkedHashSet::new));
		System.out.println(collect4);
		System.out.println(strings.stream().distinct().toList());
	}

	public static boolean isPalinDrome(String str) {
		if (str.length() <= 1) {
			return true;
		}

		char[] charArray = str.toCharArray();
		for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
			char temp = charArray[i];
			charArray[i] = charArray[j];
			charArray[j] = temp;
		}

		String reversed = new String(charArray);
		return str.equals(reversed);
	}

	public static boolean isPalindrome(String str) {
		if (str == null)
			return false;
		return IntStream.range(0, str.length() / 2).allMatch(
				i -> str.charAt(i) == str.charAt(str.length() - 1 - i));
	}

	public static boolean isAnagram(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;

		int[] freq = new int[26]; // assuming lowercase a-z

		for (int i = 0; i < s1.length(); i++) {
			freq[s1.charAt(i) - 'a']++;
			freq[s2.charAt(i) - 'a']--;
		}

		for (int count : freq) {
			if (count != 0)
				return false;
		}

		return true;
	}

	public static boolean isAnagramWithSorting(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;

		char[] a1 = s1.toCharArray();
		char[] a2 = s2.toCharArray();

		Arrays.sort(a1);
		Arrays.sort(a2);

		return Arrays.equals(a1, a2);
	}

	public static long countWordPalindromes(String str) {

		return Arrays.stream(str.split("[^a-zA-Z]+")) // remove punctuation
				.filter(word -> word.length() > 1)
				.filter(word -> isPalindrome(word.toLowerCase())).count();
	}

}
