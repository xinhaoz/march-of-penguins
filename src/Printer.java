import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/** 
 * Helper class designed to make printing easier. Basic principle is that this class will act like an output stream allowing
 * for text and graphics be outputted on it. Text are displayed on a line by line format (location of the beginning of the
 * text is preset and follows a uniform pattern. Images can be displayed anywhere and options are available to resize a
 * given image based on the dimensions of the paper.
 * 
 * @author Daniel Chen
 * @version 2.0
 */

/* Update notes:
 15/5/2014 - Created, print and println based on System.out
 
 2/6/2014 - Renamed startPrinting () to printUsingDialog (). Removed print () so only println () can be used. Added new overloads
 to println () which allows printing to different alliment (left, right, center). Added image printing which can print an image
 directly or resize it based a percentage of the paper's dimensions.
 WARNING: This class will not check for overlaps, make sure you predict the location of everything if you dont want overlapping.
 */

public class Printer implements Printable 
{
  /**
   * ArrayList containg the text to be printed on the differen allignments.
   */
  private ArrayList <String> left, center, right;
  /**
   * ArrayList containg the images to be printed.
   */
  private ArrayList <BufferedImage> images;
  /**
   * ArrayList containg the locations of the images to be displayed.
   */
  private ArrayList <Point> imageLocations;
  /**
   * ArrayList containg the whether or not an image should be resized.
   */
  private ArrayList <Boolean> needsResizing;
  /**
   * ArrayList containg the percentage of the paper's dimensions the images takes up.
   */
  private ArrayList <int []> imageSizePercent;
  /**
   * Used to keep track of which y-coordinate to display text on.
   */
  private int y;
  /**
   * Default margins from the edge of the paper, 72 is the equivalent of 1 inch.
   */
  private final int MARGINS = 72;
  /**
   * Font for the text to be printed.
   */
  private Font font;
  
  /**
   * Queues an empty line to be printed. Adds empty Strings to the ArrayLists which will occur in an empty line being printed.
   */
  public void println ()
  {
    left.add ("");
    center.add ("");
    right.add ("");
  }
  
  /**
   * Queues a String alligned on the left side to be printed. Adds the String to the left ArrayList and empty Strings to the others.
   * @param text The text to be displayed on the left.
   */
  public void println (String text)
  {
    println (text, "", "");
  }
  
  /**
   * Queues a line with different text alligned on different places to be printed. Each String will be drawn starting at different locations.
   * The one on the left will be at the margins, the one in the center will be displayed with its center alligned with the
   * center of the page and the right most one will end on the right margin.
   * @param leftText The text that will be displayed on the left side.
   * @param centerText The text that will be centered.
   * @param rightText The text that will be displayed on the right side.
   */
  public void println (String leftText, String centerText, String rightText)
  {
    left.add (leftText);
    center.add (centerText);
    right.add (rightText);
  }
  
  /**
   * Queues an image to be printed. <br> The image will be drawn at the location specified without any proccessing for if it goes off the page.
   * Images will automatically be moved a bit to the right and down based on the margin of the imageable areas of the paper - (0, 0) will
   * print at the closest location which isn't necessarily the exact top left of the page.
   * @param image The image to be printed.
   * @param location The location of the image. Syntax is: new Point (x, y).
   */
  public void printImage (BufferedImage image, Point location)
  {
    images.add (image);
    imageLocations.add (location);
    imageSizePercent.add (null);
    needsResizing.add (new Boolean (false));
  }
  
  /**
   * Queues a square image to be printed with the length based on a percent of one of the dimensions of the paper. The length of the image
   * will be calculated based on the percent multiplied by the width or height of the paper (specified by the boolean). <br> The image will be 
   * drawn at the location specified without any proccessing for if it goes off the page. Images will automatically be moved a bit to the
   * right and down based on the margin of the imageable areas of the paper - (0, 0) will print at the closest location which isn't 
   * necessarily the exact top left of the page.
   * @param image The image to be printed.
   * @param location The location of the image. Syntax is: new Point (x, y).
   * @param percent The percentage of the dimension to be taken up (0 - 100).
   * @param useWidth Boolean for wether to base the percent on width or height.
   */  
  public void printImage (BufferedImage image, Point location, int percent, boolean useWidth)
  {
    int [] temp = {useWidth?percent:-1, useWidth?-1:percent};
    images.add (image);
    imageLocations.add (location);
    imageSizePercent.add (temp);
    needsResizing.add (new Boolean (true));
  }
  
