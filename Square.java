import javax.swing.*;
import java.awt.*;

public class Square {
    private ImageIcon i = new ImageIcon("images/Water.png");
    private JButton b = new JButton();
    private int X_Pos = b.getLocation().x;
    private int Y_Pos = b.getLocation().y;

    public Square(int xPos, int yPos) {
        xPos = X_Pos;
        yPos = Y_Pos;
        b.setIcon(i);
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
}
