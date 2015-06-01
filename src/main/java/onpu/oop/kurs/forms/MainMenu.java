package onpu.oop.kurs.forms;

import onpu.oop.kurs.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);

    private JButton newGameBtn;
    private JButton exitBtn;

    public MainMenu() {
        setTitle("Snake game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 300, 310);
        setResizable(false);
        setLocationRelativeTo(null);
        LOGGER.info("Create main menu of game");

        JPanel contentPane = new BackgroundPanel("snake-300x288.jpg");
        contentPane.setLayout(new FlowLayout());
        LOGGER.info("Selected background \"snake-300x288.jpg\"");

        newGameBtn = new JButton("New game");
        exitBtn = new JButton("Exit");
//        newGameBtn.setContentAreaFilled(false);
//        exitBtn.setContentAreaFilled(false);
//        newGameBtn.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new LineBorder(Color.BLUE, 2)));
//        exitBtn.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new LineBorder(Color.BLUE, 2)));

        newGameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Game().startGame();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        contentPane.add(Box.createVerticalStrut(230));
        contentPane.add(newGameBtn);
        contentPane.add(exitBtn);
        setContentPane(contentPane);

    }


}
