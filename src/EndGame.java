import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** This creates several labels and a textfield as well as buttons which will display the user's score
  * after they have completed the game and store their username.  
  * 
  * @author Sabrina Cancelliere
  * @version 4, June 10 2014.
  */

public class EndGame extends Screen implements ActionListener
{
  
  /**
   * (reference) Creates a text field which stores user input.
   */
  JTextField inputField = new JTextField (20);
  /**
   * (String) Stores the current level.
   */
  String level;
  /** 
   * (int) Stores the user's score.
   */
  int score;
  
  /**This method is the constructor, which sets the JPanel layout, sets the values of score
    * as well as level and calls the labelField method.
    * 
    * @param level (String) Stores the current level.
    * @param score (int) Stores the user's score.
    */ 
  public EndGame (int score, String level)
  {
    this.score = score;
    this.level = level;
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    labelField ();
  }
  
  /**
   * This method creates labels, text fields and buttons which will congratualte the user, output their score and 
   * prompt for as well as store their username. 
   * 
   * @param title (reference) Creates a label for the screen's title.
   * @param cont (reference) Allows the user to submit their username and leave the endgame screen.  
   * @param back (reference) Creates a button allowing the user to return to the main menu.
   * @param message1 (reference) Creates a JLabel by referencing the JLabel class.
   * @param message1 (reference) Creates a JLabel by referencing the JLabel class.
   * @param message1 (reference) Creates a JLabel by referencing the JLabel class.
   */
  public void labelField ()
  {
    JLabel title = createLabel ("Congratulations!", 28);
    JButton cont = createButton ("Continue", 15);
    JButton back = createButton ("Main Menu", 15);
    JLabel message1 = createLabel ("You have reached the mating grounds!", 20);
    JLabel message2 = createLabel ("Your score is: "+ score, 20);
    JLabel message3 = createLabel ("Please enter your username to be considered for high scores: ", 20);
    
    title.setAlignmentX (Component.CENTER_ALIGNMENT);
    message1.setAlignmentX (Component.CENTER_ALIGNMENT);
    message2.setAlignmentX (Component.CENTER_ALIGNMENT);
    message3.setAlignmentX (Component.CENTER_ALIGNMENT);
    inputField.setAlignmentX (Component.CENTER_ALIGNMENT);
    inputField.setMaximumSize (inputField.getPreferredSize());
    
    add (Box.createRigidArea (new Dimension(0, 50)));
    add (title);
    add (Box.createRigidArea (new Dimension(0, 100)));
    add (message1);
    add (message2);
    add (message3);
    add (inputField);
    add (cont);
    add (back);
  }
  
  
  /** This method stores the username and exits the endgame screen when the "Submit" button had been clicked. 
    * 
    * The first if structure is resposible for ensuring that the username entered was acceptable.
    * The next if structure is responsible for checking which button has been clicked.
    * The last nested if structures are responsible for determining how to proceed based on what level the user is
    * currently on. 
    * 
    * @param ae (reference) Creates an action even to be used in the conditional structure.
    */
  public void actionPerformed (ActionEvent ae)
  {
    String username = inputField.getText();
    if (username.length() > 10 || username.length() == 0 || username.contains (" "))
    {
      JOptionPane.showMessageDialog (this, "You must submit a username that does not exceed 10 characters or contain spaces!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    new HighScores (score, username, level);
    if (ae.getActionCommand ( ).equals ("Continue") && !level.equals ("Hard"))
    {
      if (level.equals ("Easy"))
      {
        changeScreen (new PlayGame (1100, "Medium"), this);
      }
      else 
      {
        if (level.equals ("Medium"))
        {
          changeScreen (new PlayGame (1240, "Hard"), this);
        }
      }
    }
    else
    {
      if ((ae.getActionCommand ().equals ("Continue") &&level.equals ("Hard")) || ae.getActionCommand ().equals ("Main Menu"))
      {
        changeScreen (new MainMenu(), this);
      }
    }
  }
}