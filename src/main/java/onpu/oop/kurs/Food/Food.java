package onpu.oop.kurs.Food;

import java.awt.*;
import java.util.Random;

public class Food {

    private int points = 50;
    private Color color = Color.green;
    private int x;
    private int y;
    private int wallStep;

    public Food(int wallStep) {
        this.wallStep = wallStep;
    }

    public void putFood(Shape walls2) {
        x = 5;
        y = 5;

        while (walls2.contains(x, y)) {
            setX(wallStep * new Random().nextInt(40) + 2);
            setY(wallStep * new Random().nextInt(40) + 2);
        }
    }

    ;


    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWallStep() {
        return wallStep;
    }

    public void setWallStep(int wallStep) {
        this.wallStep = wallStep;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
