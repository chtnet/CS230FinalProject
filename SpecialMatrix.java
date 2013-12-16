import javafoundations.*;
import java.util.*;

public class SpecialMatrix {
  
  private MatrixCalculator reduced;
  private LinkedList<String> properties;
  private LinkedBinaryTree<String> t0, t1, t2, t3, t4, t5, t6, t7, t8, t9;
  
  public SpecialMatrix(Matrix m) {
    reduced = new MatrixCalculator(m.getRowCount(), m.getColumnCount());
    reduced.REF(m);
    properties = new LinkedList<String>();
    
    String s1 = "Is the matrix square?";
    String s2 = "Is the matrix upper triangular?";
    String s3 = "Is the matrix lower triangular?";
    String s4 = "Is the matrix symmetric?";
    String s5 = "Is the matrix skew-symmetric?";
    String s6 = "Is the matrix orthonormal?";
    
    t0 = new LinkedBinaryTree<String>();
    
    t1 = new LinkedBinaryTree<String>(s3, t0, t0);
    t2 = new LinkedBinaryTree<String>(s2, t1, t1);
    t3 = new LinkedBinaryTree<String>(s1, t2, t0);
    
    t4 = new LinkedBinaryTree<String>(s5, t0, t0);
    t5 = new LinkedBinaryTree<String>(s4, t0, t4);
    t6 = new LinkedBinaryTree<String>(s1, t5, t0);
    
    t7 = new LinkedBinaryTree<String>(s6, t0, t0);
    t8 = new LinkedBinarytree<String>(s1, t7, t0);
  }
  
  public LinkedList getProperties() {
    return properties; 
  }
  
  public MatrixCalculator getMatrix() {
    return reduced; 
  }
  
  public LinkedList treeOne() {
    if(t3.getRootElement()) {
      if(t2.getRootElement()) {
        t1.getRootElement();
      } else t1.getRootElement();
    }
    return properties;
  }
  
  public LinkedList treeTwo() {
    if(t6.getRootElement()) {
      if(t5.getRootElement()) t4.getRootElement();
    }
    return properties;
  }
  
  public LinkedList treeThree() {
    if(t9.getRootElement()) {
      if(!t8.getRootElement()) t7.getRootElement();
    }
    return properties;
  }
  
  public boolean isSquare() {
    if(reduced.getMatrix().getRowCount() == reduced.getMatrix().getColumnCount()) {
      properties.add("This matrix is square. This means the number of rows equals the number of columns.");
      return true;
    }
    return false;
  }
  
  public boolean isUpperTriangular() {
    for(int i = 0; i < reduced.getRowCount(); i++) {
      for(int j = i+1; j < reduced.getRowCount(); j++) 
        if(reduced.getMatrix().getMatrix()[i][j] != 0) return false;
    }
    properties.add("This matrix is upper triangular. This means that every element above the diagonal"
                   + "is 0.");
    return true;
  }
  
  public boolean isLowerTriangular() {
    for(int i = 0; i < reduced.getRowCount(); i++) {
      for(int j = 0; j < i; j++) {
        if(reduced.getMatrix().getMatrix()[i][j] !=0) return false; 
      }
    }
    properties.add("This matrix is lower triangular. This means that every element below the diagonal"
                   + "is 0. If this matrix is also upper triangular, then this matrix is diagonal.");
    return true;
  }
//  
//  public boolean diagonal() {
//    for(int i = 0; i < reduced.getRowCount(); i++) 
//      if(reduced.getMatrix().getMatrix()[i][i] != 1) return false;
//    properties.add("The matrix is diagonal. This means that every element along the diagonal"
//                   + "is 1 and every other element is 0.");
//    return true;
//  }
  
  public boolean isSymmetric() {
    Matrix transpose = reduced.getMatrix().transpose(); 
    if(reduced.getMatrix().equals(transpose)) {
      properties.add("This matrix is symmetric. This means that it is equal to its transpose.");
    }
    return false;
  }
  
  public boolean isSkew() {
    Matrix negative = reduced.getMatrix().transpose();
    negative.negMatrix();
    if(reduced.getMatrix().equals(negative)) {
      properties.add("This matrix is skew-symmetric. This means that it is equal to the negative of its transpose.");
      return true;
    }
    return false;
  }
  
  public boolean isOrthonormal() {
    Matrix transpose = reduced.getMatrix().transpose();
    if(reduced.inverse().equals(transpose)) {
      properties.add("This matrix is orthonormal. This means that its inverse is equal to its transpose."); 
      return true;
    }
    return false;
  }
  
  public boolean isDetNonZero() {
    if(reduced.determinant(reduced.getMatrix()) != 0) {
      properties.add("The matrix does not have determinant 0. This means that it is invertible.");
      return true;
    }
    return false;
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
  }
 
}