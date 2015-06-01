package onpu.oop.kurs.Food.impl;

import onpu.oop.kurs.Food.Food;

import java.awt.*;
import java.util.Random;

public class Cherry extends Food {

    public Cherry(int wallStep) {
        super(wallStep);
        color = Color.red;
        super.points = 50;
    }
    
    @Override
    public void putFood(Shape walls2) {
        x = 5;
        y = 5;

        while (walls2.contains(x, y)) {
            setX(wallStep * new Random().nextInt(40) + 2);
            setY(wallStep * new Random().nextInt(40) + 2);
        }
    }

}
