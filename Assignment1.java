import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Assignment1{
  
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
	  size = A.length;
	  int[][] C = new int[size][size];
	  
	  if (size == 1) {
		  C[0][0] = A[0][0] * B[0][0];
	  } else {
int[][] zero = new int[size][size]; //used to split matrix into sub-matrices
		  
		  /*
		   * M0 = (A00 + A11) * (B00 + B11)
		   * M1 = (A10 + A11) * B00
		   * M2 = A00 * (B01 - B11)
		   * M3 = A11 * (B10 - B00)
		   * M4 = (A00 + A01) * B11
		   * M5 = (A10 - A00) * (B00 + B01)
		   * M6 = (A01 - A11) * (B10 + B11)
		   */
		  
		int[][] M0 = denseMatrixMult(sum(A, A, 0, 0, size/2, size/2, size / 2), sum(B, B, 0, 0, size/2, size/2, size / 2), size / 2);
		int[][] M1 = denseMatrixMult(sum(A, A, size/2, 0, size/2, size/2, size / 2), sum(B, zero, 0, 0, 0, 0, size / 2), size / 2);
		int[][] M2 = denseMatrixMult(sum(A, zero, 0, 0, 0, 0, size / 2), sub(B, B, 0, size/2, size/2, size/2, size / 2), size / 2);
		int[][] M3 = denseMatrixMult(sum(A, zero, size/2, size/2, 0, 0, size / 2), sub(B, B, size/2, 0, 0, 0, size / 2), size / 2);
		int[][] M4 = denseMatrixMult(sum(A, A, 0, 0, 0, size/2, size / 2), sum(B, zero, size/2, size/2, 0, 0, size / 2), size / 2);
		int[][] M5 = denseMatrixMult(sub(A, A, size/2, 0, 0, 0, size / 2), sum(B, B, 0, 0, 0, size/2, size / 2), size / 2);
		int[][] M6 = denseMatrixMult(sub(A, A, 0, size/2, size/2, size/2, size / 2), sum(B, B, size/2, 0, size/2, size/2, size / 2), size / 2);

		  /*
		   * C00 = M0 + M3 - M4 + M6
		   * C01 = M2 + M4
		   * C10 = M1 + M3
		   * C11 = M0 - M1 + M2 + M5
		   */ 
		  
		  int[][] C00 = sum(sub(sum(M0, M3, 0, 0, 0, 0, size/2), M4, 0, 0, 0, 0, size/2), M6, 0, 0, 0, 0, size/2);
		  int[][] C01 = sum(M2, M4, 0, 0, 0, 0, size/2);
		  int[][] C10 = sum(M1, M3, 0, 0, 0, 0, size/2);
		  int[][] C11 = sum(sum(sub(M0, M1, 0, 0, 0, 0, size/2), M2, 0, 0, 0, 0, size/2), M5, 0, 0, 0, 0, size/2);
		 
		  
		  /*
		   * Two nested loops needed to add columns and rows of matrices
		   * Combining values into one array
		   */
		  		  
		  for(int i = 0; i < C.length/2; i++) {			  
			  for(int j = 0; j < C.length/2; j++) {			
				  C[i][j] = C00[i][j]; //top left
				  C[i][j + C.length/2] = C01[i][j]; //top right
				  C[i+ C.length/2][j] = C10[i][j]; //bottom left
				  C[i+ C.length/2][j + C.length/2] = C11[i][j]; //bottom right			
			  }
		  }
	  }
	  
	  return C;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
	  int[][] C = new int[n][n]; //result matrix
	  
	  //increase row position of each matrix every loop
	  for (int i = 0; i < n; i++, x1++, x2++) {	
		  
		  /*
		   * create new values to store the column position so it will
		   * wrap around each time outer loop reruns
		   * increase column position of each matrix every loop
		   */
		  for (int j = 0, ay = y1, by = y2; j < n; j++, ay++, by++) {
			  C[i][j] = A[x1][ay] + B[x2][by];
		  }
	  }
	  return C;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
	  int[][] C = new int[n][n];
	  for (int i = 0; i < n; i++, x1++, x2++) {
		  for (int j = 0, ay = y1, by = y2; j < n; j++, ay++, by++) {
			  C[i][j] = A[x1][ay] - B[x2][by];
		  }
	  }
	  return C;
  }
  
  
  public int[][] initMatrix(int n)
  {
	  int[][] A = new int[n][n];
	  return A;
  }
  
  public void printMatrix(int n, int[][] A)
  {
	  for (int i = 0; i < n; i++) {
		  for (int j = 0; j < A[i].length; j++) {
			  System.out.print(A[i][j] + " ");
		  }
		  System.out.println("");
	  }
  }
  
  public int[][] readMatrix(String filename, int n) throws Exception
  {	  
	  File file = new File(filename);
	  Scanner in = new Scanner(file);
	  int[][] A = new int[n][n];
	  
	  try {		
		  while(in.hasNext()) {
			  for(int i=0; i < A.length; i++) {				  
				  String[] row = in.nextLine().trim().split(" ");				  
				  for(int j=0; j < A[i].length; j++) {					  
					  A[i][j] = Integer.parseInt(row[j]);
				  }
			  }
		  }
		  in.close();
	  }
	  catch (Exception e) {
		  System.out.println("File cannot be read!");
	  }	  
	return A;
  }
}