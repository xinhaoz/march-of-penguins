import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.*;
import java.io.*;

/** 
 * The game board where the game occurs. 
 * Players are added to interact on the game board. It is constantly being updated to show movement of the players.
 * 
 * @author Xin Hao Zhang
 * @version 4, June 6 2014
 */

public class PenguinBoard extends JPanel implements ActionListener, KeyListener
{
  
  /**
   * (int) The width of the board.
   */
  private int width;
  /**
   * (int) How long the penguin has had immunity from predators.
   */
  private int immunityCounter;
  /**
   * (reference) The player's Penguin.
   */
  private Penguin p;
  
  /**
   * (reference) The screen that the board belongs to.
   */
  private PlayGame pg;
  
  /**
   * (reference) The background image of the board.
   */
  private Image img;
  
  /**
   * (ArrayList of Integers) Contains the number of normal questions.
   */
  ArrayList <Integer> normalList;
  /**
   * (ArrayList of Integers) Contains the number of bonus questions.
   */
  ArrayList <Integer> bonusList;
  /**
   * (ArrayList of GamePieces) Contains the GamePieces on the board with the exception of the Penguin.
   */
  ArrayList<GamePiece> players;
  
  /**
   * (Reference) The timer thread object that sends messages to the action listener. Has a delay of 10 ms.
   */
  Timer t = new Timer (10, this);
  
  /**
   * Constructor sets up the board. Sets the size of the board, initializes width and pg, loads the background image,
   * adds a KeyListener, calls the methods to initialize the players and ArrayLists and starts the Timer thread.
   * 
   * @param width The horizontal length of the board.
   * @param level (String) The level the user is playing.
   * @param pg (reference) The screen that the board belongs to.
   */
  public PenguinBoard (int width, String level, PlayGame pg)
  {
    this.width = width;
    this.pg = pg;
    players = new ArrayList<GamePiece> ();
    bonusList = new ArrayList <Integer>();
    normalList = new ArrayList <Integer>();
    
    try
    {
      img = ImageIO.read(getClass().getResource ("/resources/" + "map.png"));
    }
    catch (IOException e)
    {
    }
    
    initPlayers (level);
    initArray (level);
    
    addKeyListener (this);
    setFocusable (true);
  }
  
  /**
   * Initializes and adds the required amount of FishingHoles and BonusHoles to the board and sets them up at random locations.
   * <br>
   * The if structures in this method check to see if the FishingHoles would collide with another GamePiece if added at the random location.
   * If they don't collide, add them at the location, otherwise try a different location.
   * 
   * @param numNorm (int) The number of normal questions for this game.
   * @param numBon (int) The number of bonus questions for this game.
   * @param norm (String) The file name for the normal questions.
   * @param bonus (String) The file name for the bonus questions.
   * @param randX (int) The x coordinate to place the hole.
   * @param randY (int) The y coordinate to place the hole.
   * @param x (int) Counter in the for loop.
   * @param f (reference) The FishingHole to be added.
   * @param b (reference) The BonusHole to be added.
   */
  private void initHoles (int numNorm, int numBon, String norm, String bonus)
  {
    int randX, randY;
    for (int x = 0; x < numNorm; x++)
    {
      randX = (int) (Math.random () * (width - 40));
      randY = (int) (Math.random () * (655 - 40));
      FishingHole f = new FishingHole (randX, randY, norm, pg);
      if (f.collidedWith (players) == null && (randX >= 50 || randY < 260 || randY >350))
        players.add (0, f);
      else
        x--;
    }
    for (int x = 0; x < numBon; x++)
    {
      randX = (int) (Math.random () * (width - 40));
      randY = (int) (Math.random () * (655 - 40));
      BonusHole b = new BonusHole (randX, randY, bonus, pg);
      if (b.collidedWith (players) == null && (randX >= 50 || randY < 260 || randY >350))
        players.add (0, b);
      else
        x--;
    }
  }
  
