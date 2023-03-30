package practice;

public class matrixMultiplication {
	
	private static int[][] recursiveMatrixMultiplication(int[][] A, int[][] B) {
		int len = A.length;
		
		if (len == 2) {
			return new int[][] {
								{A[0][0]*B[0][0] + A[0][1]*B[1][0], A[0][0]*B[0][1] + A[0][1]*B[1][1]},
								{A[1][0]*B[0][0] + A[1][1]*B[1][0], A[1][0]*B[0][1] + A[1][1]*B[1][1]}
							   };
		}
		
		int[][] C = new int[len][len];
		
		int n = len / 2;
		
		int[][] A11 = new int[n][n];
		int[][] A12 = new int[n][n];
		int[][] A21 = new int[n][n];
		int[][] A22 = new int[n][n];
		int[][] B11 = new int[n][n];
		int[][] B12 = new int[n][n];
		int[][] B21 = new int[n][n];
		int[][] B22 = new int[n][n];
		
		split(A, A11, 0, 0);
        split(A, A12, 0, n);
        split(A, A21, n, 0);
        split(A, A22, n, n);
        split(B, B11, 0, 0);
        split(B, B12, 0, n);
        split(B, B21, n, 0);
        split(B, B22, n, n);
        
        // Recursively multiply sub-matrices
        int[][] C11 = addMatrix(recursiveMatrixMultiplication(A11, B11), recursiveMatrixMultiplication(A12, B21));
        int[][] C12 = addMatrix(recursiveMatrixMultiplication(A11, B12), recursiveMatrixMultiplication(A12, B22));
        int[][] C21 = addMatrix(recursiveMatrixMultiplication(A21, B11), recursiveMatrixMultiplication(A22, B21));
        int[][] C22 = addMatrix(recursiveMatrixMultiplication(A21, B12), recursiveMatrixMultiplication(A22, B22));
        
        merge(C11, C, 0, 0);
        merge(C12, C, 0, n);
        merge(C21, C, n, 0);
        merge(C22, C, n, n);
		
		return C;
	}
	
	private static void merge(int[][] part, int[][] target, int x, int y) {
		int len = part.length;
		
		for (int i=x; i<len; i++) {
			for (int j=y; j<len; j++) {
				target[i][j] = part[i-x][j-y];
			}
		}
		
	}
	
	private static int[][] addMatrix(int[][] m1, int[][] m2) {
		int len = m1.length;
		int[][] res = new int[len][len];
		
		for (int i=0; i<len; i++) {
			for (int j=0; j<len; j++) {
				res[i][j] = m1[i][j] + m2[i][j];
			}
		}
		
		return res;
	}
	
	private static void split(int[][] origin, int[][] part, int x, int y) {
		for (int i=x; i<part.length; i++) {
			for (int j=y; j<part[0].length; j++) {
				part[i-x][j-y] = origin[i][j];
			}
		}
	}

	public static void main(String[] args) {
	    // Initialize matrices
		
		int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {0, 0, 0, 0}};
		int[][] B = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}, {0, 0, 0, 0}};



	    // Multiply matrices using recursive matrix multiplication
	    int[][] C = recursiveMatrixMultiplication(A, B);

	    // Print out resulting matrix
	    for (int i = 0; i < C.length; i++) {
	        for (int j = 0; j < C[0].length; j++) {
	            System.out.print(C[i][j] + " ");
	        }
	        System.out.println();
	    }
	}

}