  /**
   * Queues an image to be printed with the length based on the percent of the dimensions of the paper. The width of the image will be
   * calculated based on the width of the paper multiplied by the percentWidth and the height by the percentHeight. <br> The image will be 
   * drawn at the location specified without any proccessing for if it goes off the page. Images will automatically be moved a bit to the
   * right and down based on the margin of the imageable areas of the paper - (0, 0) will print at the closest location which isn't 
   * necessarily the exact top left of the page.
   * @param image The image to be printed.
   * @param location The location of the image. Syntax is: new Point (x, y).
   * @param percentWidth The percentage of the width of the paper to base the width of the image on.
   * @param percentHeight The percentage of the height of the paper to base the height of the image on.
   */  
  public void printImage (BufferedImage image, Point location, int percentWidth, int percentHeight)
  {
    int [] temp = {percentWidth, percentHeight};
    images.add (image);
    imageLocations.add (location);
    imageSizePercent.add (temp);
    needsResizing.add (new Boolean (true));
  }
  
  /**
   * Signals the printer to start printing. Users will be able to modify print conditions using the dialog box that popups
   * up.
   * @return boolean representing whether or not the print was sucessful.
   */
  public boolean printUsingDialog ()
  {
    PrinterJob p = PrinterJob.getPrinterJob ();
    if (p.printDialog ())
    {
      p.setPrintable (this);
      try
      {
        p.print ();    
      }
      catch (PrinterException e)
      {
        return false;
      }
      return true;
    }
    else
      return false;
  }
  
  /**
   * Method invoked by PrinterJob to render a page. The queued text and images to be displayed are rendered on to the 
   * paper and the printed. Do NOT try to call directly, use printUsingDialog () instead.
   * @param g The context into which the page is drawn.
   * @param pf The size and orientation of the page being drawn.
   * @param page The zero based index of the page to be drawn.
   * @return PAGE_EXISTS if the page is rendered successfully or NO_SUCH_PAGE if pageIndex specifies a non-existent page.
   * @throws PrinterException thrown when the print job is terminated.
   */
  public int print(Graphics g, PageFormat pf, int page) throws PrinterException 
  {
    if (page > 0)
      return NO_SUCH_PAGE;
    
    g.setFont (font);
    Graphics2D g2d = (Graphics2D)g;
    FontMetrics fm = g.getFontMetrics ();
    y = MARGINS + fm.getHeight ();
    double pageWidth = 2 * pf.getImageableX () + pf.getImageableWidth ();
    double pageHeight = 2 * pf.getImageableY () + pf.getImageableHeight ();
    
    for (int i = 0; i < images.size (); i ++)
    {
      if (!((boolean) needsResizing.get (i)))
        g2d.drawImage (images.get (i), (int) (pf.getImageableX() + imageLocations.get (i).getX ()), (int) (pf.getImageableY() + imageLocations.get (i).getY ()), null);
      else
      {
        int [] temp = imageSizePercent.get (i);
        int width = temp [0] == -1? (int) (temp [1] / 100.0 * pageHeight): (int) (temp [0] / 100.0 * pageWidth);
        int height = temp [1] == -1? (int) (temp [0] / 100.0 * pageWidth): (int) (temp [1] / 100.0 * pageHeight);
        g2d.drawImage (images.get (i), (int) (pf.getImageableX() + imageLocations.get (i).getX ()), (int) (pf.getImageableY() + imageLocations.get (i).getY ()), width, height, null);
      }
    }
    
    for (int i = 0; i < left.size (); i ++)
    {
      g2d.drawString(left.get (i), MARGINS , y);
      g2d.drawString(center.get (i), (int) (pageWidth/2 - fm.stringWidth (center.get (i))/2.0), y); //Roughly center, will lose precision due to double -> int
      g2d.drawString(right.get (i), (int) (pageWidth - fm.stringWidth (right.get (i))) - MARGINS, y);
      y+= g.getFontMetrics ().getHeight ();
      if (y >= pageHeight - MARGINS)
        break;
    }
    return PAGE_EXISTS;
  }
  
  /**
   * Creates a Printer object with Serif size 12 font.
   */
  public Printer ()
  {
    left = new ArrayList <String> ();
    center = new ArrayList <String> ();
    right = new ArrayList <String> ();
    images = new ArrayList <BufferedImage> ();
    imageLocations = new ArrayList <Point> ();
    needsResizing = new ArrayList <Boolean> ();
    imageSizePercent = new ArrayList <int []> ();
    font = new Font ("Serif", Font.PLAIN, 12);
  }
  
  /**
   * Creates a Printer object with a custom font.
   * @param f The font to be used when displaying text.
   */
  public Printer (Font f)
  {    
    left = new ArrayList <String> ();
    center = new ArrayList <String> ();
    right = new ArrayList <String> ();
    images = new ArrayList <BufferedImage> ();
    imageLocations = new ArrayList <Point> ();
    needsResizing = new ArrayList <Boolean> ();
    imageSizePercent = new ArrayList <int []> ();
    font = f;
  }
}