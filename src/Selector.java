import javax.swing.*;

public class Selector {
    static JFrame frame;
    Starter starter;

    Selector() {
        starter = new Starter();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setLayout(null);
        frame.add(starter);
        frame.setSize(616, 719);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Selector();
    }

    public static void Done() {
        frame.dispose();
    }
}
