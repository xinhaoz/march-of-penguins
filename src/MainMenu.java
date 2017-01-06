import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** 
 * The main menu screen of the game allows the user to navigate throughout the program.
 * 
 * @author Xin Hao Zhang
 * @version 4, June 6 2014
 */
public class MainMenu extends Screen
{
  
  /**
   * The constructor that sets the layout and calls the init method.
   */
  public MainMenu ()
  {
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    init();
  }
  
  /** 
   * This method adds components such as labels and buttons to the main menu.
   * 
   * @param title (reference) The title of the screen.
   * @param buttonPanel (Reference) The panel holding the buttons of the main menu, it uses the grid layout.
   */
  private void init()
  {
    JLabel title = createLabel ("Main Menu", 30);
    JPanel buttonPanel = new JPanel (new GridLayout (5, 1, 0, 5));
    buttonPanel.add(createButton ("Play", 20));
    buttonPanel.add(createButton ("Instructions", 20));
    buttonPanel.add (createButton ("Learn", 20));
    buttonPanel.add(createButton ("High Scores", 20));
    buttonPanel.add(createButton ("Quit", 20));
    
    title.setAlignmentX (Component.CENTER_ALIGNMENT);
    buttonPanel.setOpaque (false);
    buttonPanel.setMaximumSize (new Dimension(200, buttonPanel.getPreferredSize().height));
    
    add (Box.createRigidArea (new Dimension (0, 100)));
    add (title);
    add(Box.createRigidArea(new Dimension(0,150)));
    add (buttonPanel);
  }
  
  
  
  
  /**
   * This method gets the action performed by the user and responds accordingly based on the action's
   * returned String.
   * <br>
   * The if structure determines which button was pressed by getting the String representation of the action event.
   * It changes screens according to the returned String.
   * 
   * @param ae (reference) The type of action that occured.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand().equals("Play"))
    {
      changeScreen (new LevelSelect (), this);
    }
    else if (ae.getActionCommand().equals ("Instructions"))
    {
      changeScreen (new Instructions (), this);
    }
    else if (ae.getActionCommand().equals ("Learn"))
    {
      changeScreen (new Learn (), this);
    }
    else if (ae.getActionCommand().equals("High Scores"))
    {
      changeScreen (new DisplayHighScores (), this);
    }
    else
    {
      changeScreen (new Goodbye(), this);
    }
  }
  
}