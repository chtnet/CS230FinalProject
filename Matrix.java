/**
 * This is a representation of the Matrix object and includes all operations performed on a matrix.
 * Includes the three elementary row operations: scale rows, multiply and add one row to another and to switch rows.
 * 
 * @author Sravanti Tekumalla
 * @author Su Lin Blodgett
 */

import java.util.*;

public class Matrix implements MatrixOps
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
    Matrix transpose = new Matrix(getColumnCount(), getRowCount());
    for(int i = 0; i < getColumnCount(); i++) {
      for(int j = 0; j < getRowCount(); j++)
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
    for(int i = 0; i < getRowCount()-1; i++) {
      for(int j = 0; j < getColumnCount(); j++) 
        matrix[i][j] = matrix[i+1][j]; 
    }
    for(int j = 0; j < getColumnCount(); j++) {
      matrix[getRowCount()-1][j] = 0;
    }
    rowCount--;  
  }
  
  /**
   * Removes the specified column from the matrix. Reduces the number of rows by one.
   * 
   * @param n the index of the row to be removed
   */
  public void removeColumn(int m) {
    for(int i = 0; i < getRowCount(); i++) {
      for(int j = 0; j < getColumnCount() - 1; j++)
        matrix[i][j] = matrix[i][j+1];
    }
    for(int i = 0; i < getColumnCount(); i++) {
      matrix[i][getColumnCount()-1] = 0;
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
    double[] temp = new double[getColumnCount()];
    for(int i = 0; i < getColumnCount(); i++) {
      temp[i] = matrix[m][i];
    }
    for(int j = 0; j < getColumnCount(); j++) {
      matrix[m][j] = matrix[n][j];
    }
    for(int k = 0; k < getColumnCount(); k++) {
      matrix[n][k] = temp[k];
    }
  }
  
  /**
   * Scales a row by a factor.
   * 
   * @param m the index of row to be scaled
   * @param n the factor by which the row will be scaled
   */
  public void scaleRow(int m, double n) {
    try {
      for(int i = 0; i < getColumnCount(); i++) {
        matrix[m][i] = matrix[m][i]/n;
      }
    } catch(ArithmeticException e) {
      System.out.println("Divide by zero, try again!");
    }
  }
  
  /**
   * Executes the 3rd type of elementary row operation: 
   * Multiplies nth row by a scalar and subtracts those values from the mth row.
   * Leaves the nth row as it was before.
   * Assumes the scalar is not 0.
   * 
   * @param k the scalar to multiply the ith row
   * @param m the index of the row to multiplied
   * @param n the index of th row to add the values of the scaled row
   */
  public void multiplyAndAdd(int m, int n, double k) {
    for(int i = 0; i < getColumnCount(); i++) {
      //matrix[n][i] *= k;
      scaleRow(n, k);
      matrix[m][i] -= matrix[n][i];
      scaleRow(n, 1.0/k);
      //matrix[n][i] /= k;
    }
  }
  
  /**
   * Checks to see if the row consists of all zeroes. 
   * 
   * @param m the int of the row to be checked
   * @return boolean if the row is all zeroes
   */
  public boolean isRowZeroes(int m) {
    for(int i = 0; i < getColumnCount(); i++) { 
      if(matrix[m][i] != 0) return false;
    }
    return true;
  }
  
  /**
   * Checks to see if the specified row consists of all zeroes and removes it. 
   * 
   */
  public void removeRowZeroes() {
    for(int i = 0; i < getColumnCount(); i++) { 
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
    for(int i = 0; i < getRowCount(); i++) { 
      if(matrix[i][m] != 0) return false;
    }
    return true;
  }
  
  /**
   * Finds the highest int in a column below it
   * 
   * @param m the int of the column to be checked
   * @return int of highest integer in the row
   */
  public int getHighest(int m) {
    int highest = m;
    if(getRowCount() != m) {
      for(int i = m+1; i < getRowCount(); i++) {
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
    for(int i = 0; i < getColumnCount(); i++) { 
      if(matrix[m][i] != 0) return i;
    }
    return -1;
  }
  
  /**
   * Scales rows and then searches for and removes duplicate rows.
   * 
   */
  public void removeDuplicateRows() {
    for (int i = 0; i < getRowCount(); i++) {
      if(getNonZero(i) >= 0) //only scales the row if it's not all zeroes
        scaleRow(i, matrix[i][getNonZero(i)]); 
    }
    for (int j = 0; j < getRowCount()-1; j++) {
      for(int k = j+1; k < getRowCount(); k++)
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
    for (int i = 0; i < getColumnCount(); i++) 
      if (matrix[m][i] != matrix[n][i]) return false;
    return true; 
  }
  
  /**
   * Returns a matrix whose every entry is the negative of the entry of the original.
   * 
   * @return Matrix the negative of the original matrix
   */
  public Matrix negMatrix() {
    Matrix temp = new Matrix(getRowCount(), getColumnCount());
    for(int i = 0; i < getRowCount(); i++) {
      for(int j = 0; j < getColumnCount(); j++) {
        if(temp.matrix[i][j] != 0) temp.matrix[i][j] = -matrix[i][j];
        else temp.matrix[i][j] = 0;
      }
    }
    return temp;
  }
  
  /**
   * Checks if two matrices are equal by checking every entry
   * 
   * @return boolean if matrices are equal
   */
  public boolean equals(Matrix m) {
    for(int i = 0; i < getRowCount(); i++) {
      for(int j = 0; j < getColumnCount(); j++) {
        if(matrix[i][j] != m.matrix[i][j]) return false;
      }
    }
    return true;
  }
  
  /**
   * Returns a string repesenation of the Matrix object
   * 
   * @return String string representation of Matrix object
   */
  public String toString() {
    String s = "";
    for(int i = 0; i < getRowCount(); i++) {
      for(int j = 0; j < getColumnCount(); j++)
        s += matrix[i][j] + " "; 
      s += "<br>";
    }
    return s;
  }
  
  // testing 
  public static void main(String[] args) {
    
    // constructor, toString, and setEntry
    Matrix m = new Matrix(2,3);
    System.out.println("Matrix m:\n" + m);
    m.setEntry(0,0,4);
    m.setEntry(0,1,12);
    m.setEntry(0,2,5);
    m.setEntry(1,0,15);
    m.setEntry(1,1,7);
    m.setEntry(1,2,29);
    System.out.println("After entries have been set:\n" + m);
    
    // clone
    Matrix clone = m.clone();
    System.out.println("Clone of matrix m:\n" + clone);
    
    // getEntry
    System.out.println("Get entry of (1,1): " + m.getEntry(1,1));
    System.out.println("Get entry of (0,2): " + m.getEntry(0,2));
    
    // getRowCount, getColumnCount
    System.out.println("Row count of m:" + m.getRowCount());
    System.out.println("Column count of m: " + m.getColumnCount());
    
    Matrix n = new Matrix();
    System.out.println("Row count of n:" + n.getRowCount());
    System.out.println("Column count of n: " + n.getColumnCount());
    
    // getMatrix
    System.out.println("getMatrix() for m:\n" + m.getMatrix());
    System.out.println("getMatrix() for n:\n" + n.getMatrix());
    
    // transpose
    System.out.println("Transpose of matrix m:\n" + m.transpose());
    System.out.println("Transpose of matrix m transpose:\n"+  m.transpose());
    System.out.println("Transpose of matrix n:\n" + n.transpose());
    
    // removeRow
    clone.removeRow(3);
    System.out.println(clone);
    clone.removeRow(1);
    System.out.println(clone);
    
    Matrix p = new Matrix(1,1);
    System.out.println("Matrix p:\n" + p);
    p.removeRow(0);
    System.out.println("Now removing row 0:\n" + p);
    // p.removeRow(0);
    // System.out.println(p);
    
    // removeColumn
    //clone.removeColumn(0);
    //System.out.println(clone);
    
    p.removeColumn(0);
    System.out.println("Now removing column 0:\n" + p);
    
    // swapRows
    System.out.println(m);
    m.swapRows(0,1);
    System.out.println("Now swapping rows 0 and 1:\n" + m);
    //m.swapRows(1,3);
    //System.out.println(m);
    
    // scaleRow
    m.scaleRow(1, 5);
    System.out.println("Now scaling row 1 by 5:\n" + m);
    //m.scaleRow(1, 0);
    //System.out.println(m);
    
    // multiplyAndAdd
    m.multiplyAndAdd(0,1,12);
    System.out.println("Now multiplying row 0 by 12 and subtracting it from row 12\n"+ m);
    //m.multiplyAndAdd(0,3,2);
    //System.out.println(m);
    //m.multiplyAndAdd(0,1,0);
    //System.out.println(m);
    
    // isRowZeroes
    System.out.println("Is column zeroes? (false)" + m.isRowZeroes(1));
    //System.out.println(m.isRowZeroes(5));
    
    Matrix q = new Matrix(3,3);
    System.out.println("Matrix q\n" + q);
    System.out.println(q.isRowZeroes(2));
    
    // removeRowZeroes
    // m.removeRowZeroes();
    // System.out.println(m);
    
    q.removeRowZeroes();
    System.out.println("Now removing row zeroes\n" + q);
    
    // isColumnZeroes
    System.out.println("Is column zeroes? (false)" + m.isColumnZeroes(2));
    // System.out.println(m.isColumnZeroes(3));
    
    q = new Matrix(3,3);
    System.out.println("Is column zeroes? (true)" + q.isColumnZeroes(0));
    
    // getHighest
    System.out.println("Index of highest element of column 0: (0) " + m.getHighest(0));
    System.out.println("Index of highest element of column 2: (2) " + m.getHighest(2));
    // System.out.println(m.getHighest(5));
    
    // getNonZero
    System.out.println("First nonzero element of row 1: (0) " + m.getNonZero(1));
    // System.out.println(m.getNonZero(3));
    
    // removeDuplicates
    System.out.println("Now removing duplicate rows:");
    m.removeDuplicateRows();
    System.out.println(m);
    
    q.setEntry(0,0,4);
    q.setEntry(0,2,12);
    q.setEntry(2,0,20);
    q.setEntry(2,2,60);
    System.out.println(q);
    q.removeDuplicateRows();
    System.out.println(q);
    
    // rowEquals
    System.out.println("Does row 0 equal row 1? (false) " + m.rowEquals(0,1));
    
    q.setEntry(2,0,1);
    q.setEntry(2,2,3);
    System.out.println("Does row 0 equal row 2? (true) " + q.rowEquals(0,2));
    
    // negMatrix
    System.out.println("Matrix m negated:\n" + m.negMatrix());
    
    System.out.println("Matrix a negated:\n" +q.negMatrix());
    
    Matrix r = new Matrix(5,5);
    System.out.println("Matrix r\n" + r);
    System.out.println(r.negMatrix());
    
    // equals
    System.out.println(m.equals(q));
    
    Matrix s = new Matrix(5,5);
    System.out.println("Matrix s\n" + s);
    System.out.println("Does matrix s = matrix r? (true) " + r.equals(s));
    s.setEntry(4,4,12);
    System.out.println("Does matrix s = matrix r? (false) " + s.equals(r));
    
  }
  
}