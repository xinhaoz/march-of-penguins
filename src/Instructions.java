import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** This creates several labels and a button which will output the instructions and allow the user to return
  * to the main menu once they have finished reading them. 
  * 
  * @author Sabrina Cancelliere
  * @version 4, June 10 2014.
  */
public class Instructions extends Screen implements ActionListener
{
  
  
  /**
   * This method is the constructor, which sets the JPanel layout and calls the labelField method.
   */ 
  public Instructions ()
  {
    this.setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    labelField ();
  }
  
  /**
   * This method creates the labels and button which will output a the instructions and allow the user to return to 
   * the main menu once they have finished reading them. 
   * 
   * @param instructions (reference) Creates a JTextArea by referencing the JTextArea class.
   * @param title (reference) Creates a label for the screen's title.
   * @param back (reference) Allows the user to return to the main menu.   
   */
  public void labelField ()
  {
    JLabel title;
    JButton back = createButton ("Back", 15);
    title = new JLabel ("Instructions");
    title.setFont (new Font ("Century Gothic", Font.PLAIN, 28));
    JTextArea instructions = new JTextArea ("Your penguin will move across the virtual game board on its path to the mating \n"
                                              + " ground. However, you can not step on broken ice, and must find a path that \n"
                                              +" includes only solid ice, or fishing holes. If you land on a regular fishing \n"
                                              +" hole, you will be asked a general biology knowledge question in order to \n"
                                              +" catch food, such as krill, a small shrimp like crustacean which is a delicacy \n"
                                              +" among penguins. You will receive points for every correct answer. The game \n"
                                              +" may also include bonus fishing holes where you will be asked a difficult \n"
                                              +" question about Antarctic specific biology. Correct answers here will earn you \n"
                                              +" double points, as you will \"catch\" rarer food such as larger fish or squid. \n" 
                                              + " Meanwhile, predators move in predictable patterns across the board, and you \n" 
                                              +" must anticipate their movements to avoid them on your journey to the endpoint. \n"
                                              +" For example, Skuas may fly across the board, and can \"swoop\" down to attack \n"
                                              + " you at any time. If you are caught by a predator, such as a Skua or Leopard Seal, \n"
                                              +" your penguin will be eaten, and you will lose points and one life.If you run \n"
                                              +" out of lives, you have the option to restart the level, or to return to the main menu.");
    
    
    instructions.setLineWrap (true);
    instructions.setOpaque (false);
    instructions.setEditable (false);
    instructions.setFont (new Font ("century gothic", Font.PLAIN, 20));
    instructions.setMaximumSize (new Dimension (850, 400));
    title.setAlignmentX (Container.CENTER_ALIGNMENT);
    back.setAlignmentX (Container.CENTER_ALIGNMENT);
    
    add (Box.createRigidArea (new Dimension (0, 50)));
    add (title);
    add (Box.createRigidArea (new Dimension (0, 20)));
    add (instructions);
    add (back);
  }
  
  
  /** This method returns to the main menu when the "Back" button has been clicked.  
    * @param ae (reference)  The type of action that occured. 
    */
  public void actionPerformed (ActionEvent ae)
  {
    changeScreen (new MainMenu (), this);
  }
}