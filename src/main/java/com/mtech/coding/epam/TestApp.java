package com.mtech.coding.epam;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestApp {
	public static void main(String[] args) {
		int[] a = { 10, 15, 20, 25 };
		int n = 1; // change to 1 or 2

		for (int i = 0; i < a.length; i++) {
			int num = a[i];
			while (num >= 10) {
				num /= 10;
			}
			if (num == n) {
				System.out.println(a[i]);
			}
		}

		int[] arr = extracted();
		System.out.println(Arrays.toString(arr));
		int[] arr1 = extracted1();
		System.out.println(Arrays.toString(arr1));
		
		String str = "programming";
		String collect = str.chars().distinct().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining());
		System.out.println(collect);
		
		String s1 = "Java";
		s1.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));

        String s2 = "Software";
        System.out.println("Before swap s1 "+ s1);
        System.out.println("Before swap s2 "+s2);
        s1 = s1 + s2;
        s2 = s1.substring(0, s1.length() - s2.length());
        s1 = s1.substring(s2.length());
        
        System.out.println("After swap s1 "+s1);
        System.out.println("After swap s2 "+s2);
	}

	private static int[] extracted() {
		int[] arr = { 1, 2, 4, 0, 6, 7, 0, 0, 0, 0, 8, 9, 0 };

		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}
		return arr;
	}

	private static int[] extracted1() {
		int[] arr = { 1, 2, 4, 0, 6, 7, 0, 0, 0, 0, 8, 9, 0 };

		int index = arr.length-1;
		for (int i = arr.length-1; i >=0 ; i--) {
			if(arr[i] !=0) {
				arr[index--] = arr[i];
			}
		}
		while(index >= 0) {
			arr[index--] = 0;
		}
		return arr;
	}
}
