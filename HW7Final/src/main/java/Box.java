/*
 * class Box extends JPanel and has 2 mv's. measureX mv is the horizontal x coordinate position of 
 * this instance and yCoord mv is the y coordniate position of this instance. When multiple instances
 * are created they are used to make sure each instance has it's own uninterrupted paths. 
 * constructor Box(int yCoord) -- creates the dimensions and green color of the box. It allows 
 * it's y coordinate to be changed so that it won't stack up with other instances. 
 * public void RightLeft() -- defines a thread and starts that thread. It overrides the run() method 
 * of a runnable thread which gives this instance the ability to move right and left across 
 * the JFrame or JPanel it is added to, independently and asynchronously from all other instances. 
 */

/**
 *
 * @author Trevor Lastelick
 */

import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {

private int measureX = 0;   // keeps track of boxes horizontal position 
private int yCoord = 0;    // keeps track of y position of boxes
    

/**
 * Creates dimensions for this JPanel (box) and makes it green. 
 * It will be added to another JPanel or JFrame once created to be visible. 
 * @param yCoord is each boxes yCoordinate on a plain 
 */
    public Box(int yCoord)
    {
    this.yCoord = yCoord;   // set this box's y value to a unique value 
    setBounds(0,yCoord,50,50);
    setLayout(null);
    setBackground(Color.green);
    yCoord += 50;  // every box will line up on a different vertical plain 
    } // end constructor
    
    /**
     * This class has two boolean flags that are used to switch between two differnt functions.
     * When 'flag' is true, it tells the box to move to the right until it hits the edge of the JFrame.
     * When right edge of JFrame is hit, 'switchFlag' is triggered. This tells the box to move to the
     * left until left edge of JFrame is hit. Then 'flag' is triggered and the box moves the other way.
     * This functionality is triggered infinitely. 
     */
    public void RightLeft()
    {
        // Defines how a thread is supposed to run when created 
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
           
            boolean flag = true;    // triggers infinite loop
            boolean switchFlag = true;  // triggers box to move right or move left
            
             // Never stop moving once activated 
             while (flag)
             {
                
                // MoveRight function
                if (switchFlag)
                {
                    
                try {
                while (switchFlag)
                {
                // increment the box across the screen with a small delay, so it can be seen visually
                for (int i = measureX; measureX<535; measureX++)
                { 
                setBounds(measureX, yCoord, 50, 50);
                Thread.currentThread().sleep(5);
               
               // if reaches the end of the JFrame, trigger flag to MoveLeft function
                if (measureX == 534)
                   switchFlag = false;

                } // end for
                } // end inner while switchFlag == true
                } catch (InterruptedException e) {
                 e.printStackTrace();
                } // end catch
                
               // MoveLeft function
                if (!switchFlag)
                try {
                while (!switchFlag)
                {
                // increment the box across the screen with a small delay, so it can be seen visually
                for(int i = measureX; measureX>0; measureX--)
                { 
                setBounds(measureX, yCoord, 50, 50);
                Thread.currentThread().sleep(5);
                if (measureX == 1)
                   switchFlag = true;

                } // end for
                } // end inner while case 1
                } catch (InterruptedException e) {
                e.printStackTrace();
                } // end catch
                }
      
                } // end infinite loop while 
             //   
            } // end run()
                
            };  // end runnable thread definition
        
        // start the thread that was just created above
        new Thread(r2).start();
    }
    
    
}   // end Box class
