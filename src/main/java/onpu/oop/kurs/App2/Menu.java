package onpu.oop.kurs.App2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Menu extends JFrame {

    public static boolean ROOT;

    public Menu() {
        super("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton play = new JButton("Играть");
        //play.setMinimumSize(new Dimension(500, 50));
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Play p = new Play();
                Play.end = false;
                p.repaint();
                setVisible(false);
            }
        });

        JButton settings = new JButton("Настройки");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame set = new Settings();
                setVisible(false);
            }
        });

        JButton exit = new JButton("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        settings.setPreferredSize(new Dimension(200, 50));
        play.setPreferredSize(settings.getPreferredSize());
        exit.setPreferredSize(settings.getPreferredSize());

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 0;
        c2.fill = GridBagConstraints.BOTH;
        panel.add(play, c2);

        GridBagConstraints cvs1 = new GridBagConstraints();
        cvs1.gridx = 0;
        cvs1.gridy = 2;
        cvs1.weightx = 0;
        cvs1.fill = GridBagConstraints.BOTH;
        panel.add(Box.createVerticalStrut(10), cvs1);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 3;
        c3.weightx = 0;
        c3.fill = GridBagConstraints.BOTH;
        panel.add(settings, c3);

        GridBagConstraints cvs2 = new GridBagConstraints();
        cvs2.gridx = 0;
        cvs2.gridy = 4;
        cvs2.weightx = 0;
        cvs2.fill = GridBagConstraints.BOTH;
        panel.add(Box.createVerticalStrut(10), cvs2);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        c4.gridy = 5;
        c4.weightx = 0;
        c4.fill = GridBagConstraints.BOTH;
        panel.add(exit, c4);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
