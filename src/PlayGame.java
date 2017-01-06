import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** The screen where the user plays the game.
  * The GameBoard is added to this screen.
  * The player may also choose to go back to pause the game or go back the main menu at any time.
  * 
  * @author Xin Hao Zhang
  * @version 5, June 13 2014
  */

public class PlayGame extends Screen
{
  
  
  /**
   * (int) The user's current score.
   */
  private int score;
  
  /**
   * (Reference) The game board.
   */
  PenguinBoard board;
  
  /**
   * (Reference) Label with the text "score".
   */
  private JLabel scores = createLabel ("Score: 0", 20);
  /**
   * (Reference) Label with the text "Lives".
   */
  private JLabel lives = createLabel ("Lives: 3", 20);
  /**
   * (Reference) Dialog containing a button to start the game.
   */
  private JDialog start;
  
  /**
   * (String) The level the user is playing.
   */
  private String level;
  
  /**
   * Constructor loads background image, sets score to 0, sets the layout, adds the game board and sets up the screen.
   * 
   * @param x (int) The horizontal length of the game board.
   * @param level (String) The level the user is playing at.
   */
  public PlayGame (int x, String level)
  {
    score = 0;
    this.level = level;
    board = new PenguinBoard (x, level, this);
    setLayout (new BorderLayout (0, 20));
    add (Box.createRigidArea (new Dimension((1280 - x)/2, 0)), BorderLayout.EAST);
    add (Box.createRigidArea (new Dimension((1280 -x)/2, 0)), BorderLayout.WEST);
    add (board, BorderLayout.CENTER);
    init();
  }
  
  /**
   * This method sets up the screen by adding buttons, labels and dialog windows to the screen.
   * The second block of code sets up the panel to be added to the bottom of the screen by adding the score and lives label
   * as well as the necessary buttons.
   * The third block of code sets up the start dialog box which is added when the game begins to allow the user to start the game.
   * 
   * @param bottomPanel (Reference) The panel added to the bottom of the screen containing the player's score, lives and the pause button.
   * @param pause (Reference) The button to pause the game.
   */
  private void init()
  {
    JPanel bottomPanel = new JPanel ();
    start = new JDialog ();
    
    bottomPanel.add (scores);
    bottomPanel.add (lives);
    bottomPanel.add (createButton ("Pause", 15));
    bottomPanel.add (createButton ("Main Menu", 15));
    add (bottomPanel, BorderLayout.SOUTH);
    bottomPanel.setLayout (new FlowLayout(FlowLayout.CENTER, 30, 0));
    
    start.add (createButton ("Start!", 20));
    start.setTitle (level);
    start.setSize (100, 100);
    start.setLocationRelativeTo (this);
    bottomPanel.setOpaque (false);
    
    start.setVisible (true);
  }
  
  /**
   * Updates the lives label to show the user how many lives they have left.
   * 
   * @param newLives (int) The number of lives the user has left.
   */
  public void updateLives (int newLives)
  {
    lives.setText("Lives: " + newLives);
  }
  
  /**
   * Updates the scores label to show how many points the user has scored.
   * 
   * @param newScore (int) The number of points to be added to the user's score.
   */
  public void updateScore (int newScore)
  {
    score+=newScore;
    scores.setText ("Score: " + score);
  }
  
  /**
   * The user has finished the level so change the screen to EndGame.
   */
  public void notifyWin ()
  {
    changeScreen (new EndGame(score, level), this);
  }
  
  /**
   * The user has lost all their lives so ask them if they want to restart the level.
   * <br>
   * The if structure checks to see if they wanted to restart or not.
   * <br>
   * If they selected yes, remove the current board, create a new one and add it.
   * <br>
   * If they did not want to restart, change the screen to the MainMenu.
   * 
   * @param n (int) The integer representation of the response from the user recieved from the button they clicked.
   */
  public void notifyLoss ()
  {
    int n = JOptionPane.showConfirmDialog(this,"Your penguin has been eaten! Restart?", "Game Over", JOptionPane.YES_NO_OPTION);
    if (n == JOptionPane.YES_OPTION)
    {
      score = 0;
      scores.setText("Score: 0");
      remove (board);
      board = new PenguinBoard (board.getWidth(), level, this);
      add (board, BorderLayout.CENTER);
      updateUI();
      start.setVisible (true);
    }
    else
    {
      changeScreen (new MainMenu(), this);
    }
  }
  
  /**
   * This method gets the action performed by the user and responds accordingly.
   * The timer from PenguinBoard is started if they did not choose to go back to the MainMenu.
   * <br>
   * If the user clicked Pause a dialog window will show and the timer from the board will stop until they choose to resume.
   * <br>
   * If they chose to go back to the MainMenu, change the screen.
   * <br>
   * Otherwise, they clicked to start the game, so stop displaying the start window.
   * 
   * @param ae (reference) The type of action that occured.
   */
  public void actionPerformed (ActionEvent ae)
  {
    board.t.stop();
    if (ae.getActionCommand().equals("Main Menu"))
    {
      changeScreen (new MainMenu (), this);
      return;
    }
    else if (ae.getActionCommand().equals("Pause"))
    {
      JOptionPane.showMessageDialog (this, "GAME PAUSED. Continue?", "PAUSE", JOptionPane.QUESTION_MESSAGE);
    }
    else
    {
      start.setVisible(false);
    }
    board.t.start();
    board.requestFocus();
  }
  
  
}