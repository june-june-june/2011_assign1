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
		  
		  int[][] M0 = denseMatrixMult(sum(A, A, 0, 0, 1, 1, size/2), sum(B, B, 0, 0, 1, 1, size/2), size/2);
		  int[][] M1 = denseMatrixMult(sum(A, A, 1, 0, 1, 1, size/2), sum(B, zero, 0, 0, 0, 0, size/2), size/2);		  
		  int[][] M2 = denseMatrixMult(sum(A, zero, 0, 0, 0, 0, size/2), sub(B, B, 0, 1, 1, 1, size/2), size/2);
		  int[][] M3 = denseMatrixMult(sum(A, zero, 1, 1, 0, 0, size/2), sub(B, B, 1, 0, 0, 0, size/2), size/2);
		  int[][] M4 = denseMatrixMult(sum(A, A, 0, 0, 0, 1, size/2), sum(B, zero, 1, 1, 0, 0, size/2), size/2);
		  int[][] M5 = denseMatrixMult(sub(A, A, 1, 0, 0, 0, size/2), sum(B, B, 0, 0, 0, 1, size/2), size/2);
		  int[][] M6 = denseMatrixMult(sub(A, A, 0, 1, 1, 1, size/2), sum(B, B, 1, 0, 1, 1, size/2), size/2);
		  
		  /*
		   * C00 = M0 + M3 - M4 + M6
		   * C01 = M2 + M4
		   * C10 = M1 + M3
		   * C11 = M0 - M1 + M2 + M5
		   */ 
		  
		  for (int i = 0; i < size; i++) {
			  for (int j = 0; j < size; j++) {
				  
			  }
		  }
	  }
	  
	  return C;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
	  int[][] C = new int[n][n];
	  for (int i = 0; i < n; i++, x1++, x2++) {
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
	  
	  try {
		
		while(in.hasNext()) {
			System.out.println(in.nextLine());
		}
		//in.close();
  }
	  catch (Exception e) {
		  System.out.println("File cannot be read!");
	  }
	  
	  int[][] A = new int[n][n];
	  
	  while(true) {
	  
	  for(int i=0; i < A.length; i++) {
		  
		  String[] row = in.nextLine().trim().split(" ");
		  
		  for(int j=0; j < A[i].length; j++) {
			  
			  A[i][j] = Integer.parseInt(row[j]);
		  }
		  i++;
	  }
	return A;
	  
	 }
  

  }
  
  
}
