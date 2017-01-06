/** 
 * This class creates a game piece which asks the user a bonus question when a penguin lands on it.
 * Every correct answer is worth 20 points. 
 * 
 * @author Sabrina Cancelliere and Xin Hao Zhang
 * @version 4, June 9 2014
 */
public class BonusHole extends FishingHole
{
  
  /** This method is the constructor for BonusHole. It calls the superclass FishingHole's constructor
    * with an added image parameter.
    * 
    * @param x (int) Stores the x coordinate of the BonusHole.
    * @param y (int) Stores the y coordinate of the BonusHole.
    * @param fileName (String) Stores the name of the file to be read from. 
    * @param ph (reference) References the PlayGame class.
    */
  public BonusHole (int x, int y, String fileName, PlayGame pg)
  {
    super (x, y, fileName, "bonushole.png", pg);
  }
}