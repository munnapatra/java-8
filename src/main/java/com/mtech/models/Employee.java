package com.mtech.models;

import java.util.Arrays;
import java.util.List;

public class Employee {
	private String name;
	private String department;
	private int age;
	private Long salary;

	Employee(String name, String department, int age, Long salary) {
		this.name = name;
		this.department = department;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department
				+ ", age=" + age + ", salary=" + salary + "]";
	}

	public static List<Employee> getEmployees() {
		List<Employee> employees = Arrays.asList(
				new Employee("Alice", "HR", 25, 61000L),
				new Employee("Bob", "IT", 30, 55000L),
				new Employee("Charlie", "HR", 30, 44000L),
				new Employee("Dave", "IT", 25, 35000L),
				new Employee("Jane", "SL", 42, 40000L));
		return employees;
	}

}