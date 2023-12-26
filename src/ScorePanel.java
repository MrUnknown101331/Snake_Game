import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel{
    static JLabel label;

    ScorePanel() {
        this.setBounds(0, 0, 600, 80);
        label = new JLabel("Score : 0");
        label.setForeground(Color.green);
        label.setFont(new Font("MV Boli", Font.PLAIN, 40));
        this.setBorder(BorderFactory.createLineBorder(Color.blue,5));
        this.add(label);
        this.setBackground(Color.black);
    }


    public static void updateScore(int score) {
        label.setText("Score : " + score);
    }
}
