import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int DELAY;
    static char difficulty;
    static MyButton button;
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
    Color bgcolor = Color.black;
    Color outline = Color.white;
    Color head_color = Color.green;
    Color snake_color = new Color(45, 180, 0);
    Color apple_color = Color.RED;
    Color text_color = Color.blue;
    Color foreground_button = Color.lightGray;


    SnakePanel(char diff) {
        difficulty = diff;
        if (difficulty == 'H')
            DELAY = 80;
        else if (difficulty == 'M')
            DELAY = 100;
        else
            DELAY = 120;
        random = new Random();
        this.setLayout(null);
        this.setFocusable(true);
        this.setBounds(0, 80, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setBackground(bgcolor);
        this.addKeyListener(new MyKeyAdapter());

        button = new MyButton("Restart");
        button.setBackground(bgcolor);
        button.setForeground(foreground_button);
        button.setFocusable(false);
        button.setVisible(false);
        button.setBounds(183, 400, 250, 100);
        button.addActionListener(new ReDone());
        this.add(button);

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
            /*
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }*/

        g.setColor(apple_color);
        g.fillOval(AppleX, AppleY, UNIT_SIZE, UNIT_SIZE);


        for (int i = bodyParts - 1; i >= 0; i--) {
            if (i == 0) {
                g.setColor(head_color);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                g.setColor(outline);
                if (x[i] - x[i + 1] == UNIT_SIZE && y[i] == y[i + 1]) {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else if (x[i + 1] - x[i] == UNIT_SIZE && y[i] == y[i + 1]) {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else if (x[i] == x[i + 1] && y[i] - y[i + 1] == UNIT_SIZE) {
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else if (x[i] == x[i + 1] && y[i + 1] - y[i] == UNIT_SIZE) {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                }
            } else if (i == bodyParts - 1) {
                g.setColor(snake_color);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                g.setColor(outline);
                if (x[i] - x[i - 1] == UNIT_SIZE && y[i] == y[i - 1]) {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else if (x[i - 1] - x[i] == UNIT_SIZE && y[i] == y[i - 1]) {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else if (x[i] == x[i - 1] && y[i] - y[i - 1] == UNIT_SIZE) {
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else if (x[i] == x[i - 1] && y[i - 1] - y[i] == UNIT_SIZE) {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                } else {
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                    g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                }
            } else {
                g.setColor(snake_color);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                g.setColor(outline);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE / 8);
                g.fillRect(x[i], y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE, UNIT_SIZE / 8);
                g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i], UNIT_SIZE / 8, UNIT_SIZE);
                g.fillRect(x[i], y[i], UNIT_SIZE / 8, UNIT_SIZE);
                g.setColor(snake_color);
                if ((x[i + 1] - x[i] == UNIT_SIZE && y[i + 1] == y[i]) || (x[i - 1] - x[i] == UNIT_SIZE && y[i] == y[i - 1])) {
                    g.fillRect(x[i] + UNIT_SIZE - (UNIT_SIZE / 8), y[i] + UNIT_SIZE / 8, UNIT_SIZE / 8, UNIT_SIZE - UNIT_SIZE / 4);
                }
                if ((x[i] - x[i + 1] == UNIT_SIZE && y[i + 1] == y[i]) || (x[i] - x[i - 1] == UNIT_SIZE && y[i] == y[i - 1])) {
                    g.fillRect(x[i], y[i] + UNIT_SIZE / 8, UNIT_SIZE / 8, UNIT_SIZE - UNIT_SIZE / 4);
                }
                if ((x[i + 1] == x[i] && y[i + 1] - y[i] == UNIT_SIZE) || (x[i - 1] == x[i] && y[i - 1] - y[i] == UNIT_SIZE)) {
                    g.fillRect(x[i] + UNIT_SIZE / 8, y[i] + UNIT_SIZE - (UNIT_SIZE / 8), UNIT_SIZE - UNIT_SIZE / 4, UNIT_SIZE / 8);
                }
                if ((x[i + 1] == x[i] && y[i] - y[i + 1] == UNIT_SIZE) || (x[i - 1] == x[i] && y[i] - y[i - 1] == UNIT_SIZE)) {
                    g.fillRect(x[i] + UNIT_SIZE / 8, y[i], UNIT_SIZE - UNIT_SIZE / 4, UNIT_SIZE / 8);
                }
            }
        }

        if (ispaused) {
            button.setVisible(true);
            drawpause(g);
        }
        if (!running)
            gameOver(g);
    }

    public void newApple() {
        AppleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        AppleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        for (int i = 0; i <= bodyParts; i++) {
            if (AppleX == x[i] && AppleY == y[i]) {
                newApple();
            }
        }

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
            ScorePanel.updateScore(AppleEaten);
            newApple();
        }

    }

    public void checkCollisions() {
        //check collision with body
        if (difficulty != 'E') {
            for (int i = bodyParts; i > 0; i--) {
                if ((x[0] == x[i]) && y[0] == y[i]) {
                    running = false;
                    break;
                }
            }
        }

        //check collision with walls
        if (difficulty == 'H') {
            if ((x[0] < 0) || (x[0] >= SCREEN_WIDTH) || (y[0] < 0) || (y[0] >= SCREEN_HEIGHT))
                running = false;
        } else {
            if (x[0] < 0)
                x[0] = SCREEN_WIDTH - UNIT_SIZE;
            if (x[0] >= SCREEN_WIDTH)
                x[0] = 0;
            if (y[0] < 0)
                y[0] = SCREEN_HEIGHT - UNIT_SIZE;
            if (y[0] >= SCREEN_HEIGHT)
                y[0] = 0;
        }

        if (!running)
            timer.stop();
    }

    public void gameOver(Graphics g) {
        button.setVisible(true);

        g.setColor(text_color);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    public void drawpause(Graphics g) {
        g.setColor(text_color);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Paused", (SCREEN_WIDTH - metrics.stringWidth("Paused")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            f = true;
            if (!ispaused) {
                button.setVisible(false);
                move();
                checkCollisions();
                checkApple();
            }
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (f) {
                if (!ispaused) {
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
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    ispaused = !ispaused;
                f = false;
            }

        }
    }
}
