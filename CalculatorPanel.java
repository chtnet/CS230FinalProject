/**
 * Calculator panel. Allows user to input values for a matrix or to import a text file with comma-separated values.
 * Displays calculations performed in MatrixCalculator in GUI.
 * 
 * @author Sravanti Tekumalla
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class CalculatorPanel extends JPanel
{
  
  private JComboBox dimension, calculation;
  private JButton enter, calculate, importMatrix, enterValues, clear, showSteps;
  private int mValue, nValue;
  private JPanel matrixPanel, settingsPanel, southPanel, centerPanel;
  private MatrixCalculator mc;
  private SpecialMatrix sm;
  private TermsPanel tp;
  private JTextField[][] textFields;
  
  
  /** 
   * Class constructor.
   */
  public CalculatorPanel(TermsPanel tp)
  {
    
    this.tp = tp;
    //sets up layout
    setLayout (new BorderLayout());
    setBackground(new Color(82, 217, 141));
    //initialize panels for layout
    matrixPanel = new JPanel(); matrixPanel.setBackground(new Color(82, 217, 141));
    settingsPanel = new JPanel(); settingsPanel.setLayout(new GridLayout(5,1)); settingsPanel.setBackground(new Color(82, 217, 141));
    southPanel = new JPanel(); southPanel.setBackground(new Color(82, 217, 141));
    
    //create and initialize information for combo boxes
    String[] items = new String [6];
    items [0] = "---";
    items [1] = "1";
    items [2] = "2";
    items [3] = "3";
    items [4] = "4";
    items [5] = "5";
    
    String[] items2 = new String[6];
    items2[0] = "---";
    items2[1] = "Row Echelon Form";
    items2[2] = "Row-reduced Echelon Form";
    items2[3] = "Column-Reduced Echelon Form";
    items2[4] = "Inverse";
    items2[5] = "Determinant";
    
    dimension = new JComboBox (items);  
    //n = new JComboBox (items);
    calculation = new JComboBox(items2);
    
    //set up size
    dimension.setPreferredSize(new Dimension(10,20));
    //n.setPreferredSize(new Dimension(10,20));
    
    
    //add listeners
    dimension.addItemListener(new ItemChangeListener());
    //n.addItemListener(new ItemChangeListener());
    calculation.addActionListener(new ButtonListener());
    
    
    //initialize buttons for south/west panel - includes HTML styling
    enter = new JButton("<html><p style ='font-family:Marker felt;color:#00CC00;font-size:15px;align:left'>Enter</p></html>");
    calculate = new JButton("<html><p style ='font-family:Marker felt;color:#00CC00;font-size:15px;align:left'>Calculate</p></html>");
    importMatrix = new JButton("<html><p style ='font-family:Marker felt;color:#9900CC;font-size:16px;align:left'>Import matrix</p></html>");
    clear = new JButton("<html><p style ='font-family:Marker felt;color:#FF0000;font-size:15px;align:left'>Clear</p></html>");
    showSteps = new JButton("<html><p style ='font-family:Marker felt;color:#0066FF;font-size:13px;align:left'>Show me the steps!</p></html>");
    
    //add listeners for buttons 
    clear.addActionListener(new ButtonListener());
    calculate.addActionListener(new ButtonListener());
    importMatrix.addActionListener(new ButtonListener());
    showSteps.addActionListener(new ButtonListener());
    //steps cannot be initialized until a calculation is performed
    showSteps.setVisible(false);
    showSteps.setEnabled(false);
    
    //initialize and add labels for buttons for west panel (settings)
    settingsPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#0066FF;font-size:13px;align:left'>Dimension of matrix:</p></html>"));
    settingsPanel.add(dimension);
    settingsPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#0066FF;font-size:13px;align:left'>Calculation</p></html>"));
    settingsPanel.add(calculation);
    settingsPanel.add(importMatrix);
    //add top label
    add (new JLabel("<html><h2 style ='font-family:Marker felt;color:#B20000;font-size:20px;align:center;text-decoration:underline;opacity:0.4'>"
                      + "Enter the dimensions and values for your matrix below. Alternatively, import a matrix.</h2></html>"), BorderLayout.NORTH);
    
    
    //add labels and buttons to south panel
    southPanel.add(clear);
    southPanel.add(calculate);
    southPanel.add(showSteps);
    //add top label
    add (new JLabel("<html><h2 style ='font-family:Marker felt;color:#B20000;font-size:20px;align:center;text-decoration:underline'>"
                      + "Enter the dimensions and values for your matrix below. Alternatively, import a matrix.</h2></html>"), BorderLayout.NORTH);
    
    //add panels to layout
    add (matrixPanel, BorderLayout.CENTER);
    add (settingsPanel, BorderLayout.WEST);
    add (southPanel, BorderLayout.SOUTH);
    
  }
  
  
  //section: helper methods
  
  /**
   * Helper method that obtains the Matrix Calculator used in Calculator Panel.
   */ 
  
  public MatrixCalculator getMC() {
    return mc; 
  }
  /**
   * Helper method to create a panel of the matrix displayed in a clean grid format. Used for center portion of CalculatorPanel.
   */ 
  private JPanel matrixLabel(Matrix matrix) {
    JPanel grid = new JPanel();
    //creates grid that has same dimensions of matrix
    grid.setLayout(new GridLayout(matrix.getRowCount(), matrix.getColumnCount())); 
    for(int i = 0; i < matrix.getRowCount(); i++) {
      for (int j = 0; j < matrix.getColumnCount(); j++) {
        grid.add(new JLabel( matrix.getEntry(i, j) + "  "));
      } 
    }
    return grid;
  }
  
  
  /**
   * Helper method to read in imported matrix and create a matrix based on imported values. 
   * Values must be 
   * Then proceeds to get information from user about what calculation needs to be performed.
   * Updates the GUI accordingly. 
   * 
   * @throws FileNotFoundException
   */ 
  private void readIn(String textfile) {
    try {
      
      //creates reader to read in imported file
      Scanner reader = new Scanner(new File(textfile));
      
      //finds dimensions of matrix
      fileDimensions(textfile);
      
      //creates matrix calculator based on imported values
      mc = new MatrixCalculator(mValue, nValue);
      for (int i = 0; reader.hasNextLine(); i++) {
        String[] nValues = reader.nextLine().split(",");
        for(int j = 0; j < nValue; j++) {
          mc.getMatrix().setEntry(i, j, Double.parseDouble(nValues[j]));
        }
      }
      
      //initialize combo box for popup pane
      String[] items2 = new String[6];
      items2[0] = "---";
      items2[1] = "Row Echelon Form";
      items2[2] = "Row-reduced Echelon Form";
      items2[3] = "Column-Reduced Echelon Form";
      items2[4] = "Inverse";
      items2[5] = "Determinant";
      String picked = (String)JOptionPane.showInputDialog(this, "Pick which Calculation:","Choose calculation",JOptionPane.QUESTION_MESSAGE ,null,items2,items2[0]);
      
      //perform calculation based on chosen value by user
      calculate(picked);
      
      //catches exception if user types in file that does not exist
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null,  "File not found. Please make sure file name is typed in correctly and includes extension .txt.", "File not found", JOptionPane.WARNING_MESSAGE);
    }  
  }
  
  /**
   * Helper method to find dimensions of imported matrix.
   * 
   * @throws FileNotFoundException
   */ 
  private void fileDimensions(String textFile) {
    try {
      Scanner reader = new Scanner(new File(textFile));
      
      System.out.println(Arrays.toString(reader.next().split(",")));
      mValue = reader.next().split(",").length;
      System.out.println(mValue);
      nValue = 2;
      while(reader.hasNextLine())  reader.next(); nValue++;
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null,  "File not found. Please make sure file name is typed in correctly and includes extension .txt.", "File not found", JOptionPane.WARNING_MESSAGE);
    }  
    
    
  }
  
  /**
   * Helper method to clear matrix calculator data and clear GUI.
   * 
   */ 
  private void clear() {
    //updates GUI
    tp.getPropertiesPanel().removeAll();
    matrixPanel.removeAll();
    //resets combo boxes
    dimension.setSelectedIndex(0);
    calculation.setSelectedIndex(0);
    mc = null;
    
    
  }
  
  /**
   * Helper method to calculate information about matrix based on user input.
   * 
   * @throws NumberFormatException
   */ 
  private void calculate(String s) {
    //makes sure user has selected calculation
    if(s == "---")
      JOptionPane.showMessageDialog(null, "Please enter which calculation you would like to perform", "Error", JOptionPane.ERROR_MESSAGE);
    
    else {
      if(textFields != null) {
        for(int i = 0; i < mValue; i++) {
          for(int j = 0; j < nValue; j++) {
            try {
              mc.getMatrix().setEntry(i, j, Double.parseDouble(textFields[i][j].getText()));
              //makes sure entries are valid numbers (no complex numbers a+bi)
              //clears matrix if entries are not correct and exits method (program freezes otherwise)
            } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Please enter a valid integer for every field (does not accept complex numbers)", "Error", JOptionPane.ERROR_MESSAGE);
              clear(); return;
            }
          }
          
        }
        
        //updates terms panel according to matrix entered
//        sm = new SpecialMatrix(mc.getMatrix());
//        tp.setProperties(sm.getProperties());
      }
      
      //clears matrix in GUI to populate with calculation result
      matrixPanel.removeAll();
      //matrixPanel.repaint();
      
      //performs appropriate calculation and updates GUI based on chosen calculation
      if (s.equals("Row Echelon Form")) 
        add(matrixLabel(mc.REF()), BorderLayout.CENTER);
      if (s.equals("Row-reduced Echelon Form")) 
        add(matrixLabel(mc.RREF()), BorderLayout.CENTER); 
      if (s.equals("Column-Reduced Echelon Form")) 
        add(matrixLabel(mc.CREF()), BorderLayout.CENTER); 
      if (s.equals("Inverse")) {
        //checks to make sure matrix is square - inverse does not exist otherwise
        if(mc.getMatrix().getRowCount() != mc.getMatrix().getColumnCount()) matrixPanel.add(new JLabel("Inverse could not be calculated because the matrix is not square."));
        else add(matrixLabel(mc.inverse()), BorderLayout.CENTER);
      }  
      if (s.equals("Determinant")) {
        //label instead of panel because determinant is an int value, not a matrix.
        JLabel result = new JLabel();
        //checks to make sure matrix is square - determinant is 0 otherwise
        if(mc.getMatrix().getRowCount() != mc.getMatrix().getColumnCount())   result = new JLabel("Determinant could not be calculated because the matrix is not square.");
        else  result = new JLabel(String.valueOf(mc.determinant(mc.getMatrix().getMatrix())));
        matrixPanel.add(result);
        matrixPanel.setBackground(new Color(82, 217, 141));
        add(matrixPanel, BorderLayout.CENTER);
        setComponentZOrder(matrixPanel, 0);
      }
      //now users can see the steps of their calculation if desired
      showSteps.setVisible(true);
      showSteps.setEnabled(true); 
    }  
  }
  
  //section: listeners
  
  
  
  /**
   * Implements Listeners for buttons for GUI.
   *
   */
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      //if user selects "Import Matrix"
      if(event.getSource() == importMatrix) {
        //obtain file name, read in values and figure out appropriate calculation
        readIn(new String( JOptionPane.showInputDialog("Enter text file name to import matrix; file must be located in same directory.\n"
                                                         + "Also note that entries on the same line (e.g. within the same row\n"
                                                         + "should be separated by integers.")));
      }
      if(event.getSource() == calculate) {
        //calculate based on user selection
        calculate((String)calculation.getSelectedItem());
      }
      
      if(event.getSource() == clear) {
        //resets matrix calculator and GUI
        clear(); 
      }
      
      if (event.getSource() == showSteps) {
        //initializes Steps Panel which stores calculation steps
        StepsPanel sp = new StepsPanel(mc);
        //creates a new frame for popup
        JFrame stepsFrame = new JFrame ("Steps");
        stepsFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        stepsFrame.getContentPane().add(sp);
        stepsFrame.pack();
        stepsFrame.setVisible(true);
        //attempt to add scroller to pane so user can view all steps (BUG: DOES NOT WORK CURRENTLY)
        JScrollPane scroller = new JScrollPane(sp);  
        stepsFrame.getContentPane().add(scroller, BorderLayout.CENTER); 
      }
    }
  }
  
  /**
   * Implements Listeners for combo boxes for GUI.
   * Note: Does not use ButtonListener because only ItemListener has 
   * ability to see if item selected is different from before
   *
   */
  
  private class ItemChangeListener implements ItemListener{
    public void itemStateChanged(ItemEvent event) {
      
      //in case user selects values for column and row lengths and then changes their mind
      if (event.getStateChange() == ItemEvent.SELECTED) {
        if(dimension.getSelectedItem() != "---")
        {
          //reset panel
          matrixPanel.removeAll();
          matrixPanel.repaint();
          
          //figure out updated values and create new matrix calculator accordingly
          mValue = Integer.valueOf((String)dimension.getSelectedItem());
          nValue = Integer.valueOf((String)dimension.getSelectedItem());
          mc = new MatrixCalculator(mValue, nValue);
          //initialize textfields for user input for matrix values
          textFields = new JTextField[mValue][nValue];
          //set up GUI, initializes all values to be 0 
          matrixPanel.setLayout(new GridLayout(mValue, nValue));
          for(int i = 0; i < mValue; i++) {
            for(int j = 0; j < nValue; j++) {
              textFields[i][j] = new JTextField();
              textFields[i][j].setText("0");
              matrixPanel.add(textFields[i][j]);
              
            }    
          }
        }
      }
    }       
  }
}


