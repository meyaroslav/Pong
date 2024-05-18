import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Ball ball;
    private Player player1, player2;
    private Obstacle[] obstacles = new Obstacle[5];
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private boolean gameRunning = true;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        ball = new Ball(WIDTH / 2 - Ball.DIAMETER / 2, HEIGHT / 2 - Ball.DIAMETER / 2);
        player1 = new Player(0, HEIGHT / 2 - Player.HEIGHT / 2, KeyEvent.VK_W, KeyEvent.VK_S);
        player2 = new Player(WIDTH - Player.WIDTH, HEIGHT / 2 - Player.HEIGHT / 2, KeyEvent.VK_UP, KeyEvent.VK_DOWN);

        createObstacles();
        Timer timer = new Timer(1000 / 60, e -> update());
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    private void createObstacles() {
        Random rand = new Random();
        for (int i = 0; i < obstacles.length; i++) {
            int centerX = rand.nextInt(WIDTH / 2 - Obstacle.WIDTH) + WIDTH / 4;
            int centerY = rand.nextInt(HEIGHT - Obstacle.HEIGHT);
            obstacles[i] = new Obstacle(centerX, centerY);
        }
    }

    public static int getPanelWidth() {
        return WIDTH;
    }

    public static int getPanelHeight() {
        return HEIGHT;
    }

    private void update() {
        if (gameRunning) {
            ball.move();
            player1.move();
            player2.move();
            checkCollisions();
            checkScore();
        }
        repaint();
    }

    private void checkCollisions() {
        if (ball.getBounds().intersects(player1.getBounds()) || ball.getBounds().intersects(player2.getBounds())) {
            ball.reverseXSpeed();
        }

        for (Obstacle obstacle : obstacles) {
            if (ball.getBounds().intersects(obstacle.getBounds())) {
                ball.reverseXSpeed();
            }
        }
    }

    private void checkScore() {
        if (ball.getX() <= 0) {
            scorePlayer2++;
            ball.reset();
        } else if (ball.getX() >= WIDTH - Ball.DIAMETER) {
            scorePlayer1++;
            ball.reset();
        }

        if (scorePlayer1 == 10 || scorePlayer2 == 10) {
            gameRunning = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        drawScore(g);
        if (!gameRunning) {
            drawGameOver(g);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player 1: " + scorePlayer1, 20, 30);
        g.drawString("Player 2: " + scorePlayer2, WIDTH - 140, 30);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String winner = scorePlayer1 == 10 ? "Player 1" : "Player 2";
        g.drawString("Game Over! " + winner + " wins!", WIDTH / 2 - 100, HEIGHT / 2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player1.keyPressed(e);
        player2.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
        player2.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
