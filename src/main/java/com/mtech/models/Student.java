package com.mtech.models;

import java.util.Arrays;
import java.util.List;

public class Student {
	private String name;
	private int score;
	
	private String department;

	Student(String name, int score, String depatment) {
		this.name = name;
		this.score = score;
		this.department = depatment;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	

	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + ", department="
				+ department + "]";
	}

	public static List<Student> getStudents() {
		List<Student> students = Arrays.asList(new Student("Alice", 85, "CSE"),
				new Student("Bob", 92, "CSE"), new Student("Charlie", 88, "MCA"),
				new Student("Dave", 78, "MCA"), new Student("Eve", 91, "MECH"));

		return students;
	}

}