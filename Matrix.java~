/*CS 230 FINAL PROJECT
 * SU LIN BLODGETT AND SRAVANTI TEKUMALLA
 * Matrix.java
 * This class implements the Matrix object for use in MatrixCalculator
 * Includes 2 constructors
 * And capability to transpose matrix
 */ 
import java.util.*;

public class Matrix 
{
  
  private double[][] matrix;
  private int rowCount;
  private int columnCount;
  
  //constructor 1 - takes in no parameters
  public Matrix() {
    matrix = new double[2][2];
    rowCount = 2;
    columnCount = 2;
  }
  
  //constructor 2 - takes in two parameters, m and n
  public Matrix(int m, int n) {
    matrix = new double[m][n];
    rowCount = m;
    columnCount = n;
  }
  
  //clones matrix
  public Matrix clone()
  { 
    Matrix temp = new Matrix(matrix.length, matrix[0].length);
    for(int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        temp.matrix[i][j] = matrix[i][j];
      }
    }
    return temp;
  }
  
  //gets entry 
  public double getEntry(int i, int j) {
    return matrix[i][j];
  }
  
  //sets entry
  protected void setEntry(int i, int j, double k) {
    matrix[i][j] = k; 
  }
  
  //gets row count 
  public int getRowCount() {
    return rowCount;
  }
  
  //gets column count
  public int getColumnCount() {
    return columnCount;
  }
  
  // helper method that returns matrix
  public double[][] getMatrix() {
    return this.matrix; 
  }
  
  //transposes matrix 
  public Matrix transpose() {
    Matrix transpose = new Matrix(matrix[0].length, matrix.length);
    for(int i = 0; i < matrix[0].length; i++) {
      for(int j = 0; j < matrix.length; j++)
        transpose.matrix[i][j] = matrix[j][i];
    }
    return transpose;
  }
  
  // removes a row from the matrix
  public void removeRow(int n) {
    for(int i = 0; i < matrix.length-1; i++) {
      for(int j = 0; j < matrix[0].length; j++) 
        matrix[i][j] = matrix[i+1][j]; 
    }
    for(int j = 0; j < matrix[0].length; j++) {
      matrix[matrix.length-1][j] = 0;
    }
    rowCount--;
     
  }
  
  // swaps rows of a matrix
  public void swapRows(int m, int n) {
    double[] temp = new double[matrix[0].length];
    for(int i = 0; i < matrix[0].length; i++) {
      temp[i] = matrix[m][i];
    }
    for(int j = 0; j < matrix[0].length; j++) {
      matrix[m][j] = matrix[n][j];
    }
    for(int k = 0; k < matrix[0].length; k++) {
      matrix[n][k] = temp[k];
    }
  }
  
  // divides row of matrix by number
  // assumes number is not zero
  public void scaleRow(int m, double n) {
    try {
      for(int i = 0; i < matrix[0].length; i++) {
        matrix[m][i] = matrix[m][i]/n;
      }
    } catch(ArithmeticException e) {
      System.out.println("Divide by zero, try again!");
    }
  }
  
  // multiplies and adds a row to another
  // assumes double is not zero
  public void multiplyAndAdd(int m, int n, double k) {
    for(int i = 0; i < matrix[0].length; i++) {
      matrix[n][i] *= k;
      //System.out.println(matrix[n][i]*k);
      matrix[m][i] -= matrix[n][i];
      matrix[n][i] /= k;
    }
  }
  
  // checks that row is reduced
  public boolean isReduced(int m) {
    for(int i = 0; i < matrix[0].length; i++) {
      if(i == m-1 && matrix[m][i] != 1.0) return false;
      if(matrix[m][i] != 0.0) return false;
    }
    return true;
  }
  
  // checks if row is zeroes
  public boolean isRowZeroes(int m) {
    for(int i = 0; i < matrix[0].length; i++) { 
      if(matrix[m][i] != 0) return false;
    }
    return true;
  }
  
  public void removeRowZeroes() {
    for(int i = 0; i < matrix[0].length; i++) { 
      if(isRowZeroes(i)) removeRow(i);
    }
  }
  
  // checks if column is zeroes
  public boolean isColumnZeroes(int m) {
    for(int i = 0; i < matrix.length; i++) { 
      if(matrix[i][m] != 0) return false;
    }
    return true;
  }
  
  // gets the row index of highest element in the mth column
  public int getHighest(int m) {
    int highest = m;
    if(matrix.length != m) {
      for(int i = m+1; i < matrix.length; i++) {
        if(matrix[i][m] > matrix[highest][m]) highest = i;
      }
    } else highest = m;
    return highest;
  }
  
  // finds index of first nonzero element in a row
  public int getNonZero(int m) {
    for(int i = 0; i < matrix[0].length; i++) { 
        if(matrix[m][i] != 0) return i;
    }
    return -1;
  }
  
  //search and destroy
  public void nuke() {
    for (int i = 0; i < matrix.length; i++) {
      scaleRow(i, matrix[i][getNonZero(i)]); 
    }
    for (int j = 0; j < matrix.length-1; j++) {
      for(int k = j+1; k < matrix.length; k++)
        if(rowEquals(j, k)) multiplyAndAdd(k, j, 1);    
    }
  }  
  
  //compare method
  public boolean rowEquals(int m, int n) {
    for (int i = 0; i < matrix[0].length; i++) 
      if (matrix[m][i] != matrix[n][i]) return false;
    return true; 
  }
  
  // returns a string representation of the matrix
  public String toString() {
    String s = "";
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length; j++)
        s += matrix[i][j] + " "; 
      s += "\n";
    }
    return s;
  }
  
  // testing 
  public static void main(String[] args) {
    Matrix m = new Matrix();
    System.out.println(m);
    for(int i = 0; i < m.matrix.length; i++) {
      for(int j = 0; j < m.matrix[0].length; j++) {
        m.matrix[i][j] = 5; 
      }
    }
//    Matrix m1 = new Matrix(3,3);
//    System.out.println(m1);
//    m1.setEntry(1,1,1);
//    m1.setEntry(1,2,5);
//    System.out.println(m1);
//    System.out.println(m1.getEntry(1,1));
//    
//    m1.swapRows(0,1);
//    System.out.println(m1);
//    
//    m1.removeRow(0);
//    System.out.println(m1);
    
    System.out.println(m);
    m.multiplyAndAdd(0,1,2);
    System.out.println(m);
    System.out.println(m.clone());
  
    Matrix fourth = new Matrix(3,3);
    fourth.setEntry(0,0,4);
    fourth.setEntry(0,1,12);
    fourth.setEntry(0,2,5);
    fourth.setEntry(1,1,7);
    fourth.setEntry(1,2,29);
    fourth.setEntry(2,0,3);
    fourth.setEntry(2,1,9);
    fourth.setEntry(2,2,15.0/4.0);
    fourth.multiplyAndAdd(0,2,(4/3));
    fourth.nuke();
    System.out.println(fourth);
    
    Matrix fifth = new Matrix(2,2);
    fifth.setEntry(0,0,1);
    fifth.setEntry(0,1,1);
    fifth.setEntry(1,0,1);
    fifth.setEntry(1,1,1);
    fifth.multiplyAndAdd(1,0,2);
    System.out.println(fifth);
    
  }
  
}