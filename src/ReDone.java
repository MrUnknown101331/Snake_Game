import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReDone implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SnakePanel.button) {
            Snake.over();
            new Selector();
        }
    }
}
