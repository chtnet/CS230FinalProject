/**
 * Steps Panel which displays steps based on chosen matrix calculation. 
 * This panel is implemented in a new frame opened when a user selects "Show me the steps!" after choosing a matrix calculation 
 * Takes in a matrix calculator to print out steps of calculation.
 * 
 * @author Sravanti Tekumalla
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class StepsPanel extends JPanel
{
  
  private MatrixCalculator mc;
  private LinkedList<String> steps; 
  private JButton save;
  private int countFiles; //to enable creation of multiple step files
  public StepsPanel(MatrixCalculator mc) {
    this.mc = mc;
    steps = mc.getSteps();
    save = new JButton("Save steps to text file");
    save.addActionListener(new ButtonListener());
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    
    //adds header to panel with original matrix
    steps.addFirst("<html><p style ='font-family:Marker felt;font-size:16px;text-decoration:underline'>Steps:</p>");
    steps.addFirst(mc.getMatrix().toString());
    steps.addFirst("<html><p style ='font-family:Marker felt;font-size:16px;text-decoration:underline'>Original matrix:</p>");
    
    add(save);
    //prints out the calculation steps
    for(int i = 0; i < steps.size(); i++) {
      add(new JLabel("<html><p style ='font-family:Marker felt;font-size:12px;'>" + steps.get(i) + "</p><br></html>"));
      JScrollPane scrollPane = new JScrollPane(this);
      scrollPane.setViewportView(this);
      setPreferredSize(new Dimension(110, 440));
      
    }  
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      if(event.getSource() ==  save) {
        try {
          PrintWriter writer = new PrintWriter(new File("steps"+countFiles+".txt"));
          for(int i = 0; i < steps.size(); i++) 
            writer.println(steps.get(i));
          writer.close();
          countFiles++;
          JOptionPane.showMessageDialog(null, "File saved as steps"+countFiles+".txt");
        } catch (IOException e) {
          JOptionPane.showMessageDialog(null, "Could not write to file.");
        }
      }
    }
  }
}
