
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

/**
 * Screen that displays information the user should study. The user can navigate between general biology information or antarctic specific
 * information using tabs.
 * 
 * @author Xin Hao Zhang
 * @version 1, June 11, 2014
 */

public class Learn extends Screen
{
  
  /**
   * Constructor sets the layout and calls init method.
   */
  public Learn ()
  {
    setLayout (new BorderLayout());
    init ();
  }
  
  /**
   * Adds the tabs to the screen.
   * 
   * @param tabbedPane (reference) The JTabbedPane to add.
   */
  private void init ()
  {
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    tabbedPane.addTab("General Biology", createTab("BiologyFactFile.scxhz"));
    tabbedPane.addTab("Antarctic Biology", createTab("BonusFactFile.scxhz"));
    add (tabbedPane);
    add (createButton ("Back", 15), BorderLayout.SOUTH);
  }
  
  
  /**
   * Returns a panel with a JScrollPane containing information read from a file.
   * <br>
   * The try attempts to read lines from a file, while the catch catches any errors.
   * The for loop goes through the entire file until there are no more lines to read.
   * 
   * @param fileName (String) The file to read the information from.
   * @param panel (reference) The panel to return
   * @param input (reference) The input stream.
   * @param scroll (reference) The scroll pane to add the text area to.
   * @param textArea (reference) The text area to add the information to.
   * @param line (String) The line read from the file.
   * @param throws IOException If the file could not be read or was interrupted.
   */
  public JPanel createTab (String fileName)
  {
    JPanel panel = new JPanel (new BorderLayout());
    BufferedReader input;
    JScrollPane scroll;
    JTextArea textArea = new JTextArea();
    String line;
    
    scroll = new JScrollPane (textArea);
    scroll.getViewport().setOpaque(false);
    scroll.setOpaque (false);
    panel.setOpaque (false);
    
    textArea.setEditable (false);
    textArea.setFont(new Font("century gothic", Font.PLAIN, 18));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord (true);
    textArea.setOpaque (false);
    
    try
    {
      input =  new BufferedReader (new InputStreamReader (getClass().getResourceAsStream("/resources/" + fileName)));
      for (line = input.readLine (); line != null; line = input.readLine())
      {
        textArea.append (line + "\n");
      }
    }
    catch (IOException e)
    {
    }
    
    panel.add (scroll);
    return panel;
  }
  
  /**
   * Changes the screen to the MainMenu once Back button is pressed.
   * 
   * @param ae The ActionEvent that occured.
   */
  public void actionPerformed (ActionEvent ae)
  {
    changeScreen (new MainMenu (), this);
  }
}