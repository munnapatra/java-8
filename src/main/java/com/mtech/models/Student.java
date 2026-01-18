package com.mtech.models;

import java.util.Arrays;
import java.util.List;

public class Student {
	private String name;
	private int score;

	Student(String name, int score) {
		this.name = name;
		this.score = score;
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

	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	}

	public static List<Student> getStudents() {
		List<Student> students = Arrays.asList(new Student("Alice", 85),
				new Student("Bob", 92), new Student("Charlie", 88),
				new Student("Dave", 78), new Student("Eve", 91));

		return students;
	}

}