package onpu.oop.kurs.App2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food implements Draw {

    public int posx, posy;

    public Food() {
        int bsize = SnakePart.bsize;
        boolean done = true;
        int x = 5, y = 5;
        while (done) {
            x = randInt(0, (800 - bsize) / bsize);
            y = randInt(0, (600 - bsize * 2) / bsize);
            if (!SnakeHead.net[x][y]) {
                done = false;
            }
        }
        posx = x * bsize;
        posy = y * bsize;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(posx, posy, SnakePart.bsize, SnakePart.bsize);
    }

    // this is from internet
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
