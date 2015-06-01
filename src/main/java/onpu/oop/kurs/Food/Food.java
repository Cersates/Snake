package onpu.oop.kurs.Food;

import java.awt.*;

public abstract class Food {

//    public void putFood(Shape walls2, int wallStep);
//    public void setLocation(int x, int y);

    protected int points = 50;
    protected Color color;
    protected int x;
    protected int y;
    protected int wallStep;

    public Food(int wallStep) {
        this.wallStep = wallStep;
    }

    public abstract void putFood(Shape walls2);


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

    public int getPoints() {
        return points;
    }
}
