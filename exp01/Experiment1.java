package exp01;

import java.util.Random;

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

	void main() {
		JVMWarmup();

		// Integer Arrays. 
		IntegerArraysExecTime.testIntegerArraysExecTime();

		// String Arrays.
		StringArraysExecTime.testStringArraysExecTime();

		// Double Arrays.
		DoubleArraysExecTime.testDoubleArraysExecTime();
	}
}
