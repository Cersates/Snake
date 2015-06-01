package onpu.oop.kurs.App2;

import java.awt.Color;
import java.awt.Graphics;

public class SnakePart implements Draw {

    public static int bsize = 20;
    protected int posx, posy;

    public SnakePart(int x, int y) {
        posx = x * bsize;
        posy = y * bsize;
    }

    @Override
    public void draw(Graphics g) {
        int xPoints[] = {posx, posx + bsize, posx + bsize, posx};
        int yPoints[] = {posy, posy, posy + bsize, posy + bsize};
        g.setColor(Color.GRAY);
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public void move(int x, int y) {
        //SnakeHead.net[posx/bsize][posy/bsize] = false;
        posx = x;
        posy = y;
    }

    public void move(int x, int y, boolean hlam) {
        SnakeHead.net[posx / bsize][posy / bsize] = false;
        posx = x;
        posy = y;
        //SnakeHead.net[x/bsize][y/bsize] = true; 
    }
}
