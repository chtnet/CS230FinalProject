//********************************************************************
//  MatrixCalculatorGUI.java       
//  Sets up the GUI for the program MatrixCalculator.java
//********************************************************************

import javax.swing.*;
import java.awt.*;

public class MatrixCalculatorGUI
{
   //-----------------------------------------------------------------
   //  Sets up a frame containing a tabbed pane.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Matrix Calculator");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JTabbedPane tp = new JTabbedPane();
      CalculatorPanel cp = new CalculatorPanel();
      tp.addTab ("Intro", new IntroPanel());
      tp.addTab ("Calculator", cp);
      tp.addTab ("Terms", new TermsPanel(cp));
      frame.getContentPane().add(tp);

      frame.pack();
      frame.setVisible(true);
   }
}