  /**
   * Initializes the players on the board depending on the level.
   * <br>
   * If structure checks to see which level the user is playing and adds the appropriate players for each level.
   * 
   * @param (String) The level the user is playing.
   */
  private void initPlayers (String level)
  {
    p = new Penguin (0,300, 50, 50, this);
    GamePiece goal = new GamePiece (width - 60, 297, 60, 60, "goal.png");
    
    players.add (goal);
    new Skua (width / 2, 655 / 2, 100, 88, this);
    
    if (level.equals ("Easy"))
    {
      new CrackedIce ((width - 80) / 2, 247, this);
      new CrackedIce ((width - 80) / 2, 327, this);
      new CrackedIce (210, 0, this);
      new CrackedIce (210, 80, this);
      new CrackedIce (710, 575, this);
      new CrackedIce (710, 495, this);
      new CrackedIce (710, 415, this);
      new CrackedIce (600, 0, this);
      initHoles(5, 2, "easy.scxhz", "easyBonus.scxhz");
    }
    else if (level.equals ("Medium"))
    {
      new CrackedIce (200, 0, this);
      new CrackedIce (200, 80, this);
      new CrackedIce (200, 160, this);
      new CrackedIce (240, 495, this);
      new CrackedIce (320, 495, this);
      new CrackedIce (400, 495, this);
      new CrackedIce (590, 247, this);
      new CrackedIce (510, 247, this);
      new CrackedIce (510, 327, this);
      new CrackedIce (430, 327, this);
      new CrackedIce (240, 415, this);
      new CrackedIce (940, 210, this);
      new CrackedIce (940, 290, this);
      new CrackedIce (940, 370, this);
      new Seal (300, 0, 62, 170, this);
      initHoles (10, 4, "medium.scxhz", "mediumBonus.scxhz");
    }
    else
    {
      new CrackedIce (150, 80, this);
      new CrackedIce (150, 240, this);
      new CrackedIce (150, 400, this);
      new CrackedIce (150, 575, this);
      new CrackedIce (400, 400, this);
      new CrackedIce (400, 480, this);
      new CrackedIce (480, 80, this);
      new CrackedIce (480, 160, this);
      new CrackedIce (1000, 160, this);
      new CrackedIce (1000, 240, this);
      new CrackedIce (1000, 400, this);
      new CrackedIce (1000, 480, this);
      new CrackedIce (620, 320, this);
      new CrackedIce (620, 400, this);
      
      new Seal (350, 460, 51, 140, this);
      new Seal (750, 0, 62, 170, this);
      new Skua (0, 0,100, 88, this);
      initHoles (15, 5, "hard.scxhz", "hardBonus.scxhz");
    }
  }
  
  /**
   * Returns the width (horizontal length) of the game board.
   * 
   * @return (int) The variable width which is the width (horizontal length) of the board.
   */
  public int getWidth ()
  {
    return width;
  }
  
  
  /**
   * Initializes normalList and bonusList with the appropriate number of questions based on the level the user is playing.
   * <br>
   * If structure determines which level the user is playing and sets the limiters accordingly.
   * <br>
   * The for loops initialize the ArrayLists. Both start at 1 and end at norm + 1 and bonus + 1 respectively.
   * 
   * @param level (String) The level the user is playing.
   * @param norm  (int) Stop value for the for loop initializing the normalList.
   * @param bonus (int) Stop value for the for loop initializing the bonusList.
   * @param z (int) Counter in for loop.
   */
  private void initArray (String level)
  {
    int norm, bonus;
    if (level.equals ("Easy"))
    {
      norm = 30;
      bonus = 10;
    }
    else if (level.equals ("Medium"))
    {
      norm = 45;
      bonus = 15;
    }
    else
    {
      norm = 60;
      bonus = 20;
    }
    for (int z = 1; z <= norm; z++)
    {
      normalList.add (new Integer (z));
    }
    for (int z = 1; z <= bonus; z++)
    {
      bonusList.add (new Integer (z));
    }
  }
  
  /**
   * This method moves the predators, repaints the screen and checks for collisions.
   * The for loop goes through all the GamePieces in the players ArrayList in order to move them.
   * 
   * @param ae (reference) The type of action that occured.
   * @param gp (reference) The GamePiece in the players ArrayList.
   */
  public void actionPerformed (ActionEvent ae)
  {
    for (GamePiece gp : players)
    {
      if (gp instanceof Seal || gp instanceof Skua)
        gp.move();
    }
    repaint();
    gameLogic();
  }
  
