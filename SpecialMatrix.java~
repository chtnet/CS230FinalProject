/**
 * This is an implementation of a binary search tree of matrix properties with two search functions.
 * 
 *
 * @author Su Lin Blodgett
 */
import javafoundations.*;
import java.util.*;

public class SpecialMatrix {
  
  private MatrixCalculator reduced;
  private LinkedList<String> properties;
  private LinkedBinaryTree<String> t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, tree;
  
  /** 
   * Class constructor.
   */
  public SpecialMatrix(Matrix m) {
    reduced = new MatrixCalculator(m);
    reduced.REF();
    properties = new LinkedList<String>();
    
    String s1 = "Square";
    String s2 = "Upper triangular";
    String s3 = "Lower triangular";
    String s4 = "Symmetric";
    String s5 = "Skew-symmetric";
    String s6 = "Zero determinant";
    String s7 = "Orthonormal";
    
    t0 = new LinkedBinaryTree<String>("");
    
    t1 = new LinkedBinaryTree<String>(s3, t0, t0);
    t2 = new LinkedBinaryTree<String>(s2, t1, t1);
    t3 = new LinkedBinaryTree<String>(s1, t2, t0);
    
    t4 = new LinkedBinaryTree<String>(s5, t0, t0);
    t5 = new LinkedBinaryTree<String>(s4, t0, t4);
    t6 = new LinkedBinaryTree<String>(s1, t5, t0);
    
    t7 = new LinkedBinaryTree<String>(s7, t0, t0);
    t8 = new LinkedBinaryTree<String>(s6, t0, t7);
    t9 = new LinkedBinaryTree<String>(s1, t8, t0);
  }
  
  /**
   * Gets list of properties.
   * 
   * @return LinkedList of properties
   */
  public LinkedList getProperties() {
    return searchAll(); 
  }
  
  /**
   * Gets associated MatrixCalculator.
   * 
   * @return MatrixCalculator.
   */
  public MatrixCalculator getMatrix() {
    return reduced; 
  }
  
  /**
   * Runs through the three binary search trees and returns list of properties it finds
   * associated with the matrix.
   * 
   * @return LinkedList of properties.
   */
  private LinkedList searchAll() {
    searchFirst();
    searchSecond();
    searchThird();
    return properties;    
  }
  
  /**
   * Runs through the first binary search tree.
   * 
   */
  private void searchFirst() {
    if(isSquare()) {
      properties.add("This matrix is square. This means the number of rows equals the number of columns.");
      if(isUpperTriangular()) {
        properties.add("This matrix is upper triangular. This means that every element above the diagonal"
                   + "is 0.");
        if(isLowerTriangular()) {
          properties.add("This matrix is lower triangular. This means that every element below the diagonal"
                   + "is 0. If this matrix is also upper triangular, then this matrix is diagonal.");
          properties.add("This matrix is diagonal.");
        }
      } else if(isLowerTriangular()) {
         properties.add("This matrix is lower triangular. This means that every element below the diagonal"
                   + "is 0. If this matrix is also upper triangular, then this matrix is diagonal.");
      }
    }
  }
  
  /**
   * Runs through the second binary search tree.
   * 
   */
  private void searchSecond() {
    if(isSquare()) {
      if(isSymmetric()) {
        properties.add("This matrix is symmetric. This means that it is equal to its transpose.");
      } else if(isSkew()) {
        properties.add("This matrix is skew-symmetric. This means that it is equal to the negative of its transpose.");
      }
    }
  }
  
  /**
   * Runs through the third binary search tree.
   * 
   */
  private void searchThird() {
    if(isSquare()) {
      if(isDetNonZero()) {
        properties.add("The matrix does not have determinant 0. This means that it is invertible.");
        if(isOrthonormal()) {
          properties.add("This matrix is orthonormal. This means that its inverse is equal to its transpose.");  
        }
      }
    }
  }
  
  /**
   * Determines if matrix is square.
   * 
   * @return boolean
   */
  private boolean isSquare() {
    return (getMatrix().getRowCount() == getMatrix().getColumnCount());
  }
  
  /**
   * Determines if matrix is upper triangular.
   * 
   * @return boolean
   */
  private boolean isUpperTriangular() {
    for(int i = 0; i < getMatrix().getRowCount(); i++) {
      for(int j = 0; j < i; j++) 
        if(reduced.getMatrix().getEntry(i,j) != 0) return false;
    }
    return true;
  }
  
  /**
   * Determines if matrix is lower triangular.
   * 
   * @return boolean
   */
  private boolean isLowerTriangular() {
    for(int i = 0; i < reduced.getRowCount(); i++) {
      for(int j = i+1; j < reduced.getRowCount(); j++) {
        if(reduced.getMatrix().getEntry(i,j) !=0) return false; 
      }
    }
    return true;
  }
  
  /**
   * Determines if matrix is symmetric.
   * 
   * @return boolean
   */
  private boolean isSymmetric() {
    Matrix transpose = reduced.getMatrix().transpose(); 
    return reduced.getMatrix().equals(transpose);
  }
  
  /**
   * Determines if matrix is skew-symmetric.
   * 
   * @return boolean
   */
  private boolean isSkew() {
    Matrix negative = reduced.getMatrix().transpose();
    negative.negMatrix();
    return reduced.getMatrix().equals(negative);
  }
  
  /**
   * Determines if matrix is orthonormal.
   * 
   * @return boolean
   */
  private boolean isOrthonormal() {
    Matrix transpose = reduced.getMatrix().transpose();
    return reduced.inverse().equals(transpose);
  }
  
  /**
   * Determines if matrix has nonzero determinant.
   * 
   * @return boolean
   */
  private boolean isDetNonZero() {
    return reduced.determinant(getMatrix().getMatrix().getMatrix()) != 0;
  }
  
  public static void main(String[] args) {
    
    SpecialMatrix test = new SpecialMatrix(new Matrix(3,3));
    test.getMatrix().getMatrix().setEntry(0,0,4);
    test.getMatrix().getMatrix().setEntry(0,1,3);
    test.getMatrix().getMatrix().setEntry(0,2,7);
    test.getMatrix().getMatrix().setEntry(1,0,5);
    test.getMatrix().getMatrix().setEntry(1,1,6);
    test.getMatrix().getMatrix().setEntry(0,2,9);
    test.getMatrix().getMatrix().setEntry(2,2,2);
    System.out.println(test.getMatrix().getMatrix());
    System.out.println(test.searchAll().size());
  }
 
}