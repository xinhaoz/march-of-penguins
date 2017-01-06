import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

/**
 * The Frame for the program that contains the screens, tool bars and menus.
 * 
 * @author Xin Hao Zhang and Sabrina Cancelliere
 * @version 4, June 6 2014
 */

public class PenguinFrame extends JFrame implements ActionListener
{
  /**
   * (reference) References the ImageIcon class to create an icon to be used in the about dialog.
   */
    private ImageIcon icon;
    
  /** This constructor sets up the JFrame, creating a menu bar with menus and menu items. 
    * The try catch is used to try to display the splash screen for 5 seconds before closing it and displaying the game
    * and for loading the logo for the About icon.
    * Keyboard shortucts are also set here.
    *
    * @param menuBar Reference variable for the program's menu bar.
    * @param fileMenu Reference variable for the File menu.
    * @param helpMenu Reference variable for the Help menu.
    * @param quitItem Reference variable for the Quit menu item.
    * @param helpItem Reference variable for the Help menu item.
    * @param aboutItem Reference variable for the about menu item.
    * @param splash Reference variable for the splash screen window.
    */
  public PenguinFrame ()
  {
    super ("March of the Penguins");
    JMenuBar menuBar = new JMenuBar ();
    JMenu fileMenu = new JMenu("File"), helpMenu = new JMenu ("Help");
    JMenuItem quitItem = new JMenuItem ("Quit"), helpItem = new JMenuItem ("Help"), aboutItem = new JMenuItem ("About");
    
    SplashScreen splash = new SplashScreen ();
    try
    {
      icon = new ImageIcon (ImageIO.read (getClass().getResource ("/resources/" + "LOGO.png")));
      Thread.sleep (5000);
    }
    catch (InterruptedException e)
    {
    }
    catch (IOException e)
    {
    }
    splash.dispose ();
    
    fileMenu.add (quitItem);
    helpMenu.add (helpItem);
    helpMenu.add (aboutItem);
    menuBar.add (fileMenu);
    menuBar.add (helpMenu);
    setJMenuBar (menuBar);
    add (new MainMenu());
    
    quitItem.addActionListener(this);
    helpItem.addActionListener (this);
    aboutItem.addActionListener (this);
    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
    helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Event.CTRL_MASK));
    aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
    fileMenu.setMnemonic ('f');
    helpMenu.setMnemonic ('h');
    setResizable (false);
    setSize (1280, 750);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
  }
  
  /**
   * This method gets the action performed by the user and responds accordingly based on the action's
   * returned String.
   * 
   * @param ae (reference) The type of action that occured.
   * @param progpath (String) Stores the path to the chm file.
   * @throws IOException Catches any exceptions writing or reading to a file. 
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ( ).equals ("Help"))
    {
      JOptionPane.showMessageDialog (this, "Please refer to the User Manuel for help.");
      String progpath = new String ("hh.exe " + System.getProperty("user.dir") + "/resources/WumboHelp.chm");
      try
      {
        Runtime.getRuntime().exec (progpath);
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog (this, "Sorry, the help file could not be found", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    else if (ae.getActionCommand().equals ("About"))
    {
      JOptionPane.showMessageDialog(this,
                                    "March of the Penguins is an educational game created by Sabrina Cancelliere and Xin Hao Zhang\n"
                                      + "which teaches about both general and Antarctic specific biology in a fun and engaging way.\n"
                                      + "More detailed information can be found in the user manual, or help files.", "About", JOptionPane.INFORMATION_MESSAGE, icon);
    }
    else
    {
      System.exit(0);
    }
  }
}