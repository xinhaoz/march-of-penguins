/**
 * An enemy game piece representing the Skua bird. It moves around the board diagonally and changes directions when it hits a wall.
 * 
 * @author Sabrina Cancelliere
 * @version 4, June 6 2014
 */

public class Skua extends GamePiece
{
  
  /**
   * (reference) The board the Skua is on.
   */
  private PenguinBoard pb;
  /**
   * The horizontal speed of the Skua.
   */
  private int vy;
  /**
   * The vertical speed of the Skua.
   */
  private int vx;
  
  /**
   * Constructor sends the xy coordinates, dimensions and board the Skua is on to the parent constructor.
   * Sets the horizontal and vertical speed to 1 and adds the Skua to the players ArrayList in PenguinBoard.
   * 
   * @param x (int) Skua's x coordinate.
   * @param y (int) Skua's y coordinate.
   * @param width (int) Skua's horizontal length in pixels.
   * @param height (int) Skua's height in pixels
   * @param pb (Reference) The board the Skua is on.
   */
  public Skua (int x, int y, int width, int height, PenguinBoard pb)
  {
    super (x, y, width, height, "skua.png");
    this.pb = pb;
    this.vy = 1;
    this.vx = 1;
    pb.players.add (this);
  }
  
  /**
   * Moves the Skua around the game board by changing its xy coordinates.
   * <br>
   * The if structures checks to see if the Skua's horizontal or vertical speeds need to be changed depending on if it
   * hit a wall and which direction it was currently going. If the speeds need to be changed, it is generated randomly as
   * (-)1 or (-)2 in order to make the Skua's movements unpredictable.
   */
  public void move ()
  {
    if (getY() <= 0 && vy < 0 || (getX() <= 0 && vx < 0 && vy > 0))
    {
      vy = (int) (Math.random () * 2 + 1);
    }
    if ( (getY() >= pb.getHeight() - getHeight () && vy > 0) || (getX() <= 0 && vx < 0 && vy < 0) || 
        (getX() >= pb.getWidth() - getWidth() && vx > 0 && vy < 0))
    {
      vy = (int) (Math.random () * -2 - 1);
    }
    if (((getY() <= 0 || getY() >= pb.getHeight() - getHeight () && vy > 0) 
           && vx > 0) || (getX() <= 0 && vx < 0))
    {
      vx = (int) (Math.random () * 2 + 1);
    }
    if (((getY() <= 0 && vy < 0 || getY() >= pb.getHeight () - getHeight() && vy > 0) && vx < 0)
          || (getX() >= pb.getWidth() - getWidth() && vx > 0))
    {
      vx = (int) (Math.random () * -2 - 1);
    }
    
    
    setY (getY()+ vy);
    setX (getX() + vx);
  }
}