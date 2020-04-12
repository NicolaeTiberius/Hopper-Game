import javax.swing.*;
import java.awt.*;

public class Square {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private ImageIcon rf1 = new ImageIcon("images/RedFrog.png");
    private ImageIcon rf2 = new ImageIcon("images/RedFrog2.png");
    private ImageIcon gf1 = new ImageIcon("images/GreenFrog.png");
    private ImageIcon gf2 = new ImageIcon("images/GreenFrog2.png");
    private ImageIcon l = new ImageIcon("images/LilyPad.png");
    private int Status = 6;
    private JButton b = new JButton();
    private int X_Pos;
    private int Y_Pos;

    // Constructor that takes the x and y coordinates from the squares in board
    // class. Sets default square to water.
    public Square(int xPos, int yPos) {
        X_Pos = xPos;
        Y_Pos = yPos;
        b.setIcon(i);
    }

    /*
     * This will return the current status of a square. e.g. if there's a green frog
     * ,red frog or water etc.
     */
    public int getStatus() {
        return Status;
    }

    /*
     * Made it an integer instead so it takes a status that works out what image to
     * change the square to, thats how it will move/swap images.
     */
    public void moveTo(int newStatus) {
        Status = newStatus;
        if (Status == 0) {
            b.setIcon(i);
        }
        // green frog square
        else if (Status == 1) {
            b.setIcon(gf1);
        }
        // green frog highlighted square
        else if (Status == 2) {
            b.setIcon(gf2);
        }
        // red frog square
        else if (Status == 3) {
            b.setIcon(rf1);
        }
        // red frog highlighted square
        else if (Status == 4) {
            b.setIcon(rf2);
        }
        // lilypad square
        else if (Status == 5) {
            b.setIcon(l);
        }
        // water square
        else if (Status == 6) {
            b.setIcon(i);
        }

    }// end newStatus

    /*
     * Resets frogs if they are selected twice (two frogs highlihted on both
     * clicks).(because it is an invalid move)
     */
    public void clear() {
        if (Status == 2) {
            moveTo(1);
        } else if (Status == 4) {
            moveTo(3);
        }
    }// end clear

    // returns x-coordinate position
    public int getLocX() {
        return X_Pos;
    }

    // returns y-coordinate positition
    public int getLocY() {
        return Y_Pos;
    }

    // getting the button/returns what is stored in the button/square.
    public JButton getButton() {
        return b;
    }

}// end Square class
