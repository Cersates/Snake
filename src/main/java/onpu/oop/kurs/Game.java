package onpu.oop.kurs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game {
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private JDialog dlg;
    private SnakeGame snakeGame;

    public void startGame() {
        LOGGER.info("Run game");
        dlg = new JDialog((JFrame) null, "Snake game");
        dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        snakeGame = new SnakeGame();
        dlg.getContentPane().add(snakeGame);
        snakeGame.newGame();
        dlg.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                snakeGame.processKey(ev);
            }
        });
        dlg.setVisible(true);
        dlg.pack();
        dlg.setLocationRelativeTo(null);
    }
}
