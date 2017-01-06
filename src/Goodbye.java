import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The screen that displays when the user wants to exit the program.
 * The program will close when the user clicks the quit button.
 * 
 * @author Sabrina Cancelliere and Xin Hao Zhang
 * @version 4, June 6 2014
 */

public class Goodbye extends Screen
{
  
  /**
   * Constructor which sets the JPanel layout and calls the labelField method.
   */ 
  public Goodbye ()
  {
    this.setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    labelField ();
  }
  
  /**
   * This method creates and adds the labels and button which will output a goodbye message to the user, credit the
   * programmers and allow the user to quit the game.
   * 
   * @param title (reference) Creates a label for the screen's title.
   * @param message1 (reference) Creates a label for the first line of text to be outputted to the user.
   * @param message2 (reference) Creates a label for the second line of text to be outputted to the user.
   * @param quit (reference) Allows the user to quit the game.   
   */
  public void labelField ()
  {
    JLabel title = createLabel ("Goodbye!", 28);
    JButton quit = createButton ("Quit", 15);
    JLabel message1 = createLabel ("Thank you for playing March of the Penguins!", 20);
    JLabel message2 = createLabel ("This game was created by Sabrina Cancelliere and Xin Hao Zhang. (2014)", 20);
    
    title.setAlignmentX (Component.CENTER_ALIGNMENT);
    message1.setAlignmentX (Component.CENTER_ALIGNMENT);
    message2.setAlignmentX (Component.CENTER_ALIGNMENT);
    quit.setAlignmentX (Component.CENTER_ALIGNMENT);
    
    add (Box.createRigidArea (new Dimension (0, 50)));
    add (title);
    add (Box.createRigidArea (new Dimension (0, 50)));
    add (message1);
    add (message2);
    add (Box.createRigidArea (new Dimension (0, 100)));
    add (quit);
  }
  
  
  /** This method exits the game and closes the window when the "Quit" button has been clicked.  
    * 
    * @param ae (reference) The type of action that occured.
    */
  public void actionPerformed (ActionEvent ae)
  {
    System.exit(0);
  }
}