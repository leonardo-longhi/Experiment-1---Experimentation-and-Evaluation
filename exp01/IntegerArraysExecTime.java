package exp01;

import java.util.Random;

/// Test class for the sorting algorithms with Integers arrays.
public final class IntegerArraysExecTime {
	private static final int NUMBER_RANGE = 1000000; // Range of numbers in the array.
	
	/// Test the performance of the Bubble Inc. sorting algorithms with random, sorted, and reverse sorted Integer arrays.
	public static void testIntegerArrayExecTime(int dimension, boolean isSorted, boolean reverseSorted) {
		Random random = new Random();
		
		// Generate random array with the given 'dimension'.
		Integer[] startingArray = Utils.generateTArray(
			Integer.class, 
			dimension, 
			() -> random.nextInt(NUMBER_RANGE), 
			isSorted, 
			reverseSorted
		);
		
		// Bubble sort until no change algorithm.
		Integer[] array = startingArray.clone();
		BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<Integer>();
		long start = System.nanoTime();

		bubbleSortUntilNoChange.sort(array);
		
		long end = System.nanoTime();

		long time = end - start;
		System.out.println("Bubble sort until no change: " + ((time) / 1_000_000) + "ms");
		
		// Bubble sort pass per item algorithm.
		array = startingArray.clone();
		BubbleSortPassPerItem<Integer> bubbleSortPassPerItem  = new BubbleSortPassPerItem<Integer>();
		start = System.nanoTime();

		bubbleSortPassPerItem.sort(array);

		end = System.nanoTime();

		time = end - start;
		System.out.println("Bubble sort pass per item: " + ((time) / 1_000_000) + "ms");

		// Bubble sort while needed algorithm.
		array = startingArray.clone();
		BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded  = new BubbleSortWhileNeeded<Integer>();
		start = System.nanoTime();

		bubbleSortWhileNeeded.sort(array);

		end = System.nanoTime();

		time = end - start;
		System.out.println("Bubble sort while needed: " + ((time) / 1_000_000) + "ms");
	}

	public static void testIntegerArraysExecTime() {

		// Random Integer arrays.
		System.out.println("Random Integer arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testIntegerArrayExecTime(i, false, false);	
		}

		// Sorted Integer arrays.
		System.out.println("Sorted Integer arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testIntegerArrayExecTime(i, true, false);
			
		}

		// Reverse Sorted Integer arrays.
		System.out.println("Reverse Sorted Integer arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testIntegerArrayExecTime(i, true, true);
		}
	}
}
