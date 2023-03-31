package practice;

import java.io.*;
import java.util.*;

public class quickSort {
    
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "QuickSort.txt";
        String[] pivotList = {"first", "middle", "last"};
        
        for (String pivotID : pivotList) {
            int[] A = loadData(fileName);
            int n = quickSort(A, 0, A.length-1, pivotID);
            System.out.println("number of comparisons: " + n);
        }
    }
    
    public static int[] loadData(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        List<Integer> lines = new ArrayList<Integer>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            lines.add(Integer.parseInt(line));
        }
        sc.close();
        
        int[] A = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            A[i] = lines.get(i);
        }
        return A;
    }
    
    public static void choosePivot(int[] alist, int first, int last, String pivotID) {
        if (pivotID.equals("first")) {
            // do nothing
        } else if (pivotID.equals("last")) {
            int temp = alist[first];
            alist[first] = alist[last];
            alist[last] = temp;
        } else if (pivotID.equals("middle")) {
            int mid = (last-first)/2 + first;
            int[] listTemp = {alist[first], alist[last], alist[mid]};
            Arrays.sort(listTemp);
            int pivotIndex;
            if (listTemp[1] == alist[first]) {
                pivotIndex = first;
            } else if (listTemp[1] == alist[last]) {
                pivotIndex = last;
            } else {
                pivotIndex = mid;
            }
            int temp = alist[first];
            alist[first] = alist[pivotIndex];
            alist[pivotIndex] = temp;
        }
    }
    
    public static int partition(int[] alist, int first, int last) {
        int pivotVal = alist[first];
        int leftmark = first+1;
        for (int rightmark = first+1; rightmark <= last; rightmark++) {
            if (alist[rightmark] < pivotVal) {
                int temp = alist[leftmark];
                alist[leftmark] = alist[rightmark];
                alist[rightmark] = temp;
                leftmark = leftmark + 1;
            }
        }
        int temp = alist[first];
        alist[first] = alist[leftmark-1];
        alist[leftmark-1] = temp;
        
        return leftmark-1;
    }
    
    public static int quickSort(int[] alist, int first, int last, String pivotID) {
        int numComp = last - first;
        if (last <= first) {
            return 0;
        } else {
            choosePivot(alist, first, last, pivotID);
            int splitpoint = partition(alist, first, last);
            int numCompL = quickSort(alist, first, splitpoint-1, pivotID);
            int numCompR = quickSort(alist, splitpoint+1, last, pivotID);
            numComp = numComp + numCompL + numCompR;
        }
        return numComp;
    }
}
