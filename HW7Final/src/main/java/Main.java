/*
 * Main function tests class Box, but first needs to create some mv's, and a 
 * keyListener to give it functionality. 
 * static JPanel mainPanel -- mv holds all the Box instances that will be created.
 * static Box box1 -- mv that is already visible when program is first run. starts moving when s or S key pressed.
 * static Box box2 -- mv that allows more boxes to be created with the press of a spacebar. 
 * These boxes begin moving on their own and perform asynchronously of each other.
 * static int sCount -- mv that does not allow user to create more threads that affect the initially visible green square.
 * static int spcaeCount -- mv limits the amount of instances of green Squares that can be created in the entire program.
 * static int yPlain -- mv that is updates to assure each Box that is initialized is on its own unique path.
 * myKeyListener overrides keyPressed - function that is triggered when either the S, s key is pressed. Or spacebar is pressed.
 * If S or s is pressed, then the initially seen box will begin to move. If space bar is pressed
 * then for each press a new box is created (max of 5) on its own y plain (path). This box begins moving
 * immediately on its own and moves asynchronously of all other boxes via it's own thread. 
*/

/**
 *
 * @author Trevor Lastelick
 */
import javax.swing.*;
import java.awt.event.*;

public class Main {
    
    static JPanel mainPanel;    // holds all the Box instances that are created. 
    static Box box1; // Already existing green box. Begin moving with S/s key. 
    static Box box2; // Class that creates boxes 2-6, via a spacebar press, and
                     // these boxes begin moving on their own. 
    
    static int sCount; // keeps track of key S/s keys pushed
    static int spaceCount; // keeps track of space keys pushed
    static int yPlain;  // sets each new box on a different y plain
    
    public static void main(String[] args) {
        
        // Create a JFrame to react to keyListener, and hold mainPanel which
        // holds all the other panels that are to be created. 
        JFrame container = new JFrame();
        container.setSize(600,600);
        container.setLayout(null);
        
        // Panel that holds all the other boxes that are to be created. 
        mainPanel = new JPanel();
        mainPanel.setSize(600,600);
        mainPanel.setLayout(null);
        
        // Add keyListener to JFrame
        // Recognizes if S or s key is pressed.
        // Also, recognizes if spacebar key is pressed.
        MyKeyListener lol = new MyKeyListener();
        container.addKeyListener(lol);
        
        
        // Create a green square box and add it to mainPanel to be dispalyed
        // upon start up of the program
        box1 = new Box(yPlain);
        yPlain += 50;
        mainPanel.add(box1);
        
        
        // Allow user to view Frame and panels 
        container.add(mainPanel);
        container.setVisible(true);
        
    }
   
   /**
    * If S or s key pressed moves the initial showing green box right then left. 
    * If spacebar key pressed then create a green box on its own unique y plain
    * that begins moving upon the 1 spacebar key press.
    */
   static private class MyKeyListener extends KeyAdapter {
        
        // Listens for certain keys to be pressed
        public void keyPressed (KeyEvent ke)
        {
            // 
            // if S or s key pressed then
            if (ke.getKeyCode() == 83 || ke.getKeyCode() == 115)
            {
              sCount++; // trigger the if statement 
              // limits user from creating too many threads via S/s keys
               if (sCount == 1)
                box1.RightLeft();
            }
            
            // if user presses spacebar, create more boxes that are already moving
            if (ke.getKeyCode() == 32)
            {
             // limits user from creating more than 5 already moving boxes
             // creates a green square box that already moves on its own thread
             // upon creation
             spaceCount++;  // trigger if statement 
                if (spaceCount <= 5)
                {
                    box2 = new Box(yPlain);
                    box2.RightLeft();   // move the new box 
                    mainPanel.add(box2);    // make the box visible
                    yPlain += 50;   // box created on a new unique y plain
                }
            }
        }
      
        }   // end keyListener
    
    
    
} // end class Main
