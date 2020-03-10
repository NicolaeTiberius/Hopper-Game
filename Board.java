import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;

public class Board {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private Square[][]Sarray=new Square[5][5];
    private JFrame f = new JFrame();
    //Square s = new Square(a, b);
   // JPanel panel = new JPanel();
 
    
    public Board() {
        //f.setContentPane(panel);
       // panel.add(s);
      //  f.add(panel);
        GridLayout g = new GridLayout(5,5);
        f.setLayout(g);
        for(int j=0;j<5;j++){
            for(int b=0;b<5;b++){
                Square s = new Square(j,b);
                Sarray[j][b] = s;
                f.add(Sarray[j][b].getButton());
            }
        }
        f.setVisible(true);
        f.setTitle("Hoppers!");
        f.setSize(700, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // for(i=0;i<25;i++){
        //     a+=10;
        //     b+=10;
        // }
    }

}



