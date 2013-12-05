import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel
{
   //-----------------------------------------------------------------
   //  Sets up this panel with two labels.
   //-----------------------------------------------------------------
   public IntroPanel()
   {
      setBackground (Color.blue);

      JLabel l1 = new JLabel ("Matrix Calculator");
      JLabel l2 = new JLabel ("This is how our program works.");
      add (l1);
      add (l2);
   }
}
