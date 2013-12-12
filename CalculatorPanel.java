import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;




public class CalculatorPanel extends JPanel
{
  
  private JComboBox m, n, calculation;
  private JButton enter, calculate, importMatrix, enterValues, clear;
  private int mValue, nValue;
  private JPanel matrixPanel, settingsPanel, southPanel, resultsPanel, centerPanel;
  private MatrixCalculator mc;
  private JTextField[][] textFields;
  //-----------------------------------------------------------------
  //  Sets up this panel with two labels.
  //-----------------------------------------------------------------
  public CalculatorPanel()
  {
    
    setLayout (new BorderLayout());
    setBackground(new Color(206, 239, 242));
    
    //String info for combo boxes
    String[] items = new String [6];
    items [0] = "---";
    items [1] = "1";
    items [2] = "2";
    items [3] = "3";
    items [4] = "4";
    items [5] = "5";
    m = new JComboBox (items);  
    m.addItemListener(new ItemChangeListener());
    n = new JComboBox (items);
    n.addItemListener(new ItemChangeListener());
    m.setPreferredSize(new Dimension(10,20));
    n.setPreferredSize(new Dimension(10,20));
    
    
    
    String[] items2 = new String[5];
    items2[0] = "---";
    items2[1] = "Row-Reduced EF";
    items2[2] = "Column-Reduced EF";
    items2[3] = "Inverse";
    items2[4] = "Determinant";
    
    calculation = new JComboBox(items2);
    calculation.addActionListener(new ButtonListener());
    matrixPanel = new JPanel();
    settingsPanel = new JPanel();
    settingsPanel.setBackground(new Color(206, 239, 242));
    resultsPanel = new JPanel();
    resultsPanel.setBackground(new Color(206, 239, 242));
    centerPanel = new JPanel();
    centerPanel.setBackground(new Color(206, 239, 242));
    
    centerPanel.setLayout(new FlowLayout());
    centerPanel.add(matrixPanel);
    centerPanel.add(resultsPanel);
    
    southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout());
    enter = new JButton("<html><p style ='font-family:Marker felt;color:#00CC00;font-size:15px;align:left'>Enter</p></html>");
    calculate = new JButton("<html><p style ='font-family:Marker felt;color:#00CC00;font-size:15px;align:left'>Calculate</p></html>");
    calculate.addActionListener(new ButtonListener());
    importMatrix = new JButton("<html><p style ='font-family:Marker felt;color:#9900CC;font-size:16px;align:left'>Import matrix</p></html>");
    importMatrix.addActionListener(new ButtonListener());
    clear = new JButton("<html><p style ='font-family:Marker felt;color:#FF0000;font-size:15px;align:left'>Clear</p></html>");
    clear.addActionListener(new ButtonListener());
    importMatrix.addActionListener(new ButtonListener());
    
    
    
    settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
    settingsPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#0066FF;font-size:13px;align:left'>Number of rows:</p></html>"));
    settingsPanel.add(m);
    settingsPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#0066FF;font-size:13px;align:left'>Number of columns:</p></html>"));
    settingsPanel.add(n);
    settingsPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#0066FF;font-size:13px;align:left'>Calculation</p></html>"));
    settingsPanel.add(calculation);
    settingsPanel.add(importMatrix);
    
