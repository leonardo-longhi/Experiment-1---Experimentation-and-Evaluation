package exp01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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

	/// Generate a random string of length n.
	static String getAlphaNumericString(int n) { 

		// Choose a Character random from this String.
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz"; 

		// Create a StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 
			// Generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index 
				= (int)(AlphaNumericString.length() 
				* Math.random());

			// Add Character one by one in end of sb 
			sb.append(AlphaNumericString 
				.charAt(index)); 
		} 

		return sb.toString(); 
	}

	/// Save the given text to a CSV file.
	/// 
	/// @param fileName The name of the CSV file.
	/// @param data The data to be saved in the CSV.
	static void saveCSVDataToFile(String fileName, String data) throws IOException {
		Files.writeString(Path.of(fileName), 
			data,
			StandardOpenOption.CREATE, 
            StandardOpenOption.APPEND
        );
	}
}
