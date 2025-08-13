import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class DesktopSnake extends JFrame implements ActionListener, KeyListener {
    private final int TILE_SIZE = 20;
    private int screenWidth, screenHeight;
    private ArrayList<Point> snake;
    private Point apple;
    private int dx = 1, dy = 0; // moving right initially
    private Timer timer;
    private Random rand = new Random();

    public DesktopSnake() {
        // Get screen size
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screen.width;
        screenHeight = screen.height;

        // Setup JFrame
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // fully transparent
        setSize(screenWidth, screenHeight);
        setLocation(0, 0);
        setAlwaysOnTop(true);
        setFocusable(true);
        addKeyListener(this);

        // Initialize snake
        snake = new ArrayList<>();
        int startX = (screenWidth / TILE_SIZE / 2) * TILE_SIZE;
        int startY = (screenHeight / TILE_SIZE / 2) * TILE_SIZE;
        snake.add(new Point(startX, startY));

        spawnApple();

        // Timer for movement
        timer = new Timer(100, this);
        timer.start();

        setVisible(true);
    }

    private void spawnApple() {
        int x = rand.nextInt(screenWidth / TILE_SIZE) * TILE_SIZE;
        int y = rand.nextInt(screenHeight / TILE_SIZE) * TILE_SIZE;
        apple = new Point(x, y);
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) img.getGraphics();

        // Draw snake
        g2.setColor(Color.GREEN);
        for (Point p : snake) {
            g2.fillRect(p.x, p.y, TILE_SIZE, TILE_SIZE);
        }

        // Draw apple
        g2.setColor(Color.RED);
        g2.fillOval(apple.x, apple.y, TILE_SIZE, TILE_SIZE);

        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move snake
        Point head = snake.get(0);
        int newX = head.x + dx * TILE_SIZE;
        int newY = head.y + dy * TILE_SIZE;

        // Wrap around screen
        if (newX < 0) newX = screenWidth - TILE_SIZE;
        if (newX >= screenWidth) newX = 0;
        if (newY < 0) newY = screenHeight - TILE_SIZE;
        if (newY >= screenHeight) newY = 0;

        Point newHead = new Point(newX, newY);

        // Check apple collision
        if (newHead.equals(apple)) {
            snake.add(0, newHead); // grow
            spawnApple();
        } else {
            snake.add(0, newHead);
            snake.remove(snake.size() - 1);
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (key == KeyEvent.VK_LEFT && dx == 0) { dx = -1; dy = 0; }
        if (key == KeyEvent.VK_RIGHT && dx == 0) { dx = 1; dy = 0; }
        if (key == KeyEvent.VK_UP && dy == 0) { dx = 0; dy = -1; }
        if (key == KeyEvent.VK_DOWN && dy == 0) { dx = 0; dy = 1; }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        // Enable transparent window support
        System.setProperty("sun.java2d.noddraw", "true");
        System.setProperty("sun.java2d.opengl", "true");
        new DesktopSnake();
    }
}
