import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;

public class Board {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private ImageIcon l = new ImageIcon("images/LilyPad.png");
    private ImageIcon gf = new ImageIcon("images/GreenFrog.png");
    private ImageIcon rf = new ImageIcon("images/RedFrog.png");
    private Square[][]Sarray=new Square[5][5];
    private JFrame f = new JFrame();

 
    
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
                    Sarray[j][b].imageReset(l);
                }
                //for printing the green frogs
                else if((j == 1 && b == 1)||(j == 1 && b == 3)||(j == 2 && b == 2)||(j == 4 && b == 0)||(j == 4 && b == 4)){
                    Sarray[j][b].imageReset(gf);
                }
                //for printing the red frogs
                else if(j == 4&& b == 2){
                    Sarray[j][b].imageReset(rf);
                }

                
            }
        
        }
        f.setVisible(true);
        f.setTitle("Hoppers!");
        f.setSize(750, 750);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
      
    }

}



