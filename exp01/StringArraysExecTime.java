package exp01;

import java.util.Random;

/// Test class for the sorting algorithms with Strings arrays.
public final class StringArraysExecTime {
	private static final int STRING_LENGTH = 10; // Length of the strings in the array.
	
	/// Test the performance of the Bubble Inc. sorting algorithms with random, sorted, and reverse sorted String arrays.
	public static void testStringArrayExecTime(int dimension, boolean isSorted, boolean reverseSorted, String orderName) {
		Random random = new Random();
		final int repetitions = 30;

		long[] timesUntilNoChange = new long[repetitions];
		long[] timesPassPerItem = new long[repetitions];
		long[] timesWhileNeeded = new long[repetitions];
		
		for (int i = 0; i < repetitions; i++) {
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
	
			timesUntilNoChange[i] = end - start;
		
			
			// Bubble sort pass per item algorithm.
			array = startingArray.clone();
			BubbleSortPassPerItem<String> bubbleSortPassPerItem  = new BubbleSortPassPerItem<String>();
			
			start = System.nanoTime();
			bubbleSortPassPerItem.sort(array);
			end = System.nanoTime();
	
			timesPassPerItem[i] = end - start;
			
			// Bubble sort while needed algorithm.
			array = startingArray.clone();
			BubbleSortWhileNeeded<String> bubbleSortWhileNeeded  = new BubbleSortWhileNeeded<String>();
			
			start = System.nanoTime();
			bubbleSortWhileNeeded.sort(array);
			end = System.nanoTime();
	
			timesWhileNeeded[i] = end - start;
		}

		for (int rep = 0; rep < repetitions; rep++) {
			String string1 = "UntilNoChange,String," + dimension + "," + orderName + "," + (timesUntilNoChange[rep] / 1_000_000.0) + "\n";
			String string2 = "PassPerItem,String," + dimension + "," + orderName + "," + (timesPassPerItem[rep] / 1_000_000.0) + "\n";
			String string3 = "WhileNeeded,String," + dimension + "," + orderName + "," + (timesWhileNeeded[rep] / 1_000_000.0) + "\n";
			try {
				Utils.saveCSVDataToFile("output.csv", string1);
				Utils.saveCSVDataToFile("output.csv", string2);
				Utils.saveCSVDataToFile("output.csv", string3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void testStringArraysExecTime() {
		final int limit = 100000;

		// Random String arrays.
		System.out.println("Random String arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testStringArrayExecTime(i, false, false, "Random");	
		}

		// Sorted String arrays.
		System.out.println("Sorted String arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testStringArrayExecTime(i, true, false, "Sorted");	
			
		}

		// Reverse Sorted String arrays.
		System.out.println("Reverse Sorted String arrays: ");
		for (int i = 100; i < limit; i *= 10) {
	
			System.out.println("Array size: " + i);
			testStringArrayExecTime(i, true, true, "ReverseSorted");	
		}
	}
}
