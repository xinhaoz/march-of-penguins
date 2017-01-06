/** 
 * An enemy GamePiece that resembles a Seal. It moves up and down the board.
 * 
 * @author Sabrina Cancelliere and Xin Hao Zhang
 * @version 4, June 6 2014
 */

public class Seal extends GamePiece
{
  
  /**
   * (int) The direction of the Seal.
   */
  private int dy;
  
  /**
   * (reference) The board the Seal is on.
   */
  private PenguinBoard pb;
  
  /**
   * Constructor sends the xy coordinates, dimensions and file name of the Seal to the parent constructor.
   * Initializes pb and sets dy to 1 and adds the GamePiece to the players array in the PenguinBoard.
   * 
   * @param x (int) Seal's x coordinates.
   * @param y (int) Seal's y coordinates.
   * @param width (int) Seal's horizontal length in pixels.
   * @param height (int) Seal's height in pixels
   * @param pb (Reference) The board the Seal is on.
   */
  public Seal (int x, int y, int width, int height, PenguinBoard pb)
  {
    super (x, y, width, height, "seal.png");
    this.pb = pb;
    dy = 1;
    pb.players.add (this);
  }
  
  /**
   * Moves the Seal by changing its x, y coordinates.
   * 
   * The if structure determines what direction the Seal should be going.
   * If it hits the bottom of the board, go upwards and if it hits the top of board, go downwards.
   * 
   * If determines whether to move the Seal upwards or downwards.
   */
  public void move ()
  {
    if (getY() >= 655 - getHeight() && dy > 0)
    {
      dy = -1;
    }
    else
    {
      if (getY() <= 0 && dy < 0)
        dy = 1;
    }
    setY (getY()+ dy);
  }
  
  
}