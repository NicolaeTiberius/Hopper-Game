import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.*;


//check status to see if frog is on the square

// lilypads coordinates to enable valid moves
public class Board {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private ImageIcon l = new ImageIcon("images/LilyPad.png");
    private ImageIcon gf = new ImageIcon("images/GreenFrog.png");
    private ImageIcon rf = new ImageIcon("images/RedFrog.png");
    private ImageIcon rf2 = new ImageIcon("images/RedFrog2.png");
    private ImageIcon gf2 = new ImageIcon("images/GreenFrog2.png");
    private Square[][]Sarray=new Square[5][5];
    private JFrame f = new JFrame();
    //button to be pressed for each image
    private JButton pressed = new JButton();
    private int FirstClick = 0;
    private Square SwapFrom; // need to be accessed later in the program 
    private Square SwapTo;// need to be accessed later in the program
    private int FrogColour;

 
    
    public Board() {
     
        GridLayout g = new GridLayout(5,5);
        f.setLayout(g);
        for(int j=0;j<5;j++){
            for(int b=0;b<5;b++){
                Square s = new Square(j,b);
                Sarray[j][b] = s; 
                f.add(Sarray[j][b].getButton());
                //for printing the lillypads
                if((j == 0 && b == 0)||(j == 0 && b ==2)||(j == 0 && b ==4)||(j == 2 && b ==0)|| (j == 2 && b ==4)||(j == 3 && b ==1)||(j == 3 && b ==3)){
                    Sarray[j][b].imageReset(5);
                }
                //for printing the green frogs
                else if((j == 1 && b == 1)||(j == 1 && b == 3)||(j == 2 && b == 2)||(j == 4 && b == 0)||(j == 4 && b == 4)){
                    Sarray[j][b].imageReset(1);
                }
                //for printing the red frogs
                else if(j == 4&& b == 2){
                    Sarray[j][b].imageReset(3);
                }
                //check when button is clicked.
                Sarray[j][b].getButton().addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent e){
                      //  System.out.println(e.getSource());
                        for(int j=0;j<5;j++){
                            for(int b=0;b<5;b++){
                                if(Sarray[j][b].getButton() == e.getSource()){
                                    System.out.println("x:"+ j + " y:" + b);
                                    //checking if green frog clicked
                                    if(Sarray[j][b].getStatus() ==1) {
                                        System.out.println("Green Frog selected");
                                        Sarray[j][b].imageReset(2);
                                        Sarray[j][b].getButton();
                                     
                                       
                                        if(FirstClick == 0){
                                            SwapFrom =  Sarray[j][b];
                                            FirstClick = 1;
                                       
                                            FrogColour = 0;
                                        }
                                    
                                      
                                    }
                                    //checking if red frog clicked
                                    else if(Sarray[j][b].getStatus() ==3){
                                        System.out.println("Red Frog selected");
                                        Sarray[j][b].imageReset(4);
                                        Sarray[j][b].getButton();
                                        SwapFrom =  Sarray[j][b];
                                        if(FirstClick == 0){
                                            SwapFrom =  Sarray[j][b];
                                            FirstClick = 1;
                                            FrogColour = 1;
                                        }

                                        
                                    }
                                    //checking if lilypads clicked
                                    else if(Sarray[j][b].getStatus() ==5){
                                        if(FirstClick ==1){
                                            FirstClick = 2;
                                            SwapTo = Sarray[j][b];
                                          
                                        }
                                    }
                                    //water clicked
                                    else{
                                        System.out.println("Water selected");
                                      
                                    
                                    }
                                }
                            }
                        }
                        System.out.println("First click" + FirstClick);
                        if(FirstClick ==2){
                       
                            System.out.println("swap from" + SwapFrom.getLocx());
                            System.out.println("swapto" + SwapTo.getLocx());
                            if (FrogColour== 0){ // green frog
                                SwapTo.imageReset(1);
                                SwapFrom.imageReset(5);
                            }
                            else{//red frog 
                                SwapTo.imageReset(3);
                                SwapFrom.imageReset(5);
                            }
                            FirstClick = 0;
                           
                        }
                     
                               
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



