Matrix Calculator: User Manual
Created by Sravanti Tekumalla and Su Lin Blodgett 
CS 230 Final Project

Matrix Calculator is a matrix calculator that can do operations that are often tedious and cumbersome to do by hand. Capabilities include calculating row reduced echelon form or column reduced echelon form, displaying the step-by-step process to help the user understand how these calculations were done. Matrix Calculator can also calculate the inverse and determinant of a matrix. After all desired calculations are completed, the user has the ability to view special properties about the matrix: is it symmetric or skew-symmetric? Orthonormal? Triangular or diagonal?

To ensure the program works correctly, please make sure the following files and folders are in the program folder. To run the program, run MatrixCalculatorGUI.java.

javafoundations package
CalculatorPanel.java
IntroPanel.java
Matrix.java
MatrixCalculator.java
MatrixCalculatorGUI.java
MatrixOps.java
SpecialMatrix.java
StepsPanel.java
TermsPanel.java

Upon running the program, an introductory panel will appear with an explanation of the program features and how to run the program (including constraints).
On the second panel the user may choose either to import a matrix or to manually enter matrix values. A matrix of any size may be imported from a plain text file in the same directory as the program, so long as the values on each row in the file are separated by commas. For manual entry the user may choose the dimensions of the matrix up to 5 by 5. The matrix is automatically populated with zeroes.


The input dialog
The user can choose to import a matrix of up to 5x5 size. Here, a 3x3 matrix is being populated manually with the calculation Row Echelon Form selected.



The user can then choose the operation they wish to perform on the matrix;

Row EF - row echelon form of the matrix
Row-Reduced EF - reduced row echelon form of the matrix
Column-Reduced EF - column reduced echelon form of the matrix
Inverse - inverse of the matrix
Determinant - determinant of the matrix

The panel will then display the calculation. The panel will display an error message if the calculation cannot be performed (e.g. trying to take the determinant of a non-square matrix).




After the calculation is performed, the results are displayed in the same panel the entries were done. The button “Show me the Steps” appears, which allows the user to see how the calculation was executed. 




A button called “Show me the steps!” will then be enabled, which will pop up a new panel displaying the step-by-step calculation. The user has the option to save these steps to a text file.

The popup steps panel which displays the steps of the calculation. 



On the third panel the properties associated with the matrix will be displayed, along with a short definition of the property.


Use of Data Structures
Decision tree: We decided to implement our extra information tool, such as if a matrix is symmetric, diagonal, etc by using a decision tree. This allows us to determine what sort of matrix a user enters by evaluating booleans about the matrix, like whether the matrix is equal to its transpose, whether it only has values on the diagonal, etc. 
Linked Lists: We also decided to use linked lists to store the steps of row-reduction. We want to store the steps in order to present to the user which steps were done in order to reduce the matrix so they can see the process. We chose linked lists because we don’t know the number of steps that are going to be used and we didn’t want to reindex continuously. 
Two-Dimensional Array: Lastly, we used a 2D array to implement the matrix itself and perform the operations such as finding the inverse, determinant, etc. This seemed to be an obvious choice because a 2D array corresponds directly to a matrix.

Important Classes: 
Matrix.java - This class will create a matrix based on specified dimensions. Implements the interface MatrixOps.
MatrixOps.java - This class is an interface which includes operations typically performed on a matrix, such as finding the transpose, negating the matrix and finding the first non-zero element in a row or column. 
MatrixCalculator.java - This class calculates determinants, inverses, row-reduced, column-reduced and row-reduced echelon form calculations on a Matrix. 
SpecialMatrix.java - This class implements a binary search decision tree to determine if a matrix is a special kind of matrix, such as diagonal, triangular or symmetric. 
MatrixCalculatorGUI.java - This class implements a multi-tabbed pane interface for MatrixCalculator.java. 


Bugs and Future Improvements: 
Determinant method: The determinant method does not currently take in the proper parameters. We originally wrote a recursive method which took in a Matrix object and calculated the determinant using the expansion by minors method. However, we kept receiving a stack overflow error due to the recursion not working properly, particularly with our getMatrix() method. We left in this method in the comments to show our thought process but implemented an alternative determinant method which takes in a double array (which is what a Matrix object consists of) instead of the  Matrix object itself. We also had to use a different algorithm to obtain the minor matrices instead of calling our minorMatrix() method. 
Row-Reduced Echelon Form: Currently, our RREF() method is broken, likely due to our scaling method. It worked in previous iterations, but currently returns a similar result to our REF() method. . As a result, CREF() is also broken because it relies on RREF().
Scrollbar on steps panel: We attempted to implement a scrollbar for the steps panel for calculations that took many steps. However, this did not show up in the panel. The code remains in CalculatorPanel.java. 
Future improvements: Nonsquare matrices: Currently, Matrix Calculator only takes in matrices with square dimensions. While most calculations beginners will try to do are for square matrices, it can be limiting. Implementing our calculations for nonsquare matrices requires a more complex algorithm that would be nice to implement in the future. 

Division of Labor:
Sravanti Tekumalla and Su Lin Blodgett worked on the algorithms for MatrixCalculator.java and implemented MatrixOps in Matrix.java together. Su Lin implemented SpecialMatrix.java while Sravanti implemented the Graphical User Interface (GUI). A more specific breakdown:

Matrix.java - Sravanti and Su Lin
MatrixCalculator.java - Sravanti and Su Lin
SpecialMatrix.java -Su Lin
MatrixCalculatorGUI.java - Sravanti. Includes: 
IntroPanel.java 
CalculationPanel.java 
TermsPanel.java 
StepsPanel.java

