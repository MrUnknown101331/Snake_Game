import javax.swing.*;

public class Snake {

    static JFrame frame;
    static Starter starter;

    public Snake(char diff) {
        frame = new JFrame();
        starter = new Starter();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setLayout(null);
        frame.add(new ScorePanel());
        frame.add(new SnakePanel(diff));
        frame.setSize(616, 719);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void over(){
        frame.dispose();
    }

}