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

    // private JButton bt = new JButton();
    private JButton b = new JButton();
    private int X_Pos;
    private int Y_Pos;

    public Square(int xPos, int yPos) {
        X_Pos = xPos;
        Y_Pos = yPos;
        b.setIcon(i);
    }

    public int getStatus() {
        return Status;
    }

    // made it a integer instead so it takes a status that works out what image to
    // change it to.
    public void moveTo(int newStatus) {
        Status = newStatus;
        if (Status == 0) {
            b.setIcon(i);
        }
        // green
        else if (Status == 1) {
            b.setIcon(gf1);
        }
        // green 2
        else if (Status == 2) {
            b.setIcon(gf2);
        }
        // red
        else if (Status == 3) {
            b.setIcon(rf1);
        }
        // red 2
        else if (Status == 4) {
            b.setIcon(rf2);
        }
        // lilypad
        else if (Status == 5) {
            b.setIcon(l);
        }
        // water
        else if (Status == 6) {
            b.setIcon(i);
        }

    }
    // public void SetImageGf(){

    // b.setIcon(gf1);
    // }
    // public void SetImageRf(){
    // b.setIcon(rf1);
    // }

    // resets frogs if they are selected
    public void clear() {
        if (Status == 2) {
            moveTo(1);
        } else if (Status == 4) {
            moveTo(3);
        }
    }

    // swapping buttons, didnt really use it instead i did the method in Board
    public void moveTo(Square p) {
        return;
    }

    // returning coordinates
    public int getLocX() {
        return X_Pos;
    }

    public int getLocY() {
        return Y_Pos;
    }

    // getting the button
    public JButton getButton() {
        return b;
    }

}
