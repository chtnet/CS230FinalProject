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

      JLabel l1 = new JLabel ("<html><h1 style ='font-family:times;color:red;font-size:30px;'><center>Matrix Calculator</center></h1>");
      JLabel l2 = new JLabel ("<html><p><i><center>A tool to reduce the pain of matrix calculations" 
                                + " and learn something along the way</center></i></p></html>");
      JLabel l3 = new JLabel("<html><h3 style = 'font-family:times;;font-size:20px;'> A guide for using this calculator: </h3></html>");
      JLabel l4 = new JLabel("<html><ul>"
                               + "<li> This matrix can take in square and non-square matrices with maximum dimensions 4x4</li>"
                               + "<li> There is also the option to import a matrix, which has no dimension restrictions; separate each entry with a comma</li>"
                               + "<li> If an entry is not filled out, it will automatically set the entry to zero</li>"
                               + "<li> To find out special properties about the matrix you entered, click on the terms panel </li> </ul></html>");
                               
      JLabel l5 = new JLabel("<html><h6>Created by Sravanti Tekumalla and Su Lin Blodgett</h6></html>");                       
                              

      add (l1);
      add (l2);
      add (l3);
      add (l4);
      add (Box.createRigidArea (new Dimension (0, 50)));
      add (l5);
   }
}
