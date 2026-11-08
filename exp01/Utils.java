package exp01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

/// Class for creating random arrays of n elements of Comparable type.
public class Utils {
	public static final int NUMBER_RANGE = 100;

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] generateTArray(Class<T> typeClass, int dimension, Supplier<T> supplier, boolean isSorted, boolean isReversed) {
		T[] array = (T[]) Array.newInstance(typeClass, dimension);
	
		for (int i = 0; i < dimension; i++) {
			array[i] = supplier.get();
		}

		if (isReversed) {
			Arrays.sort(array, Collections.reverseOrder());
		} else if (isSorted) {
			Arrays.sort(array);
		}

		return array;
	}
}
