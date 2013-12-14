//********************************************************************
//  MatrixCalculatorGUI.java       
//  Sets up the GUI for the program MatrixCalculator.java
//********************************************************************

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StepsPanel extends JPanel
{
  
  private MatrixCalculator mc;
  private LinkedList<String> steps; 
  
  public StepsPanel(MatrixCalculator mc) {
   this.mc = mc;
   steps = mc.getSteps();
   steps.addFirst("<html><p style ='font-family:Marker felt;font-size:20px;text-decoration:underline'>Steps:</p><br><br>");
   steps.addFirst(mc.getMatrix().toString());
   steps.addFirst("<html><p style ='font-family:Marker felt;font-size:20px;text-decoration:underline'>Original matrix:</p><br><br>");
  
   
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
   for(int i = 0; i < steps.size(); i++) {
    add(new JLabel("<html><p style ='font-family:Marker felt;font-size:15px;'>" + steps.get(i) + "</p><br></html>")); 
     
   }
   
    
  }
//  
//    private JPanel matrixLabel(Matrix matrix) {
//    JPanel grid = new JPanel();
//    grid.setLayout(new GridLayout(matrix.getRowCount(), matrix.getColumnCount()));
////   String html = "<html><h2 style ='font-family:Marker felt;font-size:20px;align:center'>Matrix result</h1></html>"
////     + "<p style ='font-family:Marker felt;font-size:16px;align:center'>";
//    for(int i = 0; i < matrix.getRowCount(); i++) {
//      for (int j = 0; j < matrix.getColumnCount(); j++) {
//        grid.add(new JLabel( matrix.getEntry(i, j) + "  "));
//      }
//      
//    }
//    return grid;
//  }
}
