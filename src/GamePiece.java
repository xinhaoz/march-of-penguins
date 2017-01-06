import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/** 
 * An entity in the game represented by an image.
 * <br>
 * Game pieces are added to the game and interact by colliding with each other..
 * 
 * @author Xin Hao Zhang
 * @version 5, June 13 2014
 */

public class GamePiece 
{
  
  /**
   * (Reference) Represents the game piece's image sprite to draw on the game board.
   */
  BufferedImage img;
  
  /**
   * (int) The x coordinate of the game piece.
   */
  private int x;
  
  /**
   * (int) The y coordinate of the game piece.
   */
  private int y;
  
  /**
   * (int) The width of the GamePiece in pixels.
   */
  private int width;
  
  /**
   * The height of the GamePiece in pixels.
   */
  private int height;
  
  /**
   * Constructor initializes the coordinates of the game piece and loads the sprite.
   * <br>
   * try catch block attempts to read the image associated with the GamePiece.
   * 
   * @param x The x coordinate of the game piece.
   * @param y The y coordinate of the game piece.
   * @param fileName (String) the file name of the game piece's sprite image.
   * @param e The type of exception that occured.
   */
  public GamePiece (int x, int y, int width, int height, String fileName)
  {
    this.x=x;
    this.y=y;
    this.width = width;
    this.height = height;
    try
    {
      img = ImageIO.read(getClass().getResource ("/resources/"+fileName));
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, "Image could not be loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Sets the x coordinate of the piece.
   * 
   * @param x (int) The new x coordinate.
   */
  public void setX (int x)
  {
    this.x = x;
  }
  
  /**
   * Sets the y coordinate of the piece.
   * 
   * @param y (int) The new y coordinate.
   */
  public void setY (int y)
  {
    this.y=y;
  }
  
  /**
   * Returns the game piece's current x coordinate.
   * 
   * @return int The x coordinate of the piece.
   */
  public int getX ()
  {
    return x;
  }
  
  /**
   * Returns the game piece's current y coordinate.
   * 
   * @return int The y coordinate of the piece.
   */
  public int getY ()
  {
    return y;
  }
  
  public int getWidth ()
  {
    return width;
  }
  
  /**
   * Returns the height of the GamePiece in pixels.
   * 
   * @return (int) The height of the GamePiece in pixels.
   */
  public int getHeight()
  {
    return height;
  }
  
  /**
   * Returns the the game piece that the current piece collided with.
   * 
   * @return (GamePiece) The GamePiece it collided with.
   */
  public GamePiece collidedWith (ArrayList<GamePiece> players)
  {
    for (GamePiece gp: players)
    {
      if (gp.getBounds().intersects (((Rectangle)this.getBounds())))
      {
        return gp;
      }
    }
    return null;
  }
  
  /**
   * Method to be implemented in Seal and Skua subclasses.
   */
  public void move ()
  {
  }
  
  /**
   * Returns a Shape (Ellipse) representation of the GamePiece using it's xy coordinates and dimensions.
   * Used for collision detection.
   * 
   * @return (Ellips2D.Double) A Shape representation of the GamePiece.
   */
  public Shape getBounds ()
  {
    return (new Ellipse2D.Double (x, y, width, height));
  }
  
  /**
   * Update the location of the game piece.
   * 
   * @param g (Reference) The Graphics object to protect.
   */
  public void update (Graphics g)
  {
    g.drawImage (img, x, y, width, height, null);
  }
}