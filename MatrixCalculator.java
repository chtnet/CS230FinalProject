import java.util.*;

public class MatrixCalculator {
  
  private Matrix matrix;
  private LinkedList<String> steps = new LinkedList<String>();
  private int count;
  
  /**
   * Class constructor specifying number of rows and columns.
   */
  public MatrixCalculator(int m, int n)
  {
    matrix = new Matrix(m,n); 
    steps = new LinkedList<String>();
  } 
  
  /**
   * Class constructor.
   */
  public MatrixCalculator(Matrix m) {
    matrix = m;
    steps = new LinkedList<String>();
  }
  
  /**
   * Returns associated matrix.
   * 
   * @return Matrix
   */
  public Matrix getMatrix() {
    return matrix; 
  }
  
  /**
   * Returns list of steps.
   * 
   * @return LinkedList of steps
   */
  public LinkedList<String> getSteps() {
   return steps; 
  }
  
  /** 
   * Returns row count of associated matrix.
   * 
   * @return int of rows in matrix
   */
  public int getRowCount() {
    return matrix.getRowCount();
  }
  
  /** 
   * Returns column count of associated matrix.
   * 
   * @return int of columns in matrix
   */
  public int getColumnCount() {
    return matrix.getColumnCount(); 
  }
  
  
//  public double determinant(Matrix matrix) {
//    
//   
//    double[][] temp = matrix.getMatrix();
//    int sign = 1; //keeps track of sign
//    double sum = 0; //keeps track of sum
//   
//    if(temp.length == temp[0].length) {
//      if (temp.length == 1) return temp[0][0];
//     else if (temp.length == 2) return (temp[0][0]*temp[1][1]) - (temp[0][1]*temp[1][0]);
//      else {
//        for (int i = 0; i < temp.length; i++) {
//          Matrix minor = minorMatrix(0, i);
//          System.out.println("MInor\n" + minor);
//          System.out.println(determinant(minor));
//          sum += sign* temp[0][i] * determinant(minor);
//          sign = - sign;
//        }
//      }
//    }
//    return sum;
//  }
  
  /**
   * Calculates determinant of a given matrix.
   * 
   * @param  matrix  array associated with the matrix
   * @return double  the calculated determinant
   */
  public double determinant(double[][] matrix){ //method sig. takes a matrix (two dimensional array), returns determinant.
    int sum=0; 
    int s;
    if(matrix.length == matrix[0].length) {
      if(matrix.length==1){  //base case - size 1 matrix determinant is itself.
        return(matrix[0][0]);
      }
      for(int i=0;i<matrix.length;i++){ //finds determinant using row-by-row expansion
        double[][]smaller= new double[matrix.length-1][matrix.length-1]; //creates smaller matrix- values not in same row, column
        for(int a=1;a<matrix.length;a++){
          for(int b=0;b<matrix.length;b++){
            if(b<i){
              smaller[a-1][b]=matrix[a][b];
            }
            else if(b>i){
              smaller[a-1][b-1]=matrix[a][b];
            }
          }
          steps.add("Minor matrix of row 1, column " + (i+1) + "<br>");
           steps.add(new Matrix(smaller).toString());
        }
        
       
        if(i%2==0){ //sign changes based on i
          s=1;
        }
        else{
          s=-1;
        }
        sum+=s*matrix[0][i]*(determinant(smaller)); steps.add("Current sum: " + sum); //recursive step: determinant of larger determined by smaller.
      }
    }
    steps.add("Determinant: " + sum);
    return(sum); //returns determinant value. once stack is finished, returns final determinant.
  }
  
  
  
  /**
   * Creates a submatrix of a given matrix that excludes the mth row and nth column.
   * 
   * @param  m  integer index of row to be excluded
   * @param  n  integer index of column to be excluded
   * @return Matrix  smaller resulting matrix
   */
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
  
  /* Calculates the cofactor (the determinant) of the minor (the submatrix) of a matrix. 
   * 
   * @param  m  integer index of row to be excluded
   * @param  n  integer index of row to be included
   * @param  double  the calculated cofactor
   */
  private double cofactor(int m, int n) {
    Matrix minor = minorMatrix(m, n);
    double cofactor = determinant(minor.getMatrix());
    if((m+n) %2 == 1) cofactor = - cofactor;
    return cofactor;
    
  }
  
