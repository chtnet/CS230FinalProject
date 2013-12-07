import java.util.*;

public class MatrixCalculator {
  
  private Matrix matrix;
  
  public MatrixCalculator(int m, int n)
  {
    matrix = new Matrix(m,n); 
  } 
  
  public double determinant(Matrix matrix) {
     
    double[][] temp = matrix.getMatrix();
    int sign = 1; //keeps track of sign
    double sum = 0; //keeps track of sum
    Matrix reducedTemp = REF(matrix);
        
    if(reducedTemp.getColumnCount() == reducedTemp.getRowCount()) {
      if (temp.length == 1) return temp[0][0];
      else {
        for(int i = 0; i < temp.length; i++) {
          Matrix minor = new Matrix(temp.length - 1, temp[0].length - 1);
          for(int j = 1; j < temp.length; j++) {
            for(int k = 0; k < temp.length; k++) {
              if(k < i) minor.setEntry(j - 1, k, temp[j][k]);
              if(k > i) minor.setEntry(j - 1, k - 1, temp[j][k]);
            }
          }
          sign = - sign;
          sum += temp[i][0] * determinant(minor);
        } 
      } 
    } else sum = Integer.MIN_VALUE;
    return sum;
  }
  
  public Matrix inverse()
  {
    Matrix temp = matrix.clone();
    return temp;
  }
  
  public Matrix REF(Matrix matrix)
  {
    Matrix temp = matrix.clone();
    for(int i = 0; i < temp.getMatrix()[0].length; i++) {
      if(!temp.isColumnZeroes(i)) { // ensures column is not all zeroes
        temp.swapRows(i, temp.getHighest(i)); // swaps ith row with row with highest first elt
        int index = temp.getNonZero(i);
        temp.scaleRow(i, temp.getMatrix()[i][index]); // scales the ith row by value of first element
      }
    }
    System.out.println(temp);
    temp.nuke();
    temp.removeRowZeroes();
    return temp;  
  }
  
  public Matrix RREF(Matrix matrix)
  {
    Matrix temp = REF(matrix);
    for(int i = 0; i < temp.getMatrix().length; i++) {
      temp.scaleAll(i);
    }
    return temp;
  }
  
  public Matrix CREF(Matrix matrix)
  {
    Matrix temp = matrix.transpose();
    temp = RREF(matrix);
    temp.transpose();
    return temp;
  }
  
  public static void main(String[] args) {
//    MatrixCalculator mc = new MatrixCalculator(1,1);
//    System.out.println("ORIGINAL MATRIX\n" + mc.matrix);
//    mc.matrix.setEntry(0,0,3);
//
//    System.out.println(mc.determinant(mc.matrix));
//    
//    MatrixCalculator second = new MatrixCalculator(2,2);
//    second.matrix.setEntry(0,0,4);
//    second.matrix.setEntry(0,1,12);
//    second.matrix.setEntry(1,1,13);
//    
//    System.out.println(second.determinant(second.matrix));
//    
//    MatrixCalculator third = new MatrixCalculator(2,1);
//    System.out.println(third.REF(third.matrix));
    //System.out.println(third.determinant(third.matrix));
    
    MatrixCalculator fourth = new MatrixCalculator(3,3);
    fourth.matrix.setEntry(0,0,4);
    fourth.matrix.setEntry(0,1,12);
    fourth.matrix.setEntry(0,2,5);
    fourth.matrix.setEntry(1,1,7);
    fourth.matrix.setEntry(1,2,29);
    fourth.matrix.setEntry(2,0,3);
    fourth.matrix.setEntry(2,1,9);
    fourth.matrix.setEntry(2,2,15.0/4.0);
    System.out.println("The fourth matrix:\n" + fourth.matrix);
    System.out.println(fourth.determinant(fourth.matrix));
    Matrix matrix = fourth.REF(fourth.matrix);
    System.out.println(matrix);
    
  } 
  
}