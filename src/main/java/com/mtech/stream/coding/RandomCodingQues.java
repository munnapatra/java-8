package com.mtech.stream.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mtech.models.Employee;
import com.mtech.models.Student;

public class RandomCodingQues {
	public static void main(String[] args) {

		String input = "Aut$oma$t;ion";
		char[] charArray = input.toCharArray();
		for (int i = 0, j = charArray.length - 1; i < j; i++, j--) {
			if (Character.isAlphabetic(charArray[i])) {

				char temp = charArray[j];
				charArray[j] = charArray[i];
				charArray[i] = temp;
			}
		}

		System.out.println(new String(charArray));

		// Write a Java 8 stream code to group employees by department and get
		// the highest salary per department
		Employee.getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.maxBy(
								Comparator.comparing(Employee::getSalary))))
				.entrySet().stream().forEach(x -> {
					System.out.println(x.getKey() + "-"
							+ x.getValue().get().getName() + " has salary "
							+ x.getValue().get().getSalary());
				});

		// How can you Remove all occurrences of an element from a List in Java
		List<Integer> number = new ArrayList<Integer>();
		number.add(3);
		number.add(2);
		number.add(4);
		number.add(5);
		number.add(6);
		number.add(3);

		List<Integer> list = number.stream().filter(n -> !n.equals(3)).toList();
		System.out.println(list);

		Iterator<Integer> iterator = number.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(3)) {
				iterator.remove();
			}
		}
		// number.removeIf(n -> n.equals(3));
		System.out.println(number);

		// How can you find a missing number in list ? Please write a program
		int[] arr = {1, 2, 4, 5, 6}; // Missing 3
		int n = arr.length + 1;

		int expectedSum = n * (n + 1) / 2;
		int actualSum = 0;

		for (int num : arr) {
			actualSum += num;
		}

		System.out.println("Missing number: " + (expectedSum - actualSum));

		// Find the average marks of students for each department
		Map<String, Double> collect = Student.getStudents().stream()
				.collect(Collectors.groupingBy(Student::getDepartment,
						Collectors.averagingDouble(Student::getScore)));
		System.out.println(collect);

		// List of employees working in HR Department
		List<Employee> list2 = Employee.getEmployees().stream()
				.filter(e -> e.getDepartment().equals("HR")).toList();
		System.out.println(list2);

		// Get unique numbers from the list of integers using Streams.

		List<Integer> integers = List.of(1, 3, 4, 5, 2, 3, 5, 7, 8, 9, 23, 9);
		List<Integer> uniqueNumber = integers.stream()
				.collect(Collectors.groupingBy(Function.identity(),
						Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1)
				.map(e -> e.getKey()).toList();
		System.out.println(uniqueNumber);

		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));

		ListIterator<Integer> iterator1 = list1.listIterator();

		while (iterator1.hasNext()) {
			Integer val = iterator1.next();
			if (val == 2) {
				iterator1.add(7); // Safe add
			}
		}

		System.out.println(list1);

		Map<String,Double> collect2 = Employee.getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(collect2);

	}

}
