import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    MyButton(String text) {
        this.setText(text);
        this.setBackground(Color.black);
        this.setForeground(Color.lightGray);
        this.setFocusable(false);
    }
}
