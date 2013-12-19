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
  private int countFiles; //to enable creation of multiple files to show steps
  
  /** 
   * Class constructor.
   */
  public StepsPanel(MatrixCalculator mc) {
    this.mc = mc;
    //gets information from MatrixCalculator to display steps
    steps = mc.getSteps();
    
    //initialize and add save button to frame
    save = new JButton("Save steps to text file");
    save.addActionListener(new ButtonListener());
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    add(save);
    
    //adds header to panel with original matrix
    steps.addFirst("<html><p style ='font-family:Marker felt;font-size:16px;text-decoration:underline'>Steps:</p>");
    steps.addFirst(mc.getMatrix().toString());
    steps.addFirst("<html><p style ='font-family:Marker felt;font-size:16px;text-decoration:underline'>Original matrix:</p>");
    
    
    //prints out the calculation steps
    for(int i = 0; i < steps.size(); i++) {
      add(new JLabel("<html><p style ='font-family:Marker felt;font-size:12px;'>" + steps.get(i) + "</p><br></html>"));
      JScrollPane scrollPane = new JScrollPane(this);
      scrollPane.setViewportView(this);
      setPreferredSize(new Dimension(110, 440));
      
    }  
  }
  
  /**
   * Implements Listener for save button. Writes steps to a new file saved in the same directory. 
   * Each file has a different name so files are never overwritten, allowing the user to save steps
   * for several calculations and for several matrices. 
   *
   * @throws IOException
   */
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      if(event.getSource() ==  save) {
        try {
          //creates writer with new file name each time
          PrintWriter writer = new PrintWriter(new File("steps"+countFiles+".txt"));
          
          //writes steps to file
          for(int i = 0; i < steps.size(); i++) 
            writer.println(steps.get(i));
          
          //clean up
          writer.close();
          
          //increment files to avoid file duplication
          countFiles++;
          
          //diplays message with name of file
          JOptionPane.showMessageDialog(null, "File saved as steps"+countFiles+".txt");
          
          //catches exception if file cannot be written
        } catch (IOException e) {
          JOptionPane.showMessageDialog(null, "Could not write to file.");
        }
      }
    }
  }
}
