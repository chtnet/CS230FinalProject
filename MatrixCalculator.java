public class MatrixCalculator {
  
  private Matrix matrix;
  
  public MatrixCalculator(int m, int n)
  {
    matrix = new Matrix(m,n); 
    for(int i = 0; i < 3; i++) {
      matrix.setEntry(0, i, 3);
    }
    matrix.setEntry(1,1,14);
    matrix.setEntry(1,2,6);
    matrix.setEntry(2,2,20);
   }
  
  public Matrix inverse()
  {
    Matrix temp = matrix.clone();
    return temp;
  }
  
  public Matrix RREF()
  {
    Matrix temp = matrix.clone();
    double[][] matrix = temp.getMatrix();
    for(int i = 0; i < matrix.length; i++) { // removes zero rows, if any, from matrix
      if(temp.isRowZeroes(i)) temp.removeRow(i); 
    }
    for(int i = 0; i < matrix[0].length; i++) {
      if(!temp.isColumnZeroes(i)) { // ensures column is not all zeroes
        temp.swapRows(i, temp.getHighest(i)); // swaps ith row with row with highest first elt
        int index = temp.getNonZero(i);
        temp.scaleRow(i, matrix[i][index]); // scales the ith row by value of first element
      }
    }
    for(int i = 0; i < matrix[0].length-1; i++) {
      temp.scaleAll(i);
    }
    return temp;
  }
  
  public Matrix CREF()
  {
    Matrix temp = matrix.transpose();
    temp = RREF();
    temp.transpose();
    return temp;
  }
  
  public static void main(String[] args) {
    MatrixCalculator mc = new MatrixCalculator(3,3);
    System.out.println("ORIGINAL MATRIX\n" + mc.matrix);

    System.out.println(mc.RREF());
    
    //Matrix reduced = mc.RREF();
    
  } 
  
}