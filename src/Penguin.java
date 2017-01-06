import java.awt.*;

/** 
 * The game piece that the player controls.
 * 
 * @author Xin Hao Zhang
 * @version 5, June 13 2014
 */

public class Penguin extends GamePiece
{
  
  /**
   * (int) The number of lives the penguin has.
   */
  private int lives;
  
  /**
   * Constructor sends xy coordinates, dimensions and the board the Penguin is on to parent constructor and sets lives to 3.
   * 
   * @param x (int) Penguin's x coordinate.
   * @param y (int) Penguin's y coordinate.
   * @param width (int) Penguin's width in pixels (horizontal length).
   * @param height (int) Penguin's height in pixels.
   * @param pb (reference) The game board the Penguin belongs to.
   */
  public Penguin (int x, int y, int width, int height, PenguinBoard pb)
  {
    super (x, y, width, height, "peng.png");
    lives = 3;
  }
  
  /**
   * Returns the number of lives the penguin currently has.
   * 
   * @returns The variable 'lives' which is the number of lives the penguin has.
   */
  public int getLives ()
  {
    return lives;
  }
  
  
  /**
   * Removes a life from the penguin.
   */
  public void removeLife ()
  {
    lives--;
  }
  
  /**
   * Returns a Rectangle representation of the Penguin using its location and dimensions.
   * Used for collision detection.
   * 
   * @return (Rectangle) A Rectangle that represents the Penguin.
   */
  public Rectangle getBounds()
  {
    return new Rectangle (getX() + 1, getY() + 1, 49, 49);
  }
  
  
  
}