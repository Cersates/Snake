package onpu.oop.kurs.forms;

import onpu.oop.kurs.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    JButton jButton1;
    JButton jButton2;

    public MainMenu() {
        setTitle("Snake game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 300, 310);
//        setResizable(false);
        setLocationRelativeTo(null);
        LOGGER.info("Create main menu of game");


        JPanel contentPane = new BackgroundPanel("snake-300x288.jpg");
        contentPane.setLayout(new FlowLayout());

        jButton1 = new JButton("New game");
        jButton2 = new JButton("Exit");
//        jButton1.setContentAreaFilled(false);
//        jButton2.setContentAreaFilled(false);
//        jButton1.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new LineBorder(Color.BLUE, 2)));
//        jButton2.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new LineBorder(Color.BLUE, 2)));

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        contentPane.add(Box.createVerticalStrut(230));
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        setContentPane(contentPane);

    }
}
