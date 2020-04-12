/*
 * This is Nicolae's SCC110 Java Coursework
 * A frog hopping game that works similiar to checkers
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.*;
import java.lang.Math; 
 
/** 
 * 
 * @author Nicolae Chelmenciuc
*/

public class Board {

  
    private ImageIcon i = new ImageIcon("images/Water.png");            //  Water
    private ImageIcon l = new ImageIcon("images/LilyPad.png");          // Lilypad 
    private ImageIcon gf = new ImageIcon("images/GreenFrog.png");       // Green Frog
    private ImageIcon rf = new ImageIcon("images/RedFrog.png");         // Red Frog
    private ImageIcon rf2 = new ImageIcon("images/RedFrog2.png");       // Red frog highlighted
    private ImageIcon gf2 = new ImageIcon("images/GreenFrog2.png");     // Green frog highlighted

    private Square[][] Sarray = new Square[5][5];                       // Made an array that uses an instance of Square class, so the array can be manipulated with any method from Square class. 
    private JFrame f = new JFrame();    
    private int FirstClick = 0;                                         // will be used to detect the first click of the user  

    private Square SwapFrom;                                            // need to be accessed later in the program to detect which square is swapped 
    private Square SwapTo;                                              // need to be accessed later in the program to detect what to swap a square to

    private Square HopOver;                                             // for moving over frogs, used another Square to perform the square class methods, seperate from the array Square.
    private int FrogColour;                                             // will be used to check if frog is green or red 
    private int Redcount = 0;                                           // used to count how many red frogs are removed from the game 
    private int Greencount = 0;                                         // used to count how many green frogs are removed from the game
    private int FrogRemoved = 0;                                        //counts how many frogs are removed overall 


    private Level2 nextLevel = new Level2();
 
