package onpu.oop.kurs.App2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SnakeHead extends SnakePart implements Draw {

    public static final int MOVE_UP = 0, MOVE_DOWN = 1, MOVE_LEFT = 2, MOVE_RIGHT = 3;
    private static int bsize = SnakePart.bsize;
    public static boolean net[][] = new boolean[800 / bsize + 1][600 / bsize + 1];
    private int moveSide = 3;
    private boolean mode;
    private ArrayList<SnakePart> parts;
    private Food f;
    private Timer time = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            move(moveSide);
        }
    });

    public SnakeHead(int speed, boolean mode) {
        super(5, 5);
        System.out.println(net.length + "/" + net[0].length);
        parts = new ArrayList<SnakePart>();
        parts.add(this);
        parts.add(new SnakePart(5, 5));
        parts.add(new SnakePart(4, 5));
        parts.add(new SnakePart(3, 5));
        parts.add(new SnakePart(2, 5));

        f = new Food();

        time.setDelay(500 - speed * 60);

        this.mode = mode;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (int i = 1; i < parts.size(); i++) {
            parts.get(i).draw(g);
        }
        f.draw(g);
    }

    public void move(int side) {
        switch (side) {
            case MOVE_UP:
                if (moveSide == MOVE_DOWN) {
                    move(posx, posy + bsize);
                    break;
                }
                move(posx, posy - bsize);
                moveSide = side;
                break;
            case MOVE_DOWN:
                if (moveSide == MOVE_UP) {
                    move(posx, posy - bsize);
                    break;
                }
                move(posx, posy + bsize);
                moveSide = side;
                break;
            case MOVE_LEFT:
                if (moveSide == MOVE_RIGHT) {
                    move(posx + bsize, posy);
                    break;
                }
                move(posx - bsize, posy);
                moveSide = side;
                break;
            case MOVE_RIGHT:
                if (moveSide == MOVE_LEFT) {
                    move(posx - bsize, posy);
                    break;
                }
                move(posx + bsize, posy);
                moveSide = side;
                break;

            default:
                System.err.println("Side chose error");
        }

        if (mode) {
            if (posx < 0) {
                posx = 800 - bsize;
            }
            if (posx >= 800) {
                SnakeHead.net[posx / bsize][posy / bsize] = false;
                posx = 0;
            }
            if (posy < 0) {
                posy = 600 - bsize * 2;
            }
            if (posy >= 600 - bsize) {
                SnakeHead.net[posx / bsize][posy / bsize] = false;
                posy = 0;
            }
        } else {
            if (posx < 0 || posx >= 800 || posy < 0 || posy >= 600 - bsize) {
                Play.end = true;
            }
        }

        for (int i = parts.size() - 1; i > 0; --i) {
            SnakePart tmp = parts.get(i - 1);
            if (i == parts.size() - 1) {
                parts.get(i).move(tmp.posx, tmp.posy, true);
            } else {
                parts.get(i).move(tmp.posx, tmp.posy);
            }
        }

        if (posx == f.posx && posy == f.posy) {
            f = new Food();
            parts.add(new SnakePart(posx / bsize, posy / bsize));
            parts.add(new SnakePart(posx / bsize, posy / bsize));
            Play.score++;
        }
    }

    @Override
    public void move(int x, int y) {
        posx = x;
        posy = y;
        if (x >= 0 && y >= 0) {
            if (SnakeHead.net[x / bsize][y / bsize]) {
                Play.end = true;
            }
            SnakeHead.net[x / bsize][y / bsize] = true;
        }
    }

    public void start() {
        time.start();
    }

    public void stop() {
        time.stop();
    }
}
