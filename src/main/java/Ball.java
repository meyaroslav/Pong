import java.awt.*;

public class Ball {
    public static final int DIAMETER = 20;
    private static final int SPEED = 6;

    private int x, y;
    private int xSpeed = SPEED;
    private int ySpeed = SPEED;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        if (y <= 0 || y >= GamePanel.getPanelHeight() - DIAMETER) {
            ySpeed = -ySpeed;
        }
    }

    public void reverseXSpeed() {
        xSpeed = -xSpeed;
    }

    public void reset() {
        x = GamePanel.getPanelWidth() / 2 - DIAMETER / 2;
        y = GamePanel.getPanelHeight() / 2 - DIAMETER / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }
}
