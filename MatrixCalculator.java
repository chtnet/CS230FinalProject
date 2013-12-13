import java.util.*;

public class MatrixCalculator {
  
  private Matrix matrix;
  private LinkedList<String> steps = new LinkedList<String>();
  private int count;
  
  public MatrixCalculator(int m, int n)
  {
    matrix = new Matrix(m,n); 
    steps = new LinkedList<String>();
  } 
  
  public Matrix getMatrix() {
    return matrix; 
    
  }
  
//  public double determinant(Matrix matrix) {
//    steps.clear();
//    double[][] temp = matrix.getMatrix();
//    int sign = 1; //keeps track of sign
//    double sum = 0; //keeps track of sum
//    Matrix reducedTemp = REF(matrix);
//    
//    if(reducedTemp.getColumnCount() == reducedTemp.getRowCount()) {
//      if (temp.length == 1) return temp[0][0];
//      else {
//        for(int i = 0; i < temp.length; i++) {
//          Matrix minor = new Matrix(temp.length - 1, temp[0].length - 1);
//          for(int j = 1; j < temp.length; j++) {
//            for(int k = 0; k < temp.length; k++) {
//              if(k < i) minor.setEntry(j - 1, k, temp[j][k]);
//              if(k > i) minor.setEntry(j - 1, k - 1, temp[j][k]);
//            }
//          }
//          sign = - sign;
//          sum += temp[i][0] * determinant(minor);
//        } 
//      } 
//    } else sum = Integer.MIN_VALUE;
//    return sum;
//  }
  
  public double determinant(Matrix matrix) {
    
   
    double[][] temp = matrix.getMatrix();
    int sign = 1; //keeps track of sign
    double sum = 0; //keeps track of sum
   
    if(temp.length == temp[0].length) {
      if (temp.length == 1) return temp[0][0];
      else {
        for (int i = 0; i < temp.length; i++) {
          Matrix minor = minorMatrix(0, i);
          System.out.println(determinant(minor));
          sum += sign* temp[0][i] * determinant(minor);
          System.out.println("I'M HERE");
          System.out.println("sum" + sum);
          sign = - sign;
        }
      }
    }
    return sum;
  }
  
  
  
  
  
  
  private Matrix minorMatrix(int m, int n) //creates submatrix excluding mth row and nth column
  {
    double[][] temp = matrix.getMatrix();
    Matrix minor = new Matrix(temp.length - 1, temp[0].length - 1);
    int r = -1; //keeps track of minor lengths, adjusts by shifting rows
    for(int j = 0; j < temp.length; j++) {
      if( j != m) {
        r ++;
        int c = -1;
        for(int k = 0; k < temp.length; k++) {
          if (k != n) { 
            c++;
            minor.setEntry(r, c, temp[j][k]);
          }
        }
      }
    }
    return minor; 
  }
  
  private double cofactor(int m, int n) {
    Matrix minor = minorMatrix(m, n);
    double cofactor = determinant(minor);
    System.out.println("cofactor "+ cofactor);
    if((m+n) %2 == 1) cofactor = - cofactor;
    return cofactor;
    
  }
  
  
  
  public Matrix adjoint()
  {
    double[][] temp = matrix.getMatrix();
    Matrix adj = new Matrix(temp.length, temp.length); 
    for(int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp.length; j++) {
        adj.setEntry(i, j, cofactor(i,j));
      }
    }
    System.out.println(adj);
    return adj.transpose();
  }
  
  public Matrix inverse() {
    Matrix inverse = adjoint();
    double det = determinant(getMatrix());
    System.out.println(det);
    for(int i = 0; i < inverse.getRowCount(); i++) {
      for(int j = 0; j < inverse.getColumnCount(); j++) {
        inverse.getMatrix()[i][j] /= det;
        //inverse.setEntry(i, j, inverse.getEntry(i,j) / det);
      }
    }
    return inverse;
  }
  
  public Matrix REF(Matrix matrix)
  {
    Matrix temp = matrix.clone();
    for(int i = 0; i < temp.getMatrix()[0].length; i++) {
      if(!temp.isColumnZeroes(i)) { // ensures column is not all zeroes
        temp.swapRows(i, temp.getHighest(i)); // swaps ith row with row with highest first elt
        if ( i !=  temp.getHighest(i))
          steps.add("Swap rows " + i + " and " + temp.getHighest(i)); count++;
        int index = temp.getNonZero(i);
        temp.scaleRow(i, temp.getMatrix()[i][index]); // scales the ith row by value of first element
        if ( i !=  temp.getMatrix()[i][index])
          steps.add("Scale row " + i + " by " + temp.getMatrix()[i][index]); count++;
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
      temp.scaleRow(i, matrix.getNonZero(i));
      steps.add("Scale row " + i + " by " + matrix.getNonZero(i));
    }
    System.out.println("RREF\n" + temp);
    return temp;
  }
  
  public Matrix CREF(Matrix matrix)
  {
    steps.clear();
    Matrix temp = matrix.transpose();
    temp = RREF(matrix);
    temp.transpose();
    return temp;
  }
  
  public String stepsToString() {
    String s = "";
    int count = 0;
    for(int i = 0; i < steps.size(); i++)
      s += "Step " + i + " " + steps.get(i) + "\n"; count++;
    return s;
  
  }
  
  public String toString() {
    return matrix.toString();
    
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
    System.out.println("Determinant: " + fourth.determinant(fourth.matrix));
    Matrix matrix = fourth.REF(fourth.matrix);
    System.out.println(fourth.stepsToString());
    System.out.println(matrix);
    System.out.println(fourth.minorMatrix(1,1));
    
//    MatrixCalculator five = new MatrixCalculator(2,2);
//   
//    five.matrix.setEntry(0,0, 1);
//    five.matrix.setEntry(0,1,2);
//    five.matrix.setEntry(1,0, 3);
//    five.matrix.setEntry(1,1,4);
//    System.out.println(five.matrix);
//    System.out.println("inverse " + five.inverse());
//    
//    MatrixCalculator six = new MatrixCalculator(1,1);
//    six.matrix.setEntry(0,0,4);
//    System.out.println("det " + six.determinant(six.matrix));
    
   
    
  } 
  
}