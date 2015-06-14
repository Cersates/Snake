package onpu.oop.kurs;

import onpu.oop.kurs.Food.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class SnakeGame extends JPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnakeGame.class);

    private final int WALL_STEP = 10;
    private final Random random = new Random();
    private Shape walls1 = new Polygon();
    private Shape walls2 = new Polygon();
    private Shape walls3 = new Polygon();
    private Snake snake = new Snake(2, 2, WALL_STEP);
    private Food orange;
    private Food cherry;
    private int points = 0;
    private boolean gameOver;
    private int lastPressedKey = 0;
    private String message = null;
    private String level = null;

    public SnakeGame(String level) {
        super(true);
        this.level = level;
        Dimension dimensionLevel = getLevel(level);
        LOGGER.info("Load level \"" + level + "\"");
        LOGGER.info("Selected speed " + Util.getSpeed());
        orange = new Food(WALL_STEP);
        cherry = new Food(WALL_STEP);
        orange.setColor(Color.orange);
        cherry.setColor(Color.red);
        orange.setPoints(50);
        cherry.setPoints(100);
        orange.putFood(walls2);
        cherry.putFood(walls2);
        setPreferredSize(dimensionLevel);
        setMinimumSize(dimensionLevel);
        setMaximumSize(dimensionLevel);
        setSize(dimensionLevel);

        Thread th = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    gameCycle();
                    try {
                        Thread.sleep(500 - snake.getSpeed());
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        th.start();
    }

    public Snake getSnake() {
        return snake;
    }

    private Dimension getLevel(String fileName) {
        fileName = "levels/".concat(fileName);
        int minX = 500, maxX = 0, minY = 500, maxY = 0;
        Area w1 = new Area();
        Area w2 = new Area();
        Area w3 = new Area();

        BufferedReader input = null;
        try {
            File file = new File(fileName);
            input = new BufferedReader(new FileReader(file));
            String line = null;

            for (int y = 0; (line = input.readLine()) != null; y++) {
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == '1') {
                        minX = Math.min(minX, x * WALL_STEP);
                        maxX = Math.max(maxX, (x + 1) * WALL_STEP + 4);
                        minY = Math.min(minY, y * WALL_STEP);
                        maxY = Math.max(maxY, (y + 1) * WALL_STEP + 4);

                        w1.add(new Area(new Rectangle(x * WALL_STEP, y * WALL_STEP, WALL_STEP, WALL_STEP)));
                        w2.add(new Area(new Rectangle(x * WALL_STEP + 2, y * WALL_STEP + 2, WALL_STEP, WALL_STEP)));
                        w3.add(new Area(new Rectangle(x * WALL_STEP + 4, y * WALL_STEP + 4, WALL_STEP, WALL_STEP)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        walls1 = w1;
        walls2 = w2;
        walls3 = w3;
        Dimension d = new Dimension(maxX - minX, maxY - minY);
        return d;
    }

    public void gameCycle() {
        if (snake.getDirection() != Snake.DIR_PAUSE) {
            setMessage(null);
        }
        Point p = snake.move();
        if (p.x == orange.getX() && p.y == orange.getY()) {
            points += orange.getPoints();
            snake.expand();
            orange.putFood(walls2);
        }
        if (p.x == cherry.getX() && p.y == cherry.getY()) {
            points += cherry.getPoints();
            snake.expand();
            cherry.putFood(walls2);
        }
        if (walls2.contains(p)) {
            if (snake.getDirection() != Snake.DIR_PAUSE) {
                points -= 50;
                gameOver = true;
            }
            snake.setDirection(Snake.DIR_PAUSE);
            setMessage("Game over!");
        }
        this.repaint();
    }

    private void setMessage(String msg) {
        message = msg;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.gray);
        g2.fill(walls3);

        g2.setColor(Color.lightGray);
        g2.fill(walls1);

        g2.setColor(Color.black);
        g2.fill(walls2);

        g2.setColor(orange.getColor());
        g2.fillArc(orange.getX(), orange.getY(), WALL_STEP, WALL_STEP, 0, 360);

        g2.setColor(Color.black);
        g2.drawArc(orange.getX(), orange.getY(), WALL_STEP, WALL_STEP, 0, 360);

        g2.setColor(cherry.getColor());
        g2.fillArc(cherry.getX(), cherry.getY(), WALL_STEP, WALL_STEP, 0, 360);

        g2.setColor(Color.black);
        g2.drawArc(cherry.getX(), cherry.getY(), WALL_STEP, WALL_STEP, 0, 360);

        snake.paint(g2);
        g2.setColor(Color.black);

//        g2.drawString("Points: " + points, 2, 10);
        Game.dlg.setTitle("Points: " + getPoints());


        if (message != null) {
            g2.setColor(Color.yellow);
            g2.fillRect(150, 100, 100, 30);
            g2.setColor(Color.black);
            g2.drawRect(150, 100, 100, 30);
            g2.drawString(message, 160, 120);
        }
    }

    public void processKey(KeyEvent ev) {
        Snake snake = getSnake();
        switch (ev.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (lastPressedKey != KeyEvent.VK_LEFT && !gameOver) {
                    snake.setDirection(Snake.DIR_RIGHT);
                    lastPressedKey = KeyEvent.VK_RIGHT;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (lastPressedKey != KeyEvent.VK_RIGHT && !gameOver) {
                    snake.setDirection(Snake.DIR_LEFT);
                    lastPressedKey = KeyEvent.VK_LEFT;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (lastPressedKey != KeyEvent.VK_UP && !gameOver) {
                    snake.setDirection(Snake.DIR_DOWN);
                    lastPressedKey = KeyEvent.VK_DOWN;
                }
                break;
            case KeyEvent.VK_UP:
                if (lastPressedKey != KeyEvent.VK_DOWN && !gameOver) {
                    snake.setDirection(Snake.DIR_UP);
                    lastPressedKey = KeyEvent.VK_UP;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    void setScore(int x) {
        points = points + x;
    }

    public void newGame() {
        gameOver = false;
        points = 0;
        lastPressedKey = 0;
    }

    int getPoints() {
        return points;
    }
}
