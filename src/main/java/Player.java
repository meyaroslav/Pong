import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 100;
    private static final int SPEED = 10;

    private int x, y;
    private int upKey, downKey;
    private boolean up = false, down = false;

    public Player(int x, int y, int upKey, int downKey) {
        this.x = x;
        this.y = y;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void move() {
        if (up && y > 0) {
            y -= SPEED;
        }
        if (down && y < GamePanel.getPanelHeight() - HEIGHT) {
            y += SPEED;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == upKey) {
            up = true;
        }
        if (key == downKey) {
            down = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == upKey) {
            up = false;
        }
        if (key == downKey) {
            down = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
