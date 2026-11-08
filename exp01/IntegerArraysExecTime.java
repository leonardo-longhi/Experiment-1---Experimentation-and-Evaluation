package exp01;

import java.util.Random;

/// Test class for the sorting algorithms with Integers arrays.
public final class IntegerArraysExecTime {
	private static final int NUMBER_RANGE = 1000000; // Range of numbers in the array.
	
	/// Test the performance of the Bubble Inc. sorting algorithms with random, sorted, and reverse sorted Integer arrays.
	public static void testIntegerArrayExecTime(int dimension, boolean isSorted, boolean reverseSorted, String orderName) {
		Random random = new Random();
		final int repetitions = 30;

		long[] timesUntilNoChange = new long[repetitions];
		long[] timesPassPerItem = new long[repetitions];
		long[] timesWhileNeeded = new long[repetitions];
		
		for (int i = 0; i < repetitions; i++) {
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
	
			timesUntilNoChange[i] = end - start;
		
			
			// Bubble sort pass per item algorithm.
			array = startingArray.clone();
			BubbleSortPassPerItem<Integer> bubbleSortPassPerItem  = new BubbleSortPassPerItem<Integer>();
			
			start = System.nanoTime();
			bubbleSortPassPerItem.sort(array);
			end = System.nanoTime();
	
			timesPassPerItem[i] = end - start;
			
			// Bubble sort while needed algorithm.
			array = startingArray.clone();
			BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded  = new BubbleSortWhileNeeded<Integer>();
			
			start = System.nanoTime();
			bubbleSortWhileNeeded.sort(array);
			end = System.nanoTime();
	
			timesWhileNeeded[i] = end - start;
		}

		for (int rep = 0; rep < repetitions; rep++) {
			String string1 = "UntilNoChange,Integer," + dimension + "," + orderName + "," + (timesUntilNoChange[rep] / 1_000_000.0) + "\n";
			String string2 = "PassPerItem,Integer," + dimension + "," + orderName + "," + (timesPassPerItem[rep] / 1_000_000.0) + "\n";
			String string3 = "WhileNeeded,Integer," + dimension + "," + orderName + "," + (timesWhileNeeded[rep] / 1_000_000.0) + "\n";
			try {
				Utils.saveCSVDataToFile("output.csv", string1);
				Utils.saveCSVDataToFile("output.csv", string2);
				Utils.saveCSVDataToFile("output.csv", string3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void testIntegerArraysExecTime() {
		final int limit = 100000;

		// Random Integer arrays.
		System.out.println("Random Integer arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testIntegerArrayExecTime(i, false, false, "Random");	
		}

		// Sorted Integer arrays.
		System.out.println("Sorted Integer arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testIntegerArrayExecTime(i, true, false, "Sorted");	
			
		}

		// Reverse Sorted Integer arrays.
		System.out.println("Reverse Sorted Integer arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testIntegerArrayExecTime(i, true, true, "ReverseSorted");	
		}
	}
}
