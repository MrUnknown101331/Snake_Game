import javax.swing.*;

public class Snake extends JFrame {
    public Snake() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.add(new SnakePanel());
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Snake();
    }
}
