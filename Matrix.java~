/*CS 230 FINAL PROJECT
 * SU LIN BLODGETT AND SRAVANTI TEKUMALLA
 * Matrix.java
 * This class implements the Matrix object for use in MatrixCalculator
 * Includes 2 constructors
 * And capability to transpose matrix
 */ 

public class Matrix 
{
  private int[][] matrix;
  
  //constructor 1 - takes in no parameters
  public Matrix() {
    matrix = new int[2][2];
  }
  
  //constructor 2 - takes in two parameters, m and n
  public Matrix(int m, int n) {
    matrix = new int[m][n];
  }
  
  //clones matrix
  public Matrix clone()
  {
 
   Matrix temp = new Matrix(matrix.length, matrix[0].length);
   for(int i = 0; i < matrix.length; i++) {
     for (int j = 0; i < matrix[0].length; j++)
       temp.matrix[i][j] = matrix[i][j];
   }
   return temp;
  }
  
  //gets entry 
  public int getEntry(int i, int j) {
    return matrix[i][j];
  }
  
  //sets entry
  protected void setEntry(int i, int j, int k) {
   matrix[i][j] = k; 
  }
  
  //transposes matrix 
  public Matrix transpose() {
   Matrix transpose = clone();
    for(int i = 0; i < matrix.length; i++) {
     for(int j = 0; j < matrix[0].length; j++)
       transpose.matrix[i][j] = matrix[j][i];
    }
    return transpose;
  }
  
  //returns a string represenation of the matrix
  public String toString() {
    String s = "";
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length; j++)
        s += matrix[i][j] + " "; 
      s += "\n";
    }
    return s;
    
  }
  
  //testing 
  public static void main(String[] args) {
   Matrix m = new Matrix();
   System.out.println(m);
   Matrix m1 = new Matrix(3,3);
   System.out.println(m1);
   m1.setEntry(1,1,1);
   m1.setEntry(1,2,5);
   System.out.println(m1);
   System.out.println(m1.getEntry(1,1));
  }
  
}