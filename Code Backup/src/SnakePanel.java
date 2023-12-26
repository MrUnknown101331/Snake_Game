import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 80;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int AppleEaten;
    int AppleX, AppleY;
    char direction = 'E';
    boolean running = false;
    boolean ispaused = false;
    Timer timer;
    Random random;
    boolean f = true;

    SnakePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            /*
             for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
             g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
             g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
             }
             */

            g.setColor(Color.red);
            g.fillOval(AppleX, AppleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.blue);
            g.setFont(new Font("MV Boli", Font.PLAIN, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score : " + AppleEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : " + AppleEaten)) / 2, g.getFont().getSize());
            if (ispaused)
                drawpause(g);
        } else
            gameOver(g);
    }

    public void newApple() {
        AppleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        AppleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'N' -> y[0] = y[0] - UNIT_SIZE;
            case 'S' -> y[0] = y[0] + UNIT_SIZE;
            case 'W' -> x[0] = x[0] - UNIT_SIZE;
            case 'E' -> x[0] = x[0] + UNIT_SIZE;
        }
    }

    public void checkApple() {
        if ((x[0] == AppleX) && (y[0] == AppleY)) {
            bodyParts++;
            AppleEaten++;
            newApple();
        }

    }

    public void checkCollisions() {
        //check collision with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && y[0] == y[i]) {
                running = false;
                break;
            }
        }

        //check collision with walls
        if ((x[0] < 0) || (x[0] > SCREEN_WIDTH) || (y[0] < 0) || (y[0] > SCREEN_HEIGHT))
            running = false;

        if (!running)
            timer.stop();
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(AppleX, AppleY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }

        g.setColor(Color.blue);
        g.setFont(new Font("MV Boli", Font.PLAIN, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score : " + AppleEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score : " + AppleEaten)) / 2, g.getFont().getSize());

        g.setColor(Color.blue);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    public void drawpause(Graphics g) {
        g.setColor(Color.blue);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Paused", (SCREEN_WIDTH - metrics.stringWidth("Paused")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            f = true;
            if (!ispaused) {
                move();
                checkApple();
                checkCollisions();
            }
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (f) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'E')
                            direction = 'W';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'W')
                            direction = 'E';
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'S')
                            direction = 'N';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'N')
                            direction = 'S';
                        break;
                    case KeyEvent.VK_SPACE:
                        ispaused = !ispaused;
                        break;
                }
                f = false;
            }

        }
    }
}