  /**
   * Calculates and returns the transpose of the cofactor matrix (the matrix of the cofactors 
   * of a given matrix.
   * 
   * @return Matrix  the calculated adjoint matrix
   */
  public Matrix adjoint()
  {
    double[][] temp = matrix.getMatrix();
    Matrix adj = new Matrix(temp.length, temp.length); 
    for(int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp.length; j++) {
        adj.setEntry(i, j, cofactor(i,j));
      }
    }
    return adj.transpose();
  }
  
  /**
   * Calculates and returns the inverse of the associated matrix.
   * The inverse is calculated by calculating the adjoint matrix 
   * and dividing that matrix by the determinant. 
   * 
   * @return Matrix  the inverse of the matrix
   */
  public Matrix inverse() {
    Matrix inverse = adjoint();
    if(inverse.getMatrix().length == inverse.getMatrix()[0].length) {
      double det = determinant(getMatrix().getMatrix());
      steps.clear();
      steps.add("Adjoint:<br>"+ inverse.transpose().toString());
      steps.add("Determinant: " + det +"<br> The inverse is equal to the adjoint matrix times the determinant.");
      for(int i = 0; i < inverse.getRowCount(); i++) {
        for(int j = 0; j < inverse.getColumnCount(); j++) {
          inverse.getMatrix()[i][j] /= det;
        }
      }
    }
    return inverse;
  }
  
  /**
   * Calculates and returns the reduced echelon form of the associated matrix.
   * 
   * @return Matrix  reduced echelon form of the matrix
   */
  public Matrix REF()
  {
    
    Matrix temp = matrix.clone();
    for(int i = 0; i < temp.getMatrix()[0].length; i++) {
      if(!temp.isColumnZeroes(i)) { // ensures column is not all zeroes
        steps.add("Swap rows " + (i+1) + " and " + (temp.getHighest(i) + 1)); count++; steps.add(temp.toString());
        temp.swapRows(i, temp.getHighest(i)); // swaps ith row with row with highest first elt
        int index = temp.getNonZero(i);
        steps.add("Scale row " + (i+1) + " by " + temp.getMatrix()[i][index]); count++; steps.add(temp.toString());
        temp.scaleRow(i, temp.getMatrix()[i][index]); // scales the ith row by value of first element 
      }
    }
    temp.removeDuplicateRows();
    temp.removeRowZeroes();
    return temp;  
  }
  
  /**
   * Takes each row below the given row and multiplies and adds it by the given row.
   * 
   * @param  m  integer index of the row below which every row is multiplied and added
   */
  private void scaleAll(int m) {
    for(int i = 0; i < matrix.getRowCount(); i++) {
      steps.add("Multiply row " + (i+1) + " by " + matrix.getMatrix()[m][i] + " and subtract from row " + (m+1) + "<br>");
      steps.add(matrix.toString() + "<br>");
      matrix.multiplyAndAdd(m, i, matrix.getMatrix()[m][i]);
    } 
  }
  
  /**
   * Calculates and returns the reduced row echelon form of the associated matrix.
   * 
   * @return Matrix  the reduced row echelon form of a matrix
   */
  public Matrix RREF()
  {
    Matrix temp = REF();
    for(int i = 0; i < temp.getMatrix().length; i++) {
      scaleAll(i);
    }
    System.out.println("RREF\n" + temp);
    temp.removeDuplicateRows();
    temp.removeRowZeroes();
    return temp;
  }
  
  /**
   * Calculates and returns the column reduced echelon form of a matrix, which is found by
   * transposing a matrix, taking its reduced row echelon form, and transposing again.
   * 
   * @return Matrix  the column reduced echelon form of a matrix
   */
  public Matrix CREF()
  {
    steps.clear();
    Matrix temp = matrix.transpose();
    temp = RREF();
    temp.transpose();
    return temp;
  }
  
  /**
   * Returns a string repesenation of the MatrixCalculator object
   * 
   * @return String  string representation of MatrixCalculator object
   */
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
    System.out.println("Inverse: " + fourth.inverse());
    Matrix matrix = fourth.RREF();
    
    
    
    MatrixCalculator five = new MatrixCalculator(3,3);
    
    five.matrix.setEntry(0,0, 1);
    five.matrix.setEntry(0,1,1);
    five.matrix.setEntry(1,0, 3);
    five.matrix.setEntry(1,1,4);
    System.out.println(five.matrix);
    System.out.println("det " + five.determinant(five.matrix.getMatrix()));
    
    
    MatrixCalculator six = new MatrixCalculator(1,1);
    six.matrix.setEntry(0,0,4);
    System.out.println("det " + six.determinant(six.matrix.getMatrix()));
    
    
    
  } 
  
}