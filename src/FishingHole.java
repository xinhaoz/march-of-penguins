import java.io.*;
import javax.swing.JOptionPane;
import java.awt.*;

/** This class creates a game piece which asks the user a general biology question when a penguin lands on it.
  * Every correct answer is worth 10 points. 
  * 
  * @author Sabrina Cancelliere and Xin Hao Zhang
  * @version 4, June 9 2014
  */
public class FishingHole extends GamePiece
{
  /**
   * (int) This stores the number of points to be awarded fro a correct answer.
   */
  private int points;
  /**
   * (String) This stores the name of the file to be accessed.
   */
  private String fileName;
  /** 
   * (reference) References the PlayGame class.
   */
  private PlayGame pg;
  /**
   * (boolean) Keeps track of whether or not the question has been asked. 
   */
  private boolean isDone;
  
  /** This method is one of the constructors in FishingHole. It calls the super and sets the fileName, 
    * number of points and other variables.
    * 
    * @param x (int) Stores the x coordinate of the FishingHole.
    * @param y (int) Stores the y coordinate of the FishingHole.
    * @param fileName (String) Stores the name of the file to be read from. 
    * @param pg (reference) References the PlayGame class.
    */
  public FishingHole (int x, int y, String fileName, PlayGame pg)
  {
    super (x, y, 40, 40, "fishinghole.png");
    this.fileName = fileName;
    this.pg = pg;
    points = 10;
    
  }
  
  /** This method is the second constructor for FishingHole. It passes in a String which stores an
    * image's file name in addition to all the parameters in the previous constructor. 
    * 
    * @param x (int) Stores the x coordinate of the FishingHole.
    * @param y (int) Stores the y coordinate of the FishingHole.
    * @param fileName (String) Stores the name of the file to be read from. 
    * @param imgName (String) Stores a String which represents the file name of an image. 
    * @param pg (reference) References the PlayGame class.
    */
  public FishingHole (int x, int y, String fileName, String imgName, PlayGame pg)
  {
    super (x, y, 40, 40, imgName);
    this.fileName = fileName;
    this.pg=pg;
    points = 20;
  }
  
  /** 
   * This method poses a randomly selected question from a question file to the user based on the number that 
   * is passed in. 
   * 
   * The for loop is used to read in the randNumth question from the question file. 
   * 
   * The first if statement ensures that the file has the correct header and file extension. 
   * The next if statement checks whether the question has been answered correctly or not. 
   * 
   * The try catch catches any exceptions when reading from or writing to files. 
   * 
   * @param question (String) Stores the question and according answer which are read in from a question file.
   * @param input (reference) References the BufferedReader class.
   * @param output (reference) References the PrintWriter class.
   * @param header (String) Stores the header that should be located at the top of the file being read in. 
   * @return int Returns the number of points to be awarded.
   * @throws IOException Catches exceptions when reading from or writing to files.
   */
  public int askQuestion (int randNum)
  {
    String question = " ";
    BufferedReader input;
    String header = "Sabrina and Xin Hao";
    try
    {
      input = new BufferedReader (new InputStreamReader (getClass().getResourceAsStream ("/resources/" + fileName)));
      if (!(fileName.endsWith (".scxhz")) || !(input.readLine ().equals (header)))
      {
        JOptionPane.showMessageDialog(pg, "An error has occured.", "Error", JOptionPane.ERROR_MESSAGE);
        return 0;
      }
      for (int x = 0; x < randNum; x++)
      {
        question = input.readLine ();
      }
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(pg, "An error has occured.", "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    
    isDone = true;
    Object [] options = {"True", "False"};
    int n = JOptionPane.showOptionDialog (pg, question.substring (0, question.indexOf (".") + 1), "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options [0]);    
    if ((n == JOptionPane.YES_OPTION && question.substring (question.indexOf (".") + 2).equals ("T")) || (n == JOptionPane.NO_OPTION && question.substring (question.indexOf (".") + 2).equals ("F")))
    {
      JOptionPane.showMessageDialog(pg, "Congratulations, you are correct!");
      return points;
    }
    else 
    {
      JOptionPane.showMessageDialog(pg, "Unfortunately you selected the wrong answer, study more and try again!");
      return 0;
    }
  }
  
  /**
   * This is an accessor method which returns the boolean value of the isDone variable. 
   * 
   * @return boolean Returns whether the specific FishingHole has already been used. 
   */
  public boolean getIsDone ()
  {
    return isDone;
  }
  
  /**
   * Returns a Rectangle representation of the FishingHole using its location and dimensions.
   * Used for collision detection.
   * 
   * @return (Rectangle) A Rectangle that represents the FishingHole.
   */
  public Rectangle getBounds ()
  {
    return new Rectangle (getX(), getY(), getWidth(), getHeight());
  }
}
