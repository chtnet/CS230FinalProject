import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class CalculatorPanel extends JPanel
{
  
  private JComboBox m, n, calculation;
  private JButton enter, calculate;
  private int mValue, nValue;
  private JPanel matrixPanel;
  private MatrixCalculator mc;
   //-----------------------------------------------------------------
   //  Sets up this panel with two labels.
   //-----------------------------------------------------------------
   public CalculatorPanel()
   {
     
     
      setBackground (Color.blue);

    //String info for combo boxes
    String[] items = new String [6];
    items [0] = "---";
    items [1] = "1";
    items [2] = "2";
    items [3] = "3";
    items [4] = "4";
    items [5] = "5";
    m = new JComboBox (items);  
    n = new JComboBox (items);
    
    String[] items2 = new String[5];
    items2[0] = "---";
    items2[1] = "Row-Reduced EF";
    items2[2] = "Column-Reduced EF";
    items2[3] = "Inverse";
    items2[4] = "Determinant";
    
    matrixPanel = new JPanel();
    enter = new JButton("Enter");
    calculate = new JButton("Calculate");
    
   }
   
    private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      //checks to make sure all parameters have int value
      if(event.getSource() == enter) {
        if(n.getSelectedItem() == "---" || m.getSelectedItem() == "---")
        {
          //I have no idea why the interactions console doesn't like it when the user presses "OK" - have tried to debug - the program functions regardless
          JOptionPane.showMessageDialog(null,  "Please select an number value for each parameter");
        }
        mValue = Integer.valueOf((String)m.getSelectedItem());
        nValue = Integer.valueOf((String)n.getSelectedItem());
        mc = new MatrixCalculator(mValue, nValue);

    }
  }
}
}