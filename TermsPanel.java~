import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TermsPanel extends JPanel
{
  //private SpecialMatrix specialMatrix;
  private CalculatorPanel cp;
  private SpecialMatrix sp;
  private JPanel propertiesPanel;
  private LinkedList<String> properties;
   //-----------------------------------------------------------------
   //  Sets up this panel with two labels.
   //-----------------------------------------------------------------
   public TermsPanel()
   {
     this.cp = cp;
     setBackground(new Color(82, 217, 141));
     propertiesPanel = new JPanel();  propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.Y_AXIS)); propertiesPanel.setBackground(new Color(82, 217, 141));
  
      JLabel l1 = new JLabel ("<html><h2 style ='font-family:Marker felt;color:#F7F5F7;font-size:36px;align:center'>Information about your matrix:</h2><br>");
      
      add (l1);
      add (propertiesPanel);
   }
   
   protected void setProperties(LinkedList<String> properties) { 
     for(int i = 0; i < properties.size(); i++) { 
     propertiesPanel.add(new JLabel("<html><p style ='font-family:Marker felt;color:#3399FF;font-size:16px;align:center'>" + properties.get(i) +"<br><br>")); 
     } 
     
   }
   
   protected JPanel getPropertiesPanel() {
    return propertiesPanel; 
   }
   

}