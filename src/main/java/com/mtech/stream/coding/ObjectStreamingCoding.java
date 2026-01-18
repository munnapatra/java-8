package com.mtech.stream.coding;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.mtech.models.Employee;
import com.mtech.models.Node;
import com.mtech.models.Student;

public class ObjectStreamingCoding {
	public static void main(String[] args) {

		// From a list of students, find the top 3 by score.
		List<Student> top3Student = Student.getStudents().stream()
				.sorted(Comparator.comparing(Student::getScore).reversed())
				.limit(3).toList();
		System.out.println(top3Student);

		// Group employees by department and then by age.
		Map<String, Map<Integer, List<String>>> collect = Employee
				.getEmployees().stream()
				.collect(Collectors.groupingBy(e -> e.getDepartment(),
						Collectors.groupingBy(e -> e.getAge(),
								Collectors.mapping(e -> e.getName(),
										Collectors.toList()))));
		System.out.println(collect);

		// Find the employee with max salary
		Employee employee = Employee.getEmployees().stream()
				.sorted(Comparator.comparing(Employee::getSalary).reversed())
				.findFirst().get();
		System.out.println(employee);

		Optional<Employee> max = Employee.getEmployees().stream()
				.max(Comparator.comparingLong(Employee::getSalary));
		System.out.println(max.get());

		// From a list of employees, find the highest-paid employee in each
		// department
		Map<String, Optional<Employee>> highestPaidEmpByDep = Employee
				.getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.maxBy(
								Comparator.comparing(Employee::getSalary))));
		System.out.println(highestPaidEmpByDep);

		// Detect cycles in a list of parent-child relationships.
		Map<Integer, List<Integer>> graph = Node.nodes().stream()
				.collect(Collectors.groupingBy(Node::getParentId,
						Collectors.mapping(Node::getId, Collectors.toList())));
		boolean cycleExists = graph.keySet().stream()
				.anyMatch(node -> hasCycle(node, graph, new HashSet<>(),
						new HashSet<>()));
		System.out.println(cycleExists);
	}

	public static boolean hasCycle(Integer node,
			Map<Integer, List<Integer>> graph, Set<Integer> visited,
			Set<Integer> recursionStack) {
		if (recursionStack.contains(node)) {
			return true; // cycle detected
		}
		if (visited.contains(node)) {
			return false;
		}

		visited.add(node);
		recursionStack.add(node);

		for (Integer child : graph.getOrDefault(node, List.of())) {
			if (hasCycle(child, graph, visited, recursionStack)) {
				return true;
			}
		}

		recursionStack.remove(node);
		return false;
	}
}
