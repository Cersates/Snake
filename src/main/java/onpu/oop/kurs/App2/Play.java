package onpu.oop.kurs.App2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Play extends JPanel {

    public static int score = 0;
    public static int speed = 7;
    public static boolean mode = true;
    public static boolean end = false;
    private int width = 800 + 6;
    private int height = 600 + 29;
    private JFrame playframe;

    private SnakeHead snake;

    public Play() {
        super();

        playframe = new JFrame("Snake");
        playframe.add(this);
        playframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playframe.setResizable(false);
        playframe.setSize(width, height);
        playframe.setVisible(true);

        snake = new SnakeHead(speed, mode);
        snake.start();

        playframe.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        snake.move(SnakeHead.MOVE_UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.move(SnakeHead.MOVE_DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.move(SnakeHead.MOVE_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.move(SnakeHead.MOVE_RIGHT);
                        break;

                    case KeyEvent.VK_ESCAPE:
                        snake.stop();

                        Object[] options = {"Продолжить", "Выйти в меню", "Выйти с игры"};
                        int n = JOptionPane.showOptionDialog(playframe,
                                "Вы нажали паузу", "Пауза",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null, options, options[2]);
                        switch (n) {
                            case 0:
                                snake.start();
                                break;
                            case 1:
                                JFrame menu = new Menu();
                                playframe.dispose();
                                break;
                            case 2:
                                System.exit(0);
                                break;
                        }

                        break;
                    default:
                        break;
                }
            }
        });

    }

    public void gameOver() {
        snake.stop();
        Object[] options = {"Выйти в меню", "Выйти с игры"};
        int n = JOptionPane.showOptionDialog(playframe,
                "Вы проиграли", "=(",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null, options, options[0]);
        if (n == 1) {
            System.exit(0);
        } else {
            JFrame menu = new Menu();
            playframe.setVisible(false);
        }
        end = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (end) {
            gameOver();
        }
        Rectangle r = g.getClipBounds();
        g.clearRect(r.x, r.y, r.width, r.height);

        g.setColor(Color.BLACK);
        g.drawLine(0, r.height - SnakePart.bsize, r.width, r.height - SnakePart.bsize);
        g.drawString("Счет: " + score * (int) (speed / 2)
                + "    Скорость: " + speed
                + "    Замкнутое поле: "
                + mode, 20, r.height - 5);
        snake.draw(g);

        repaint();
    }
}
