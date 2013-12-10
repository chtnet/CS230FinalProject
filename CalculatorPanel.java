import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
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
    //setBackground (Color.blue);
    
    //String info for combo boxes
    String[] items = new String [6];
    items [0] = "---";
    items [1] = "1";
    items [2] = "2";
    items [3] = "3";
    items [4] = "4";
    items [5] = "5";
    m = new JComboBox (items);  
    //m.addActionListener(new ButtonListener());
    m.addItemListener(new ItemChangeListener());
    n = new JComboBox (items);
    //n.addActionListener(new ButtonListener());
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
    resultsPanel = new JPanel();
    centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout());
    centerPanel.add(matrixPanel);
    centerPanel.add(resultsPanel);
    
    southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout());
    enter = new JButton("Enter");
    calculate = new JButton("Calculate");
    calculate.setBackground(Color.GREEN);
    calculate.setOpaque(true);
    calculate.addActionListener(new ButtonListener());
    importMatrix = new JButton("Import matrix");
    clear = new JButton("Clear matrix");
    clear.setBackground(Color.RED);
    clear.setOpaque(true);
    clear.addActionListener(new ButtonListener());
    importMatrix.addActionListener(new ButtonListener());
    
    
    
    settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
    settingsPanel.add(new JLabel("Number of rows:"));
    settingsPanel.add(m);
    settingsPanel.add(new JLabel("Number of columns:"));
    settingsPanel.add(n);
    settingsPanel.add(new JLabel("Calculation:"));
    settingsPanel.add(calculation);
    settingsPanel.add(importMatrix);
    
    southPanel.add(clear);
    southPanel.add(calculate);
    
    
    add (new JLabel("<html><h2><center>Enter the dimensions and values for your matrix below. Alternatively, import a matrix.</center></h2></html>"), BorderLayout.NORTH);
    add (matrixPanel, BorderLayout.CENTER);
    add (settingsPanel, BorderLayout.WEST);
    add (southPanel, BorderLayout.SOUTH);
    
    
  }
  
  private void readIn(String textfile) {
    try {
      Scanner reader = new Scanner(new File(textfile));
      fileDimensions(reader);
      mc = new MatrixCalculator(mValue, nValue);
      for (int i = 0; reader.hasNextLine(); i++) {
        String[] nValues = reader.nextLine().split(",");
        for(int j = 0; j < nValue; j++)
          mc.getMatrix().setEntry(i, j, Double.parseDouble(nValues[j]));
      }
      JOptionPane.showMessageDialog( null, calculate, "Select which calculation you would like to perform", JOptionPane.QUESTION_MESSAGE);
      
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null,  "File not found. Please make sure file name is typed in correctly and includes extension .txt.");
    }  
  }
  
  private void fileDimensions(Scanner reader) {
    mValue = 0;
    nValue = 0;
    while(reader.hasNextLine()) nValue++;
    String[] numRows = reader.next().split(",");
    mValue = numRows.length;  
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      if(event.getSource() == importMatrix) {
        String fileName = JOptionPane.showInputDialog("Enter text file name to import matrix; file must be located in same directory." + "\n"
                                                        + "Also note that entries on the same line (e.g. within the same row" + "\n"
                                                        + "should be separated by integers.");
        readIn(fileName);
      }
      if(event.getSource() == calculate) {
        //System.out.println(mc.getMatrix());
        for(int i = 0; i < mValue; i++) {
          for(int j = 0; j < nValue; j++) {
            System.out.println(textFields[i][j]);
            mc.getMatrix().setEntry(i, j, Double.parseDouble(textFields[i][j].getText())); 
            System.out.println((double)Double.valueOf(textFields[i][j].getText()));
          }
        }
        if (calculation.getSelectedItem() == "Row-Reduced EF")
          resultsPanel.add(new JTextArea((mc.RREF(mc.getMatrix()).getMatrix()).toString())); 
        if (calculation.getSelectedItem() == "Column-Reduced EF")
          resultsPanel.add(new JTextArea((mc.CREF(mc.getMatrix()).getMatrix()).toString())); 
        if (calculation.getSelectedItem() == "Inverse")
          resultsPanel.add(new JTextArea((mc.inverse().getMatrix().toString()))); 
        if (calculation.getSelectedItem() == "Determinant")
          System.out.println(mc.determinant(mc.getMatrix())); 
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


