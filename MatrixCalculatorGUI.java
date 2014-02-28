/**
 * This is the GUI for the Matrix Calculator program. 
 * It implements a three-tab pane: 
 * An introduction panel that explains the program and how to use all the features 
 * A calculation panel which allows the user to either input a matrix
 * or enter in values for a matrix up to 5x5 in dimension. 
 * The user can also choose between calculating inverse, determinant, row-reduced echelon form or column-reduced echelon form 
 * A terms panel which allows the user to view information about the matrix entered. Includes matrix properties and definitions. 
 * 
 * 
 * @author Sravanti Tekumalla
 */

import javax.swing.*;
import java.awt.*;


public class MatrixCalculatorGUI
{
  /**
 * Sets up the three-tabed pane.
 * 
 * @param args 
 */
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Matrix Calculator");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(new Dimension(1024, 768));

      JTabbedPane tp = new JTabbedPane();
      TermsPanel terms = new TermsPanel();
      CalculatorPanel cp = new CalculatorPanel(terms); //to communicate results of calculatorPanel to termsPanel
      tp.addTab ("Intro", new IntroPanel());
      tp.addTab ("Calculator", cp);
      tp.addTab ("Terms", terms);
      frame.getContentPane().add(tp);

      frame.pack();
      frame.setVisible(true);
   }
}
