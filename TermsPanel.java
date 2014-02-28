/**
 * Terms panel. Allows user to view special properties about the matrix they entered in Calculator Panel. 
 * @author Sravanti Tekumalla
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TermsPanel extends JPanel
{
  
  private CalculatorPanel cp;
  private SpecialMatrix sp;
  private JPanel propertiesPanel;
  private LinkedList<String> properties;
  
  /** 
   * Class constructor.
   */
  public TermsPanel()
  {
    //initialize instance variables
    this.cp = cp;
    setBackground(new Color(82, 217, 141));
    
    //set up properties panel
    propertiesPanel = new JPanel();  propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.Y_AXIS)); propertiesPanel.setBackground(new Color(82, 217, 141));
    
    //initialize label
    JLabel l1 = new JLabel ("<html><h2 style ='font-family:Marker felt;color:#F7F5F7;font-size:36px;align:center'>Information about your matrix:</h2><br>");
    
    //add label and panel to main terms panel
    add (l1);
    add (propertiesPanel);
  }
  
  /** 
   * Sets properties to display based on matrix entered in Calculator panel.
   * 
   */
  protected void setProperties(LinkedList<String> properties) { 
    for(int i = 0; i < properties.size(); i++) { 
      propertiesPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#3399FF;font-size:16px;align:center'>" + properties.get(i) +"<br><br>")); 
    } 
    
  }
    
  /**
   * Gets Properties Panel.
   * 
   * @return JPanel Properties Panel.
   */
  protected JPanel getPropertiesPanel() {
    return propertiesPanel; 
  }
  
  
}