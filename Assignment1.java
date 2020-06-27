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
		  int[][] M0 = denseMatrixMult(sum(A, A, 0, 0, size/2, size/2, size/2), sum(B, B, 0, 0, size/2, size/2, size/2), size/2);
		  
	  }
	  
	  return C;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
	  int[][] C = new int[n][n];
	  int ay = y1;
	  int by = y2;
	  for (int i = 0; i < n; i++) {
		  ay = y1;
		  by = y2;
		  for (int j = 0; j < n; j++) {
			  C[i][j] = A[x1][ay] + B[x2][by];
			  ay++;
			  by++;
		  }
		  x1++;
		  x2++;
	  }
	  return C;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
	  int[][] C = new int[n][n];
	  int ay = y1;
	  int by = y2;
	  for (int i = 0; i < n; i++) {
		  ay = y1;
		  by = y2;
		  for (int j = 0; j < n; j++) {
			  C[i][j] = A[x1][ay] - B[x2][by];
			  ay++;
			  by++;
		  }
		  x1++;
		  x2++;
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
	  int A[][] = new int[n][n];
	  return A;
  }
  
}