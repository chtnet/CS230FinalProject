/**
 * This is a representation of the Matrix object and includes all operations performed on a matrix
 * Includes the three elementary row operations: scale rows, multiply and add one row to another and to swtich rows.
 * 
 * @author Sravanti Tekumalla
 * @author Su Lin Blodgett
 */
import java.util.*;

public interface MatrixOps 
{

  /**
   * Clones Matrix object.
   * 
   * @return Matrix of clone
   */
  public Matrix clone();
  
  
  /**
   * Gets entry in a matrix based on given row and column index.
   * 
   * @param i  the row index
   * @param j  the column index
   * 
   * @return double entry at row i and column j
   */
  public double getEntry(int i, int j);
  
 
  /**
   * Gets the number of rows in the matrix.
   * 
   * @return int number of rows
   */
  public int getRowCount();
  
  /**
   * Gets the number of columns in the matrix.
   * 
   * @return int number of columns
   */
  public int getColumnCount();
  
  
  /**
   * Returns the transpose of the matrix. 
   * The transpose is switching the ith, jth entry of the matrix to the jth, ith entry.
   * 
   * @return Matrix transposed matrix
   */
  public Matrix transpose();
  
  /**
   * Removes the specified row from the matrix. Reduces the number of rows by one.
   * 
   * @param n the index of the row to be removed
   */
  public void removeRow(int n);
  
   
  /**
   * Removes the specified column from the matrix. Reduces the number of columns by one.
   * 
   * @param n the index of the column to be removed
   */
  
  public void removeColumn(int m);
 

  
  /**
   * Switches the entries of one row with another row. 
   * The order of the parameters doesn't matter.
   * 
   * @param m the index of first row to be switched
   * @param n the index of second row to be switched
   */
  public void swapRows(int m, int n);
  
  
  /**
   * Switches the entries of one row with another row. 
   * The order of the parameters doesn't matter.
   * 
   * @param m the index of first row to be switched
   * @param n the index of second row to be switched
   */
  public void scaleRow(int m, double n);
  
  /**
   * Excecutes the 3rd type of elementary row operation: 
   * Multiplies ith row by a scalar and adds those values to the jth row.
   * Leaves the ith row as it was before.
   * 
   * @param k the scalar to multiply the ith row
   * @param m the index of the ith row to multiplied
   * @param n the index of th jth row to add the values of the scaled ith row
   */
  public void multiplyAndAdd(int m, int n, double k);
  
  
  /**
   * Checks to see if the row consists of all zeroes. 
   * 
   * @param m the int of the row to be checked
   * @return boolean if the row is all zeroes
   */
  public boolean isRowZeroes(int m);
  

  
  
  /**
   * Checks to see if the specified column consists of all zeroes. 
   * 
   * @param m the int of the column to be checked
   * @return boolean if the colunn is all zeroes
   */
  public boolean isColumnZeroes(int m);
  
  

  /**
   * Finds the first nonzero int in a row 
   * 
   * @param m the int of the row to be checked
   * @return int of the first nonzero int in the row
   */
  public int getNonZero(int m);
  
  //search and destroy
  public void removeDuplicateRows(); 
  
  
  /**
   * Compares two rows to see if they are equal 
   * 
   * @param m the int of the first row to be checked
   * @param n the int of the second row to be checked
   * 
   * @return boolean if the two rows are equal
   */
  public boolean rowEquals(int m, int n);
  
  
  /**
   * Returns a string repesenation of the Matrix object
   * 
   * @return String string representation of Matrix object
   */
  public String toString();
  
 
}