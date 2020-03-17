import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.*;
import java.lang.Math;

public class Board {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private ImageIcon l = new ImageIcon("images/LilyPad.png");
    private ImageIcon gf = new ImageIcon("images/GreenFrog.png");
    private ImageIcon rf = new ImageIcon("images/RedFrog.png");
    private ImageIcon rf2 = new ImageIcon("images/RedFrog2.png");
    private ImageIcon gf2 = new ImageIcon("images/GreenFrog2.png");
    private Square[][] Sarray = new Square[5][5];
    private JFrame f = new JFrame();
    private int FirstClick = 0;
    private Square SwapFrom; // need to be accessed later in the program
    private Square SwapTo;// need to be accessed later in the program
    private Square HopOver; //for moving over frogs
    private int FrogColour; // will be used to check if frog is green
    private int Redcount = 0;
    private int Greencount = 0;
    private int FrogRemoved = 0;

    public Board() {

        GridLayout g = new GridLayout(5, 5);
        f.setLayout(g);
        for (int j = 0; j < 5; j++) {
            for (int b = 0; b < 5; b++) {
                Square s = new Square(j, b);
                Sarray[j][b] = s;
                final int squareb = Sarray[j][b].getStatus(); // to be used in the swapping

                final int checkJ = j - 2;
                final int checkB = b - 2;
                final int checkB2 = b + 2;
                final int checkJ2 = j + 2;
                final int J = j;
                final int B = b;

                f.add(Sarray[j][b].getButton());
                // for printing the lillypads
                if ((j == 0 && b == 0) || (j == 0 && b == 2) || (j == 0 && b == 4) || (j == 2 && b == 0)
                        || (j == 2 && b == 4) || (j == 3 && b == 1) || (j == 3 && b == 3)) {
                    Sarray[j][b].moveTo(5);
                }
                // for printing the green frogs
                else if ((j == 1 && b == 1) || (j == 1 && b == 3) || (j == 2 && b == 2) || (j == 4 && b == 0)
                        || (j == 4 && b == 4)) {
                    Sarray[j][b].moveTo(1);
                    Greencount++;
                }
                // for printing the red frogs
                else if (j == 4 && b == 2) {
                    Sarray[j][b].moveTo(3);
                    Redcount++;
                }
                // valid move checker
                // check when button is clicked.
                Sarray[j][b].getButton().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // System.out.println(e.getSource());
                        for (int j = 0; j < 5; j++) {
                            for (int b = 0; b < 5; b++) {
                                
                                if (Sarray[j][b].getButton() == e.getSource()) {

                                    System.out.println("x:" + j + " y:" + b); // coordinates of the square

                                    // checking if green frog clicked
                                    if ( (Sarray[j][b].getStatus()  == 1) && (FirstClick == 0)) {
                                        System.out.println("Green Frog selected");
                                        Sarray[j][b].getButton();
                                        Sarray[j][b].moveTo(2); 
                                        // if (FirstClick == 0) {
                                            // Sarray[j][b].moveTo(2); 
                                            SwapFrom = Sarray[j][b];
                                            FirstClick = 1;
                                            FrogColour = 0;
                                        // }

                                    }
                                    // checking if red frog clicked
                                    else if ((Sarray[j][b].getStatus()  == 3) && (FirstClick == 0) ) {
                                        System.out.println("Red Frog selected");
            
                                        Sarray[j][b].getButton();
                                        SwapFrom = Sarray[j][b];
                                        Sarray[j][b].moveTo(4);  
                                        // if (FirstClick == 0) {
                                            // Sarray[j][b].moveTo(4);  
                                            SwapFrom = Sarray[j][b];
                                            FirstClick = 1;
                                            FrogColour = 1;
                                        // }

                                    }
                                    // checking if lilypads clicked
                                    else if (Sarray[j][b].getStatus() == 5) {
                                        if (FirstClick == 1) {
                                            FirstClick = 2;
                                            SwapTo = Sarray[j][b];
                                            // check if there is a frog in between
                                        }
                                    }
                                    // water clicked
                                    else if(Sarray[j][b].getStatus()  == 6) {
                                        System.out.println("Water selected");
                                        if (FirstClick == 1) {
                                            FirstClick=0;
                                            SwapFrom.clear(); 
                                        }

                                    }
                                }
                            }
                        }
                        System.out.println("First click" + FirstClick);
                        if ((FirstClick == 2)&&(SwapTo.getStatus() == 5))  {
     
                            System.out.println((SwapTo.getLocX()+SwapFrom.getLocX())/2);
                            System.out.println((SwapTo.getLocY()+SwapFrom.getLocY())/2);
                            HopOver = Sarray[(SwapTo.getLocX()+SwapFrom.getLocX())/2][(SwapTo.getLocY()+SwapFrom.getLocY())/2];
                            System.out.println(HopOver.getStatus());
                            
                            //check if frog moves horizontal/vertical/diagonal
                            if(SwapTo.getLocX()== SwapFrom.getLocX() || SwapTo.getLocY()== SwapFrom.getLocY()||Math.abs(SwapTo.getLocX()-SwapFrom.getLocX())==Math.abs(SwapTo.getLocY()-SwapFrom.getLocY())){
                        
                            //when a frog is removed 
                                if(HopOver.getStatus()==1){
                                    HopOver.moveTo(5);
                                    Greencount--;
                                    FrogRemoved =1;
                                }
                                else if(HopOver.getStatus()== 3){
                                    HopOver.moveTo(5);
                                    Redcount--;
                                    FrogRemoved =1;
                                }
                                
                                if((Greencount==0) &&(Redcount ==1) ){
                                    System.out.println("You have won!");
                                }
                                else if(Redcount ==0){
                                    System.out.println("You have lost!");
                                }

                                // performs the swapping squares
                                if(FrogColour == 0) { // green frog
                                    //
                                    if(FrogRemoved>0){
                                        SwapTo.moveTo(1);
                                        SwapFrom.moveTo(5);
                                    // check if there is something in the middle when it swaps and removes it from
                                    // there , maybe make a function for it to /2 of coordinates
                                    // fix issue where if user clicks on one frog it doesnt let them click on
                                    // another frog
                                    }
                                    else{
                                        SwapFrom.moveTo(1);
                                    }

                                } 
                                else if(FrogColour == 1) {// red frog

                                    if(FrogRemoved>0){
                                         SwapTo.moveTo(3);
                                        SwapFrom.moveTo(5);
                                     }
                                    else{
                                        SwapFrom.moveTo(3);
                                    }
                                }
                            }
                            else{
                                if(FrogColour == 0){
                                    SwapFrom.moveTo(1);

                                }
                                if(FrogColour == 1){
                                    SwapFrom.moveTo(3);
                                }
                                System.out.println("Move is not valid!");
                                
                            }
                            FirstClick = 0; // goes back to the start to the first click
                            FrogRemoved = 0;
                           
                        }//end first click if

                    }
                });

            }

        }
        f.setVisible(true);
        f.setTitle("Hoppers!");
        f.setSize(750, 750);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