  /**
   * Checks to see if the Penguin collided and determines what to do when the Penguin piece collides with other game pieces.
   * <br>
   * The first if structure checks to see if the Penguin's immunity time is up and sets it back to 0 (no immunity).
   * <br>
   * The second if structure checks to see which kind of game piece the penguin collided with, if any and responds accordingly.
   * If it was a Seal or Skua, take away a life. If a FishingHole, ask a question, if the goal then end the game.
   * <br>
   * The final if checks if the Penguin has no more lives, in which case notify the player of a loss.
   * 
   * @param collision (reference) The GamePiece that Penguin collided with.
   * @param randNum (int) The question number to ask.
   * @param index (int) The location of the question in the array.
   */
  private void gameLogic()
  {
    GamePiece collision = p.collidedWith (players);
    if (immunityCounter == 130)
      immunityCounter = 0;
    else
    {
      if (immunityCounter > 0)
        immunityCounter++;
    }
    if (collision != null)
    {
      if ((collision instanceof Seal || collision instanceof Skua) && immunityCounter == 0)
      {
        p.removeLife();
        pg.updateLives(p.getLives());
        immunityCounter ++;
      }
      else if (collision instanceof FishingHole && ((FishingHole)collision).getIsDone() != true)
      {
        int randNum, index;
        if (collision instanceof BonusHole)
        {
          index = (int) (Math.random () * bonusList.size());
          randNum = bonusList.get(index);
          bonusList.remove (index);
        }
        else
        {
          index = (int) (Math.random() * normalList.size());
          randNum = normalList.get(index);
          normalList.remove (index);
        }
        pg.updateScore (((FishingHole)collision).askQuestion (randNum));
      }
      else
      {
        if (!(collision instanceof Seal || collision instanceof Skua || collision instanceof FishingHole || collision instanceof CrackedIce))
        {
          t.stop();
          pg.updateScore (10);
          pg.notifyWin ();
        }
      }
    }
    if (p.getLives() == 0)
    {
      t.stop();
      pg.notifyLoss ();
    }
  }
  
  /**
   * Draws all graphics onto the board.
   * 
   * @param g (Reference) Graphics object to protect.
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawImage (img, 0, 0, width, 655, null);
    for (GamePiece gp: players)
      gp.update(g);
    if (immunityCounter % 2 == 0)
      p.update (g);
  }
  
  /**
   * Invoked when a key has been pressed.
   * Moves the penguin player accordingly based on key input from the user.
   * <br>
   * If structure determines which of the four arrow keys were pressed and moves the Penguin accordingly.
   * The last if structure determines if the Penguin has collided with CrackedIce, and if so, move it back to the previous location.
   * 
   * @param k (Reference) An even which indicates that a keystroke occured.
   * @param prevX (int) The Penguin's previous xcoordinate.
   * @param prevY (int) The Penguin's previous ycoordinate.
   */
  public void keyPressed (KeyEvent k)
  {
    int prevX = p.getX(), prevY = p.getY();
    if (k.getKeyCode() == KeyEvent.VK_UP && p.getY() > 0)
    {
      p.setY (p.getY () - 10);
    }
    else if (k.getKeyCode() == KeyEvent.VK_DOWN && p.getY () < 655 - 50)
    {
      p.setY (p.getY () + 10);    
    }
    else if (k.getKeyCode() == KeyEvent.VK_RIGHT && p.getX() < this.getWidth()-50)
    {
      p.setX (p.getX () + 10);
    }
    else 
    {if (k.getKeyCode() == KeyEvent.VK_LEFT && p.getX() > 0)
      {
      p.setX (p.getX () - 10);
    }
    }
    if (p.collidedWith (players) instanceof CrackedIce)
    {
      p.setX(prevX);
      p.setY(prevY);
    }
  }
  
  
  /**
   * Invoked when a key has been released.
   * 
   * @param k (Reference) An even which indicates that a keystroke occured.
   */
  public void keyReleased (KeyEvent k)
  {
  }
  
  /**
   * Invoked when a key has been typed.
   * 
   * @param k (Reference) An even which indicates that a keystroke occured.
   */
  public void keyTyped (KeyEvent k)
  {
  }
  
}