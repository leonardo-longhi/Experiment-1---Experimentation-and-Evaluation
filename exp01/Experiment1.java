package exp01;

import java.io.IOException;
import java.util.Random;

/// Class for executing the experiment.
public final class Experiment1 {

	/// Warmup the JVM by sorting an array
	/// with each algorithm without tracing time.
	public final void JVMWarmup() {
		// Sort some array without tracing time (JVM warmup).
		Random random = new Random();

		// Generate random array with the given 'dimension'.
		Integer[] startingArray = Utils.generateTArray(
			Integer.class, 
			1000, 
			() -> random.nextInt(100000), 
			false, 
			false
		);

		Integer[] array = startingArray.clone();
		BubbleSortUntilNoChange<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<Integer>();
		bubbleSortUntilNoChange.sort(array);

		array = startingArray.clone();
		BubbleSortPassPerItem<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<Integer>();
		bubbleSortPassPerItem.sort(array);

		array = startingArray.clone();
		BubbleSortWhileNeeded<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<Integer>();
		bubbleSortWhileNeeded.sort(array);
	}

	/// Main method for executing the experiment.
	/// @param args Command line arguments.
	public static void main(String[] args) {
		Experiment1 experiment = new Experiment1();
		experiment.JVMWarmup();

		try {
			Utils.saveCSVDataToFile("output.csv", "Algorithm,DataType,Dimension,Order,TimeMs\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Integer Arrays. 
		IntegerArraysExecTime.testIntegerArraysExecTime();

		// String Arrays.
		StringArraysExecTime.testStringArraysExecTime();

		// Double Arrays.
		DoubleArraysExecTime.testDoubleArraysExecTime();
	}
}
