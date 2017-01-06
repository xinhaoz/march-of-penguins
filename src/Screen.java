import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

/** 
 * A screen in the game. This abstract class provides the basic screen setup common to all screens in the game.
 * Includes operations such as loading and displaying the background image. Also provides methods that can be used
 * by screens to add properly formatted JLabels and JButtons.
 * 
 * @author Xin Hao Zhang
 * @version 5, June 13 2014
 */

public abstract class Screen extends JPanel implements ActionListener
{
  
  /**
   * (Reference) The background image.
   */
  private Image img;
  
  /**
   * Constructor loads the background image.
   * <br>
   * The try block is required to attempt to read an image file.
   * 
   * @param e The type of exception that occured.
   * @throws IOException If the Image could not be read.
   */
  public Screen ()
  {
    try
    {
      img = ImageIO.read (getClass().getResource ("/resources/" + "Background2.png"));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(this, "Image could not be loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * This method changes screens in the program.
   * It removes the current screen and replaces it with the new one.
   * <br>
   * If structure makes sure the currentScreen is present and needs to be removed before removing.
   * 
   * @param newScreen (reference) The screen to be added.
   * @param currentScreen (reference) The screen to be added.
   * @param frame (reference) The JFrame that the current screen belongs to.
   */
  public void changeScreen (Screen newScreen, Screen currentScreen)
  {
    JFrame frame = (JFrame) SwingUtilities.getRoot (currentScreen);
    if (currentScreen != null)
      frame.remove (currentScreen);
    frame.add (newScreen);
    frame.getContentPane().validate();
  }
  
  /**
   * Creates and returns a JLabel set to century gothic font at the specified size.
   * 
   * @param text (String) The text for the JLabel.
   * @param size (int) The size of the text.
   * 
   * @return (JLabel) A JLabel with the specified text at the specified font size.
   */
  public JLabel createLabel (String text, int size)
  {
    JLabel l = new JLabel (text);
    l.setFont (new Font("century gothic", Font.PLAIN, size));
    return l;
  }
  
  /**
   * This method creates and returns a button with the text in the paramter and adds action listeners to them.
   * It sets the text of the JButton to century gothic.
   * 
   * @param text (String) The text to put on the button.
   * @param button (reference) The button to be added to the main menu.
   * 
   * @return (JButton) A JButton with the specified text at the specified font.
   */
  public JButton createButton(String text, int size) 
  {
    JButton button = new JButton (text);
    button.setFont (new Font ("century gothic", Font.PLAIN, size));
    button.addActionListener(this);
    return button;
  }
  
  
  /**
   * Draws the graphics for the the screen/panel.
   * 
   * @param g (Reference) The Graphics object to be protected.
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawImage (img, 0, 0, null);
  }
}