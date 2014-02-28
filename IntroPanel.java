/*
 * Intro panel. Explains the program and how to use all the features. 
 * 
 * @author Sravanti Tekumalla
 */ 

import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel
{
  /** 
   * Class constructor.
   */
  public IntroPanel()
  {
    
    JLabel imgLabel = new JLabel(new ImageIcon("IntroPanelPicture.jpg"));
    add(imgLabel);

  }
}
