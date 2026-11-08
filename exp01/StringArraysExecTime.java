package exp01;

import java.util.Random;

/// Test class for the sorting algorithms with Strings arrays.
public final class StringArraysExecTime {
	private static final int STRING_LENGTH = 1000000; // Range of numbers in the array.
	
	/// Test the performance of the Bubble Inc. sorting algorithms with random, sorted, and reverse sorted String arrays.
	public static void testStringArrayExecTime(int dimension, boolean isSorted, boolean reverseSorted) {
		// Generate random array with the given 'dimension'.
		String[] startingArray = Utils.generateTArray(
			String.class, 
			dimension, 
			() -> Utils.getAlphaNumericString(STRING_LENGTH),
			isSorted, 
			reverseSorted
		);
		
		// Bubble sort until no change algorithm.
		String[] array = startingArray.clone();
		BubbleSortUntilNoChange<String> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<String>();
		long start = System.nanoTime();

		bubbleSortUntilNoChange.sort(array);
		
		long end = System.nanoTime();

		long time = end - start;
		System.out.println("Bubble sort until no change: " + ((time) / 1_000_000) + "ms");
		
		// Bubble sort pass per item algorithm.
		array = startingArray.clone();
		BubbleSortPassPerItem<String> bubbleSortPassPerItem  = new BubbleSortPassPerItem<String>();
		start = System.nanoTime();

		bubbleSortPassPerItem.sort(array);

		end = System.nanoTime();

		time = end - start;
		System.out.println("Bubble sort pass per item: " + ((time) / 1_000_000) + "ms");

		// Bubble sort while needed algorithm.
		array = startingArray.clone();
		BubbleSortWhileNeeded<String> bubbleSortWhileNeeded  = new BubbleSortWhileNeeded<String>();
		start = System.nanoTime();

		bubbleSortWhileNeeded.sort(array);

		end = System.nanoTime();

		time = end - start;
		System.out.println("Bubble sort while needed: " + ((time) / 1_000_000) + "ms");
	}

	public static void testStringArraysExecTime() {

		// Random String arrays.
		System.out.println("Random String arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testStringArrayExecTime(i, false, false);	
		}

		// Sorted String arrays.
		System.out.println("Sorted String arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testStringArrayExecTime(i, true, false);
		}

		// Reverse Sorted String arrays.
		System.out.println("Reverse Sorted String arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testStringArrayExecTime(i, true, true);
		}
	}
}
