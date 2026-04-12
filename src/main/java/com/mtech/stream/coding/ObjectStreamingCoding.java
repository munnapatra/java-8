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
import com.mtech.models.Transaction;

public class ObjectStreamingCoding {
	public static void main(String[] args) {
		
		String st1 = "munna"; // constant pool munna
		st1.concat(" patra"); // constant pool munna patra but not assigned to any variable
		System.out.println(st1); //munna becuase st1 still pointing
		// From a list of students, find the top 3 by score.
		List<Student> top3Student = Student.getStudents().stream()
				.sorted(Comparator.comparing(Student::getScore).reversed())
				.limit(3).toList();
		System.out.println(top3Student);

		// 2nd highest salary
		 Long orElse = Employee.getEmployees().stream()
				.map(Employee::getSalary)
				.sorted(Comparator.reverseOrder())
				.distinct()
				.skip(1).findFirst().orElse(null);
		System.out.println("second high salary "+ orElse);
		
		// 2nd highest salary employee
	 Optional<Employee> flatMap = Employee.getEmployees().stream().map(Employee::getSalary).distinct()
				.sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.flatMap(sal -> Employee.getEmployees().stream().filter(e -> e.getSalary().equals(sal)).findFirst());
		System.out.println("second high salary emp "+ flatMap);

		List<Employee> list2 = Employee.getEmployees().stream()
				.sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getAge)).toList();
		System.out.println("Emp list "+ list2);

		// Group employees by department and then by age.
		Map<String, Map<Integer, List<String>>> collect = Employee
				.getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.groupingBy(Employee::getAge,
								Collectors.mapping(Employee::getName,
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
		
		Double average = Employee.getEmployees().stream().mapToDouble(Employee::getSalary).average().orElse(0);
		System.out.println("average "+average);
		
		Map<String,List<Employee>> collect3 = Employee.getEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
		collect3.entrySet().forEach(e -> {
			System.out.println(e.getKey()+"="+e.getValue().stream().map(Employee::getName).toList());
		});

		// From a list of employees, find the highest-paid employee in each department
		Map<String, Optional<Employee>> highestPaidEmpByDep = Employee
				.getEmployees().stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
		System.out.println(highestPaidEmpByDep);

		// Detect cycles in a list of parent-child relationships.
		Map<Integer, List<Integer>> graph = Node.nodes().stream()
				.collect(Collectors.groupingBy(Node::getParentId,
						Collectors.mapping(Node::getId, Collectors.toList())));
		boolean cycleExists = graph.keySet().stream()
				.anyMatch(node -> hasCycle(node, graph, new HashSet<>(),
						new HashSet<>()));
		System.out.println(cycleExists);

		// Implement an Employee class and sort employees by salary, then name.
		List<Employee> list = Employee.getEmployees().stream()
				.sorted(Comparator.comparing(Employee::getSalary)
						.thenComparing(Employee::getName))
				.toList();
		System.out.println(list);

		// Given a list of transactions, calculate total by user ID using streams.
		Map<Long, Double> collect2 = Transaction.getTransactions().stream()
				.collect(Collectors.groupingBy(Transaction::getUserId,
						Collectors.summingDouble(Transaction::getAmount)));
		System.out.println(collect2);

		Transaction.getTransactions().stream().collect(Collectors.toMap(
				Transaction::getUserId, Transaction::getAmount, Double::sum));
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