    southPanel.add(clear);
    southPanel.add(calculate);
    southPanel.setBackground(new Color(206, 239, 242));
    
    
    add (new JLabel("<html><h2 style ='font-family:Marker felt;color:#B20000;font-size:20px;align:center;text-decoration:underline'>"
                      + "Enter the dimensions and values for your matrix below. Alternatively, import a matrix.</h2></html>"), BorderLayout.NORTH);
    add (matrixPanel, BorderLayout.CENTER);
      
      
    add (settingsPanel, BorderLayout.WEST);
    add (southPanel, BorderLayout.SOUTH);
    add (resultsPanel, BorderLayout.EAST);
    
    
  }
  
  private void readIn(String textfile) {
    
    try {
      Scanner reader = new Scanner(new File(textfile));
      fileDimensions(textfile);
      mc = new MatrixCalculator(mValue, nValue);
      for (int i = 0; reader.hasNextLine(); i++) {
        String[] nValues = reader.nextLine().split(",");
        for(int j = 0; j < nValue; j++) {
          mc.getMatrix().setEntry(i, j, Double.parseDouble(nValues[j]));
        }
      }
      
      System.out.println(mc.getMatrix());
          
    String[] items2 = new String[5];
    items2[0] = "---";
    items2[1] = "Row-Reduced EF";
    items2[2] = "Column-Reduced EF";
    items2[3] = "Inverse";
    items2[4] = "Determinant";
 String picked = (String)JOptionPane.showInputDialog(this, "Pick a Calculation:","ComboBox Dialog",JOptionPane.QUESTION_MESSAGE ,null,items2,items2[0]);
 calculate(picked);
      //String picked = (String)  JOptionPane.showMessageDialog( null, calculation, "Select which calculation you would like to perform", JOptionPane.QUESTION_MESSAGE);
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null,  "File not found. Please make sure file name is typed in correctly and includes extension .txt.", "File not found", JOptionPane.WARNING_MESSAGE);
    }  
  }
  
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
  
  private void calculate(String s) {
    if(s == "---")
      JOptionPane.showMessageDialog(null, "Please enter which calculation you would like to perform", "Error", JOptionPane.ERROR_MESSAGE);
     
    else {
      if(textFields != null) {
      for(int i = 0; i < mValue; i++) {
        for(int j = 0; j < nValue; j++) {
          System.out.println(textFields[i][j]);
          mc.getMatrix().setEntry(i, j, Double.parseDouble(textFields[i][j].getText())); 
          System.out.println((double)Double.valueOf(textFields[i][j].getText()));
        }
      }
      }
      if (s == "Row-Reduced EF")
        resultsPanel.add(new JTextArea((mc.RREF(mc.getMatrix()).getMatrix()).toString())); matrixPanel.removeAll(); matrixPanel.add( new JTextArea(mc.stepsToString()));
      
      if (s == "Column-Reduced EF")
        resultsPanel.add(new JTextArea((mc.CREF(mc.getMatrix()).getMatrix()).toString()));  matrixPanel.removeAll(); matrixPanel.add( new JTextArea(mc.stepsToString()));
      
      if (s == "Inverse")
        resultsPanel.add(new JTextArea((mc.inverse().getMatrix().toString())));  matrixPanel.removeAll(); matrixPanel.add( new JTextArea(mc.stepsToString()));
      
      if (s == "Determinant")
        System.out.println(mc.determinant(mc.getMatrix()));  matrixPanel.repaint(); matrixPanel.add( new JTextArea(mc.stepsToString()));
      
    }
    
    
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      if(event.getSource() == importMatrix) {
        readIn(new String( JOptionPane.showInputDialog("Enter text file name to import matrix; file must be located in same directory." + "\n"
                                                         + "Also note that entries on the same line (e.g. within the same row" + "\n"
                                                         + "should be separated by integers.")));
        //System.out.println(fileName);
        
      }
      if(event.getSource() == calculate) {
        calculate((String)calculation.getSelectedItem());
      }
      
      if(event.getSource() == clear) {
        matrixPanel.removeAll();
        m.setSelectedIndex(0);
        n.setSelectedIndex(0);
        calculation.setSelectedIndex(0);
        mc = null;
        matrixPanel.repaint();
        
      } 
    }
  }
  
  private class ItemChangeListener implements ItemListener{
    public void itemStateChanged(ItemEvent event) {
      
      if (event.getStateChange() == ItemEvent.SELECTED) {
        if(n.getSelectedItem() != "---" && m.getSelectedItem() != "---")
        {
          matrixPanel.removeAll();
          matrixPanel.repaint();
          mValue = Integer.valueOf((String)m.getSelectedItem());
          nValue = Integer.valueOf((String)n.getSelectedItem());
          mc = new MatrixCalculator(mValue, nValue);
          System.out.println("MATRIX\n" + mc.getMatrix());
          textFields = new JTextField[mValue][nValue];
          System.out.println("TEXTFIELDS\n" + textFields);
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


