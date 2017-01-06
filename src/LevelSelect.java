import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** This create a labels and buttons which allow the user to select from either easy medium or hard game levels.   
  * 
  * @author Sabrina Cancelliere
  * @version 4, June 10 2014.
  */
public class LevelSelect extends Screen
{

  /**This method is the constructor, which sets the JPanel layout and calls the labelField method.
   */
  public LevelSelect ()
  {
    this.setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    labelField ();
  }
  
  /** This method creates the label and button which allow the user to choose one of the three available
   * game levels (easy, medium or hard).
   * 
   * @param title (reference) Creates a label for the screen's title.
   * @param buttonPanel (reference) Creates a JPanel to hold the buttons.
   */
  public void labelField ()
  {
    JLabel title = createLabel ("Level Select", 28);
    JPanel buttonPanel = new JPanel (new GridLayout (3, 1, 0, 15));
    
    buttonPanel.add(createButton  ("Easy", 20));
    buttonPanel.add( createButton ("Medium", 20));
    buttonPanel.add(createButton ("Hard", 20));
    
    buttonPanel.setOpaque (false);
    buttonPanel.setMaximumSize (new Dimension (buttonPanel.getPreferredSize().width, buttonPanel.getPreferredSize().height));
    title.setAlignmentX (Component.CENTER_ALIGNMENT);
    
    add (Box.createRigidArea (new Dimension (0, 100)));
    add(title);
    add (Box.createRigidArea (new Dimension(0, 150)));
    add(buttonPanel);
  }
  
  
  /** This method chooses to continue to one of the three levels based on which button the user has clicked.  
    * The if structure is resposible for checking which button has been clicked (which level has been selected).
    * 
    * @param ae (reference) Creates an action even to be used in the conditional structure. 
    */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ( ).equals ("Easy"))
    {
      changeScreen (new PlayGame(1000, "Easy"), this);
    }
    else if (ae.getActionCommand ().equals ("Medium"))
    {
      changeScreen (new PlayGame(1100, "Medium"), this);
    }
    else
    {
      if (ae.getActionCommand ().equals ("Hard"))
      {
        changeScreen (new PlayGame(1240, "Hard"), this);
      }
    }
  }
}