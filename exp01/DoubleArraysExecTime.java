package exp01;

import java.util.Random;

/// Test class for the sorting algorithms with Doubles arrays.
public final class DoubleArraysExecTime {
	private static final int NUMBER_RANGE = 1000000; // Range of numbers in the array.
	
	/// Test the performance of the Bubble Inc. sorting algorithms 
	/// with random, sorted, and reverse sorted Double arrays.
	///
	/// @param dimension The number of elements in the array.
	/// @param isSorted The array is sorted.
	/// @param reverseSorted The array is reverse sorted.
	/// @param orderName The name of the order of the array: "Random", "Sorted", or "ReverseSorted".
	public static void testDoubleArrayExecTime(int dimension, boolean isSorted, boolean reverseSorted, String orderName) {
		Random random = new Random();
		final int repetitions = 30;

		long[] timesUntilNoChange = new long[repetitions];
		long[] timesPassPerItem = new long[repetitions];
		long[] timesWhileNeeded = new long[repetitions];
		
		for (int i = 0; i < repetitions; i++) {
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
	
			timesUntilNoChange[i] = end - start;
		
			
			// Bubble sort pass per item algorithm.
			array = startingArray.clone();
			BubbleSortPassPerItem<Double> bubbleSortPassPerItem  = new BubbleSortPassPerItem<Double>();
			
			start = System.nanoTime();
			bubbleSortPassPerItem.sort(array);
			end = System.nanoTime();
	
			timesPassPerItem[i] = end - start;
			
			// Bubble sort while needed algorithm.
			array = startingArray.clone();
			BubbleSortWhileNeeded<Double> bubbleSortWhileNeeded  = new BubbleSortWhileNeeded<Double>();
			
			start = System.nanoTime();
			bubbleSortWhileNeeded.sort(array);
			end = System.nanoTime();
	
			timesWhileNeeded[i] = end - start;
		}

		for (int rep = 0; rep < repetitions; rep++) {
			String string1 = "UntilNoChange,Double," + dimension + "," + orderName + "," + (timesUntilNoChange[rep] / 1_000_000.0) + "\n";
			String string2 = "PassPerItem,Double," + dimension + "," + orderName + "," + (timesPassPerItem[rep] / 1_000_000.0) + "\n";
			String string3 = "WhileNeeded,Double," + dimension + "," + orderName + "," + (timesWhileNeeded[rep] / 1_000_000.0) + "\n";
			try {
				Utils.saveCSVDataToFile("output.csv", string1);
				Utils.saveCSVDataToFile("output.csv", string2);
				Utils.saveCSVDataToFile("output.csv", string3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/// Calculate the time taken by the Bubble Inc. sorting algorithms 
	/// with random, sorted, and reverse sorted Double arrays
	/// of different sizes.
	public static void testDoubleArraysExecTime() {
		final int limit = 100000;

		// Random Double arrays.
		System.out.println("Random Double arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testDoubleArrayExecTime(i, false, false, "Random");	
		}

		// Sorted Double arrays.
		System.out.println("Sorted Double arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testDoubleArrayExecTime(i, true, false, "Sorted");	
		}

		// Reverse Sorted Double arrays.
		System.out.println("Reverse Sorted Double arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testDoubleArrayExecTime(i, true, true, "ReverseSorted");	
		}
	}
}
