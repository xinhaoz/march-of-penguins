import java.awt.*;

/**
 * A GamePiece that acts as an obstacle. The Penguin can not move onto this piece.
 *
 * @author Xin Hao Zhang
 * @version 1, June 8 2014
 */
public class CrackedIce extends GamePiece
{
  
  /**
   * Constructor sends the xy coordinates to the super class as well as the file name.
   * 
   * @param x (int) The x coordinate.
   * @param y (int) The y coordinate.
   * @param pb (referece) The board that the game piece belongs to.
   */
  public CrackedIce (int x, int y, PenguinBoard pb)
  {
    super (x, y, 80, 80, "crackedice.png");
    pb.players.add (0, this);
  }
  
  /**
   * Gets a Rectangle representation of the GamePiece using the piece's location and dimensions.
   * Used for collision detection.
   * 
   * @return (Rectangle) A Rectangle that represents the CrackedIce game piece.
   */
  public Rectangle getBounds ()
  {
    return new Rectangle (getX(), getY(), 80, 80);
  }
}