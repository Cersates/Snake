package onpu.oop.kurs.App2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {

    public Settings() {
        super("Настройки");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel speedLabel = new JLabel("Выберети скорость игры:");
        String[] items = new String[9];
        for (int i = 1; i < 10; i++) {
            items[i - 1] = i + "";
        }
        final JComboBox comboBox = new JComboBox(items);

        final Checkbox closedField = new Checkbox(" замкнутое поле", false);

        JButton cancel = new JButton("Назад");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Play.speed = comboBox.getSelectedIndex();
                Play.mode = closedField.getState();
                JFrame menu = new Menu();
                setVisible(false);
            }
        });

        Box sbox = Box.createHorizontalBox();
        sbox.add(speedLabel);
        sbox.add(Box.createHorizontalStrut(5));
        sbox.add(comboBox);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(sbox);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(closedField);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(cancel);

        setLocationRelativeTo(null);
        setContentPane(mainBox);

        pack();
        setVisible(true);
    }
}
