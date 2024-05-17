import java.awt.*;

public class Obstacle {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 100;

    private int x, y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
