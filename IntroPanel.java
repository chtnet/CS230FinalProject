import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel
{
   //-----------------------------------------------------------------
   //  Sets up this panel with two labels.
   //-----------------------------------------------------------------
   public IntroPanel()
   {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setBackground(new Color(206, 239, 242));

      JLabel l1 = new JLabel ("<html><h1 style ='font-family:Marker felt;color:#33CC33;font-size:36px;align:center'>Matrix Calculator</h1>");
      JLabel imgLabel = new JLabel(new ImageIcon("underline.gif"));
      JLabel l2 = new JLabel ("<html><p style ='font-family:Marker felt;color:#3399FF;font-size:16px;align:center'><i>Because matrix calculations suck let's be real here" 
                                + "</center></i></p></html>");
      JLabel l3 = new JLabel("<html><h3 style ='font-family:Marker felt;font-size:20px;text-decoration:underline'> A guide for using this calculator: </h3></html>");
      JLabel l4 = new JLabel("<html><ul>"
                               + "<li style ='font-family:Marker felt;font-size:16px;>This matrix can take in square and non-square matrices with maximum dimensions 4x4</li>"
                               + "<li style ='font-family:Marker felt;font-size:16px;> There is also the option to import a matrix, which has no dimension restrictions; separate each entry with a comma</li>"
                               + "<li style ='font-family:Marker felt;font-size:16px;> If an entry is not filled out, it will automatically set the entry to zero</li>"
                               + "<li style ='font-family:Marker felt;font-size:16px;> To find out special properties about the matrix you entered, click on the terms panel </li></ul></html>");
                               
      JLabel l5 = new JLabel("<html><h4 style ='font-family:Marker felt;color:#3399FF;font-size:12px;align:center'>Created by Sravanti Tekumalla and Su Lin Blodgett</h4></html>");                       
                              

      add (l1);
      add(imgLabel);
      add (l2);
      add (l3);
      add (l4);
      add (Box.createRigidArea (new Dimension (0, 50)));
      add (l5);
   }
}
