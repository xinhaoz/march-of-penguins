import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/** 
 * The program's splash screen that displays when you first start the program.
 * It welcomes the user to the game.
 * 
 * @author Xin Hao Zhang
 * @version 4, June 6 2014
 */
public class SplashScreen extends JWindow
{
  
  /**
   * (reference) The graphics of the splash screen.
   */
  ImageIcon img;
  
  /**
   * Constructor loads the image and adds it to the screen and sets the size of the splash screen to 500 x 500.
   * <br>
   * The try catch attempts to read the Image needed for the splash screen and catches any errors to prevent the program from crashing.
   * 
   * @param gif (reference) The JLabel containing the icon.
   * @param e The type of exception that occured.
   * @throws IOException If the Icon could not be read.
   */
  public SplashScreen ()
  {
    try
    {
      img = new ImageIcon(ImageIO.read (getClass().getResource("/resources/Splash.jpg")));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, "Image could not be loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    JLabel gif = new JLabel (img);
    add (gif);
    setSize (500, 500);
    setLocationRelativeTo (null);
    setVisible (true);
  }
  
}