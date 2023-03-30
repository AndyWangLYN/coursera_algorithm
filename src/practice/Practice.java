package practice;
import java.net.*;
import java.math.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.io.*;

public class Practice {
	public static long countInversions(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return 0;
        }
        int[] b = new int[n/2];
        int[] c = new int[n - n/2];
        System.arraycopy(a, 0, b, 0, b.length);
        System.arraycopy(a, b.length, c, 0, c.length);

        long x = countInversions(b);
        long y = countInversions(c);
        long z = mergeAndCount(a, b, c);

        return x + y + z;
    }

    public static long mergeAndCount(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        long invCount = 0;

        while (i < b.length && j < c.length) {
            if (b[i] <= c[j]) {
                a[k++] = b[i++];
            } else {
                a[k++] = c[j++];
                invCount += b.length - i;
            }
        }

        while (i < b.length) {
            a[k++] = b[i++];
        }

        while (j < c.length) {
            a[k++] = c[j++];
        }

        return invCount;
    }
	 
	    public static void main(String[] args) {
	    	int[] arr = new int[100000]; // create an array to hold the integers
	    	File file = new File("/Users/heyuwang/eclipse-workspace/practice/src/practice/input.txt");
	        try {
	            Scanner scanner = new Scanner(file); // open the file for reading
	            for (int i = 0; i < 100000; i++) {
	                arr[i] = scanner.nextInt(); // read in the next integer and store it in the array
	            }
	            scanner.close(); // close the file
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	
	        // int[] aux = Arrays.copyOf(arr, arr.length);
	        long inversionCount = countInversions(arr);
	        System.out.println("Inversion count: " + inversionCount);
	    }
	    
}


