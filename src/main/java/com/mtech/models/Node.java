package com.mtech.models;

import java.util.Arrays;
import java.util.List;

public class Node {
	private int id;
	private int parentId;

	Node(int id, int parentId) {
		this.id = id;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public static List<Node> nodes() {
		List<Node> nodes = Arrays.asList(new Node(0, 1), new Node(1, 2),
				new Node(2, 3), new Node(3, 4), new Node(4, 5), new Node(5, 0));
		return nodes;
	}
}