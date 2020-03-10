import javax.swing.*;
import java.awt.*;

public class Square {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private ImageIcon rf1 = new ImageIcon("images/RedFrog.png");
    private ImageIcon rf2 = new ImageIcon("images/RedFrog2.png");
    private ImageIcon gf1 = new ImageIcon("images/GreenFrog.png");
    private ImageIcon gf2 = new ImageIcon("images/GreenFrog2.png");
    private ImageIcon l = new ImageIcon("images/LilyPad.png");

    // private JButton bt = new JButton();
    private JButton b = new JButton();
    private int X_Pos = b.getLocation().x;
    private int Y_Pos = b.getLocation().y;

    public Square(int xPos, int yPos) {
        xPos = X_Pos;
        yPos = Y_Pos;
        b.setIcon(i);
        // bt.setIcon(l);

    }

    public void imageReset(ImageIcon a) {
        b.setIcon(a);
    }

    public void moveTo(Square t) {

    }

    public int getLocx() {
        return X_Pos;
    }

    public int getLocY() {
        return Y_Pos;
    }

    public JButton getButton() {
        return b;
    }

    // public JButton typeButton() {
    // return bt;
    // }

}
