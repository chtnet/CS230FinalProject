import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class CalculatorPanel extends JPanel
{
  
  private JComboBox m, n, calculation;
  private JButton enter, calculate, importMatrix, enterValues;
  private int mValue, nValue;
  private JPanel matrixPanel, settingsPanel;
  private MatrixCalculator mc;
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
    m.addActionListener(new ButtonListener());
    n = new JComboBox (items);
    n.addActionListener(new ButtonListener());
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
    enter = new JButton("Enter");
    calculate = new JButton("Calculate");
    importMatrix = new JButton("Import matrix");
    importMatrix.addActionListener(new ButtonListener());
    calculate.setEnabled(false);
    
    
    
    settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
    settingsPanel.add(new JLabel("Number of rows:"));
    settingsPanel.add(m);
    settingsPanel.add(new JLabel("Number of columns:"));
    settingsPanel.add(n);
    settingsPanel.add(new JLabel("Calculation:"));
    settingsPanel.add(calculation);
    settingsPanel.add(importMatrix);
    
    add (new JLabel("<html><h2><center>Enter the dimensions and values for your matrix below. Alternatively, import a matrix.</center></h2></html>"), BorderLayout.NORTH);
    add (matrixPanel, BorderLayout.CENTER);
    add (settingsPanel, BorderLayout.WEST);
    add (calculate, BorderLayout.SOUTH);
    
    
  }
  
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      
      
      if(n.getSelectedItem() != "---" && m.getSelectedItem() != "---")
      {
        calculate.setEnabled(true);
        
        mValue = Integer.valueOf((String)m.getSelectedItem());
        nValue = Integer.valueOf((String)n.getSelectedItem());
        mc = new MatrixCalculator(mValue, nValue);
        
        matrixPanel.setLayout(new GridLayout(mValue, nValue));
        for(int i = 0; i < mValue*nValue; i++) {
          JTextField tf = new JTextField();
          tf.setPreferredSize( new Dimension( 5, 10 ) );
          matrixPanel.add(tf);
        }    
      }
      if(event.getSource() == importMatrix) {
        String fileName = JOptionPane.showInputDialog("Enter text file name to import matrix; file must be located in same directory." + "\n"
                                                        + "Also note that entries on the same line (e.g. within the same row" + "\n"
                                                        + "should be separated by integers.");
      }
      
      if(event.getSource() == calculate) {
  
      }
            
                              
                             
                         
    }
  }
}
