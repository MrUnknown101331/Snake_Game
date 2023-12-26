import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Starter extends JPanel implements ActionListener {
    MyButton easy, medium, hard;
    JLabel Version;
    String version = "Version - 1.0.0";

    Starter() {
        this.setBounds(0, 0, 600, 680);
        easy = new MyButton("EASY");
        medium = new MyButton("MEDIUM");
        hard = new MyButton("HARD");
        Version = new JLabel(version);
        this.setLayout(null);
        this.setBackground(Color.black);
        easy.setBounds(183, 180, 250, 100);
        easy.addActionListener(this);
        medium.setBounds(183, 290, 250, 100);
        medium.addActionListener(this);
        hard.setBounds(183, 400, 250, 100);
        hard.addActionListener(this);
        Version.setBounds(210, 90, 250, 50);
        Version.setForeground(Color.lightGray);
        Version.setFont(new Font("MV Boli", Font.PLAIN, 25));
        this.add(easy);
        this.add(medium);
        this.add(hard);
        this.add(Version);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char d = 'A';
        if (e.getSource() == easy) {
            d = 'E';
        }
        if (e.getSource() == medium) {
            d = 'M';
        }
        if (e.getSource() == hard) {
            d = 'H';
        }
        new Snake(d);
        Selector.Done();
    }
}