    /**
     * This will create a Board 5*5 that will have 
     *  diferent squares with green frogs and red frogs etc for the player to play
     * the game 
     */
    public Board() {

        GridLayout g = new GridLayout(5, 5);                            // Makes a Grid 5x5 to store 25 squares. 
        f.setLayout(g);                                                 // Sets the frame to have the grid format 

        /* - Making the board -
        * These nested loops are used to go over 5x5 times. 
        * So each 5 row has 5 columns and it will all be filled up with however the maps is designed with squares of lilypads,water etc.
        */
        for (int j = 0; j < 5; j++) {                                
            for (int b = 0; b < 5; b++) {
                Square s = new Square(j, b);                            // Made a new square instance that will take x and y coordinates using Square constructor.
                Sarray[j][b] = s;

                f.add(Sarray[j][b].getButton());                        //Adds a button to each square created by the nested loop (inside the grid).So they can be manipulated
                // for printing the lillypads                           
                if ((j == 0 && b == 0) || (j == 0 && b == 2) || (j == 0 && b == 4) || (j == 2 && b == 0)
                        || (j == 2 && b == 4) || (j == 3 && b == 1) || (j == 3 && b == 3)) {
                    Sarray[j][b].moveTo(5);
                }
                // for printing the green frogs
                else if ((j == 1 && b == 1) || (j == 1 && b == 3) || (j == 2 && b == 2) || (j == 4 && b == 0)
                        || (j == 4 && b == 4)) {
                    Sarray[j][b].moveTo(1);
                    Greencount++;                                       //Takes a count of how many green frogs are on the map, to be used for win/lose condition.
                }
                // for printing the red frogs
                else if (j == 4 && b == 2) {
                    Sarray[j][b].moveTo(3);
                    Redcount++;                                         //The same as green frog count, to be used for win/lose condition. 
                }
                
                // Checks when a button in the grid is clicked
                Sarray[j][b].getButton().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < 5; j++) {
                            for (int b = 0; b < 5; b++) {

                                if (Sarray[j][b].getButton() == e.getSource()) {
                                    
                                    System.out.println("x:" + j + " y:" + b); // Coordinates of the square

                                    // Checking if green frog clicked and ensures the user has not clicked on it yet
                                    if ((Sarray[j][b].getStatus() == 1) && (FirstClick == 0)) {
                                        System.out.println("Green Frog selected");
                                        Sarray[j][b].getButton();              // Collects info of the button pressed.
                                        Sarray[j][b].moveTo(2);                // Highlights the green frog using moveTo method from Square class.
                                        SwapFrom = Sarray[j][b];               // Stores this current square to be swapped 
                                        FirstClick = 1;                        // Adds a click because the user clicked on the frog 
                                        FrogColour = 0;                        //Green frog is 0, used later for checking and incrementing frogs.
                                    }
                                    // checking if red frog clicked and ensures the user has not clicked on it yet.
                                    else if ((Sarray[j][b].getStatus() == 3) && (FirstClick == 0)) {
                                        System.out.println("Red Frog selected");

                                        Sarray[j][b].getButton();       
                                        SwapFrom = Sarray[j][b];
                                        Sarray[j][b].moveTo(4);                 // Highlights the red frog
                                        SwapFrom = Sarray[j][b];
                                        FirstClick = 1;                         // Adds a click because the user has clicked, waits for second click.
                                        FrogColour = 1;                         // Red Frog is 1, used later for checking frogs conditions. 
                                    }

                                    // Checking if lilypads clicked, does not check if clicked before, has different rules. 
                                    else if (Sarray[j][b].getStatus() == 5) { 
                                        if (FirstClick == 1) {                  // If the user already clicked before then it uses this lilypad as a second click, otherwise continue.
                                            FirstClick = 2;
                                            SwapTo = Sarray[j][b];

                                        }
                                    }
                                    // Water clicked
                                    else if (Sarray[j][b].getStatus() == 6) {
                                        System.out.println("Water selected");
                                        if (FirstClick == 1) { 
                                            FirstClick = 0;   // It restarts the click because clicking on water does not do anything, invalid move.
                                            SwapFrom.clear(); // Sets a highlighted frog back to its default image.
                                        }
                                    } 
                                } // end if button is clicked 
                            }
                        } // end for loop 

                        // Prints outs the click number (e.g. 1 or 2 ) mainly used for testing purposes. 
                        System.out.println("Click " + FirstClick);

                        // If the user has clicked twice and chose a lilypad to swap to lilypad. 
                        if ((FirstClick == 2) && (SwapTo.getStatus() == 5)) {

                            System.out.println((SwapTo.getLocX() + SwapFrom.getLocX()) / 2); // gets the midpoint of x, for testing purposes.
                            System.out.println((SwapTo.getLocY() + SwapFrom.getLocY()) / 2); // gets the midpoint of y, for testing purposes.

                            HopOver = Sarray[(SwapTo.getLocX() + SwapFrom.getLocX())        // HopOver will calculate what is the midpoint between both clicks using their x,y coordinates. 
                                    / 2][(SwapTo.getLocY() + SwapFrom.getLocY()) / 2];
                            System.out.println(HopOver.getStatus());                        //Prints out what is in between the two clicks (e.g. a frog, water square etc).

                            /*  - Valid move Checker -
                            Checks if frog moves horizontal/vertical/diagonal for valid move. 
                            Used a formula that calculates when two points are vertical or diagonal or horizontal between any x,y coordinates 
                            on the map anywhere. 
                            */
                            if (SwapTo.getLocX() == SwapFrom.getLocX() || SwapTo.getLocY() == SwapFrom.getLocY()
                                    || Math.abs(SwapTo.getLocX() - SwapFrom.getLocX()) == Math
                                            .abs(SwapTo.getLocY() - SwapFrom.getLocY())) {

                                // Removes a  green frog 
                                if (HopOver.getStatus() == 1) {
                                    HopOver.moveTo(5);
                                    Greencount--;    // removes a green frog from count.
                                    FrogRemoved = 1; // ensures a frog is removed.
                                } 
                                //Removes a red frog 
                                else if (HopOver.getStatus() == 3) {
                                    HopOver.moveTo(5);
                                    Redcount--;
                                    FrogRemoved = 1;
                                }
                                //This calculates the win and lose condition using how many green frogs and red frogs are on the board, prints a simple message in teminal. 
                                if ((Greencount == 0) && (Redcount == 1)) {
                                    System.out.println("You have won!");
                                    //moves to the next level(1) when this level has been beaten
                                    nextLevel.drawLevel(); // call method to draw new frame from other class 
                                    
                                } else if (Redcount == 0) {
                                    System.out.println("You have lost!");
                                }

                                // performs the swapping squares
                                if (FrogColour == 0) { // green frog
                                    //
                                    if (FrogRemoved > 0) {
                                        SwapTo.moveTo(1);
                                        SwapFrom.moveTo(5);

                                    } else {
                                        SwapFrom.moveTo(1);
                                    }

                                } else if (FrogColour == 1) {// red frog

                                    if (FrogRemoved > 0) {
                                        SwapTo.moveTo(3);
                                        SwapFrom.moveTo(5);
                                    } else {
                                        SwapFrom.moveTo(3);
                                    }
                                }
                            } //end of valid move checker 
                             else {
                                if (FrogColour == 0) {
                                    SwapFrom.moveTo(1);

                                }
                                if (FrogColour == 1) {
                                    SwapFrom.moveTo(3);
                                }
                                System.out.println("Move is not valid!");

                            }
                            FirstClick = 0;      // goes back to the start after both clicks made, so user can choose another square etc.
                            FrogRemoved = 0;    // restarts the frog removed after both clicks to test conditions again.

                        } // end first click if

                    } // end action performed 
                }); // end of action listener 
            } // end the array of square class

        }
        f.setVisible(true); // displays the whole frame with everything else added, put at end so its updated frame. 
        f.setTitle("Hoppers!"); // sets title of the frame. 
        f.setSize(750, 750);    // sets size of frame. 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ensures the frame is removed if closed.

    }
} // end Board class 
