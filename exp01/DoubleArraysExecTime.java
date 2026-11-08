package exp01;

import java.util.Random;

/// Test class for the sorting algorithms with Double arrays.
public final class DoubleArraysExecTime {
	private static final double NUMBER_RANGE = 1000000; // Range of numbers in the array.
	
	/// Test the performance of the Bubble Inc. sorting algorithms with random, sorted, and reverse sorted Double arrays.
	public static void testDoubleArrayExecTime(int dimension, boolean isSorted, boolean reverseSorted) {
		Random random = new Random();
		
		// Generate random array with the given 'dimension'.
		Double[] startingArray = Utils.generateTArray(
			Double.class, 
			dimension, 
			() -> random.nextDouble(NUMBER_RANGE), 
			isSorted, 
			reverseSorted
		);
		
		// Bubble sort until no change algorithm.
		Double[] array = startingArray.clone();
		BubbleSortUntilNoChange<Double> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<Double>();
		long start = System.nanoTime();

		bubbleSortUntilNoChange.sort(array);
		
		long end = System.nanoTime();

		long time = end - start;
		System.out.println("Bubble sort until no change: " + ((time) / 1_000_000) + "ms");
		
		// Bubble sort pass per item algorithm.
		array = startingArray.clone();
		BubbleSortPassPerItem<Double> bubbleSortPassPerItem  = new BubbleSortPassPerItem<Double>();
		start = System.nanoTime();

		bubbleSortPassPerItem.sort(array);

		end = System.nanoTime();

		time = end - start;
		System.out.println("Bubble sort pass per item: " + ((time) / 1_000_000) + "ms");

		// Bubble sort while needed algorithm.
		array = startingArray.clone();
		BubbleSortWhileNeeded<Double> bubbleSortWhileNeeded  = new BubbleSortWhileNeeded<Double>();
		start = System.nanoTime();

		bubbleSortWhileNeeded.sort(array);

		end = System.nanoTime();

		time = end - start;
		System.out.println("Bubble sort while needed: " + ((time) / 1_000_000) + "ms");
	}

	public static void testDoubleArraysExecTime() {

		// Random Double arrays.
		System.out.println("Random Double arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testDoubleArrayExecTime(i, false, false);	
		}

		// Sorted Double arrays.
		System.out.println("Sorted Double arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testDoubleArrayExecTime(i, true, false);
			
		}

		// Reverse Sorted Double arrays.
		System.out.println("Reverse Sorted Double arrays: ");
		for (int i = 100; i < 1000000; i *= 10) {
	
			System.out.println("Array size: " + i);
			testDoubleArrayExecTime(i, true, true);
		}
	}
}
