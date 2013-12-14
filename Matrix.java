/**
 * This is a representation of the Matrix object and includes all operations performed on a matrix
 * Includes the three elementary row operations: scale rows, multiply and add one row to another and to swtich rows.
 * 
 * @author Sravanti Tekumalla
 * @author Su Lin Blodgett
 */
import java.util.*;

public class Matrix 
{
  
  private double[][] matrix;
  private int rowCount;
  private int columnCount;
  
  /** 
   * Class constructor.
   */
  public Matrix() {
    matrix = new double[2][2];
    rowCount = 2;
    columnCount = 2;
  }
  
  /**
   * Class constructor specifying number of rows and columns.
   */
  public Matrix(int m, int n) {
    matrix = new double[m][n];
    rowCount = m;
    columnCount = n;
  }
  
  public Matrix(double[][] array) {
    matrix = array;
    rowCount = array[0].length;
    columnCount = array.length;
  }
  
  /**
   * Clones Matrix object.
   * 
   * @return Matrix of clone
   */
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
  
  /**
   * Gets entry in a matrix based on given row and column index.
   * 
   * @param i  the row index
   * @param j  the column index
   * 
   * @return double entry at row i and column j
   */
  public double getEntry(int i, int j) {
    return matrix[i][j];
  }
  
  /**
   * Sets entry in a matrix based on given row and column index.
   * 
   * @param i  the row index
   * @param j  the column index
   */
  protected void setEntry(int i, int j, double k) {
    matrix[i][j] = k; 
  }
  
  /**
   * Gets the number of rows in the matrix.
   * 
   * @return int number of rows
   */
  public int getRowCount() {
    return rowCount;
  }
  /**
   * Gets the number of columns in the matrix.
   * 
   * @return int number of columns
   */
  public int getColumnCount() {
    return columnCount;
  }
  
  
  /**
   * Gets the double array containing the matrix entries
   * 
   * @return double[][] number of columns
   */
  public double[][] getMatrix() {
    return this.matrix; 
  }
  
  /**
   * Returns the transpose of the matrix. 
   * The transpose is switching the ith, jth entry of the matrix to the jth, ith entry.
   * 
   * @return Matrix transposed matrix
   */
  public Matrix transpose() {
    Matrix transpose = new Matrix(matrix[0].length, matrix.length);
    for(int i = 0; i < matrix[0].length; i++) {
      for(int j = 0; j < matrix.length; j++)
        transpose.matrix[i][j] = matrix[j][i];
    }
    return transpose;
  }
  
  /**
   * Removes the specified row from the matrix. Reduces the number of columns by one.
   * 
   * @param n the index of the row to be removed
   */
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
  
  public void removeColumn(int m) {
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length - 1; j++)
        matrix[i][j] = matrix[i][j+1];
    }
    for(int i = 0; i < matrix[0].length; i++) {
      matrix[i][matrix[0].length-1] = 0;
    }
    columnCount--; 
  }
  
  
  
  
  
  /**
   * Switches the entries of one row with another row. 
   * The order of the parameters doesn't matter.
   * 
   * @param m the index of first row to be switched
   * @param n the index of second row to be switched
   */
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
  
  /**
   * Switches the entries of one row with another row. 
   * The order of the parameters doesn't matter.
   * 
   * @param m the index of first row to be switched
   * @param n the index of second row to be switched
   */
  public void scaleRow(int m, double n) {
    try {
      for(int i = 0; i < matrix[0].length; i++) {
        matrix[m][i] = matrix[m][i]/n;
      }
    } catch(ArithmeticException e) {
      System.out.println("Divide by zero, try again!");
    }
  }
  
  /**
   * Excecutes the 3rd type of elementary row operation: 
   * Multiplies ith row by a scalar and adds those values to the jth row.
   * Leaves the ith row as it was before.
   * 
   * @param k the scalar to multiply the ith row
   * @param m the index of the ith row to multiplied
   * @param n the index of th jth row to add the values of the scaled ith row
   */
  public void multiplyAndAdd(int m, int n, double k) {
    for(int i = 0; i < matrix[0].length; i++) {
      matrix[n][i] *= k;
      //System.out.println(matrix[n][i]*k);
      matrix[m][i] -= matrix[n][i];
      matrix[n][i] /= k;
    }
  }
  
  /**
   * Checks to see if the row consists of all zeroes. 
   * 
   * @param m the int of the row to be checked
   * @return boolean if the row is all zeroes
   */
  public boolean isRowZeroes(int m) {
    for(int i = 0; i < matrix[0].length; i++) { 
      if(matrix[m][i] != 0) return false;
    }
    return true;
  }
  
  
  /**
   * Checks to see if the specified row consists of all zeroes. 
   * 
   * @return boolean if the row is all zeroes
   */
  public void removeRowZeroes() {
    for(int i = 0; i < matrix[0].length; i++) { 
      if(isRowZeroes(i)) removeRow(i);
    }
  }
  
  
  /**
   * Checks to see if the specified column consists of all zeroes. 
   * 
   * @param m the int of the column to be checked
   * @return boolean if the colunn is all zeroes
   */
  public boolean isColumnZeroes(int m) {
    for(int i = 0; i < matrix.length; i++) { 
      if(matrix[i][m] != 0) return false;
    }
    return true;
  }
  
  /**
   * Finds the highest int in a row 
   * 
   * @param m the int of the row to be checked
   * @return int of highest integer in the row
   */
  public int getHighest(int m) {
    int highest = m;
    if(matrix.length != m) {
      for(int i = m+1; i < matrix.length; i++) {
        if(matrix[i][m] > matrix[highest][m]) highest = i;
      }
    } else highest = m;
    return highest;
  }
  
  /**
   * Finds the first nonzero int in a row 
   * 
   * @param m the int of the row to be checked
   * @return int of the first nonzero int in the row
   */
  public int getNonZero(int m) {
    for(int i = 0; i < matrix[0].length; i++) { 
      if(matrix[m][i] != 0) return i;
    }
    return -1;
  }
  
  //search and destroy
  public void nuke() {
    for (int i = 0; i < matrix.length; i++) {
      if(getNonZero(i) > 0) //only scales the row if it's not all zeroes
        scaleRow(i, matrix[i][getNonZero(i)]); 
    }
    for (int j = 0; j < matrix.length-1; j++) {
      for(int k = j+1; k < matrix.length; k++)
        if(rowEquals(j, k)) multiplyAndAdd(k, j, 1);    
    }
  }  
  
  /**
   * Compares two rows to see if they are equal 
   * 
   * @param m the int of the first row to be checked
   * @param n the int of the second row to be checked
   * 
   * @return boolean if the two rows are equal
   */
  public boolean rowEquals(int m, int n) {
    for (int i = 0; i < matrix[0].length; i++) 
      if (matrix[m][i] != matrix[n][i]) return false;
    return true; 
  }
  
  
  /**
   * Returns a string repesenation of the Matrix object
   * 
   * @return String string representation of Matrix object
   */
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
    fifth.removeColumn(0);
    fifth.removeRow(0);
    System.out.println(fifth);
    
  }
  
}