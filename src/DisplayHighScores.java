import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** This class displays the top ten scores to the user and contains a button to print them, 
  * a button to clear them and a button to return to the main menu.
  * 
  * @author Sabrina Cancelliere and Xin Hao Zhang
  * @version 4, June 13 2014
  */
public class DisplayHighScores extends Screen
{
  
  /** @param hs (HighScores) References the HighScores class to allow its methods to be used.
    * @param usernames (String array) This stores the top ten usernames and according game levels to be displayed.
    * @param scores (String array) This stores the top ten scores to be displayed.
    */
  HighScores hs;
  String [] usernames = new String [10];
  String [] scores = new String [10];
  
  /** This is the constructor of the DisplayHighScores class which sets the layout and calls the labelField method.
    */
  public DisplayHighScores ()
  {
    this.setLayout (new BoxLayout(this, BoxLayout.Y_AXIS));
    labelField ();
  }
  
  /** This method is reponsible for displaying the high scores and buttons.
    * 
    * @param scoresPanel (reference) References the JPanel class to create a new JPanel.
    * @param buttonPanel (reference) References the JPanel class to create a new JPanel.
    * @param title (reference) References the JLabel class to create a JLabel containing the title.
    * 
    * The for loop is responsible for adding the top ten usernames, scores and according levels to the 
    */
  public void labelField ()
  {
    JPanel scoresPanel = new JPanel (new GridLayout (11, 3, 150, 20));
    JPanel buttonPanel = new JPanel (new FlowLayout(FlowLayout.CENTER, 20, 0));
    JLabel title;
    hs = new HighScores (scores, usernames);
    
    scoresPanel.add(createLabel ("Username", 20));
    scoresPanel.add(createLabel ("Score", 20));
    scoresPanel.add(createLabel ("Level", 20));
    
    for (int x = 0; x < 10; x++)
    {
      scoresPanel.add(createLabel ((x + 1) + ". " + usernames [x].substring (0, usernames [x].indexOf (" ")+1), 15));
      scoresPanel.add(createLabel (scores [x], 15));
      scoresPanel.add (createLabel(usernames [x].substring (usernames [x].indexOf (" ")+1), 15));
    }
    
    title = createLabel  ("HighScores", 30);
    buttonPanel.add (createButton ("Back", 15));
    buttonPanel.add (createButton ("Print", 15));
    buttonPanel.add (createButton ("Clear", 15));
    title.setAlignmentX (Component.CENTER_ALIGNMENT);
    scoresPanel.setMaximumSize (scoresPanel.getPreferredSize());
    
    scoresPanel.setOpaque (false);
    buttonPanel.setOpaque (false);
    add (Box.createRigidArea (new Dimension (0, 20)));
    add (title);
    add (Box.createRigidArea (new Dimension (0, 50)));
    add (scoresPanel);
    add (buttonPanel, BorderLayout.SOUTH);
  }
  
  /** This method prints the highscores file by implementing methods from the Printer class.
    * 
    * @param pr (reference) References the Printer class, which contains
    * methods to be implemented in the PrintScores class.
    * 
    * The for loop is used to print the top ten scores and according usernames as well as levels. 
    */
  public void printScores ()
  {
    Printer pr = new Printer ();
    hs = new HighScores (scores, usernames);
    pr.println ("March of the Penguins High Scores");
    pr.println ("Username", "Score", "Level");
    for (int x = 0; x < 10; x++)
    {
      pr.println (usernames [x].substring (0, usernames [x].indexOf (" ")+1), scores [x], usernames [x].substring (usernames [x].indexOf (" ")+1)); 
    }
    pr.printUsingDialog ();
  }
  
  /** This method returns to the main menu when the "Back" button has been clicked, clears the scores file
    * when "Clear" button is pressed or prints the top ten scores when the "Print" button has been clicked.  
    * 
    * @param ae (reference) Creates an action even to be used in the conditional structure. 
    * 
    * The nested if structure is resposible for checking which button has been clicked and respond accordingly.
    */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ( ).equals ("Back"))
    {
      changeScreen (new MainMenu (), this);
    }
    else if (ae.getActionCommand (). equals ("Clear"))
    {
      hs.printToFile (new int [10], new String [10]);
      removeAll();
      labelField ();
      updateUI();
    }
    else 
    {
      if (ae.getActionCommand ( ).equals ("Print"))
      { 
        printScores ();
      }
    }
  }
}