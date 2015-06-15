package onpu.oop.kurs.forms;

import onpu.oop.kurs.Data;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Settings extends JFrame {

    public Settings() {
        super("Settings");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box box0 = Box.createHorizontalBox();
        JLabel jLabel1 = new JLabel("Speed: ");
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 50, 500, 400);
        slider.setMajorTickSpacing(80);
        slider.setMinorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
//        slider.setSnapToTicks(true);
        box0.add(jLabel1);
        box0.add(Box.createHorizontalStrut(6));
        box0.add(slider);

        Box box1 = Box.createHorizontalBox();
        JLabel jLabel2 = new JLabel("Level: ");
        String levelList[] = new File("./levels/").list();
        final JComboBox levelComboBox = new JComboBox(levelList);
        box1.add(jLabel2);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(levelComboBox);

        Box box4 = Box.createHorizontalBox();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data.setSpeed(slider.getValue());
                Data.setStartLevel(levelComboBox.getSelectedItem().toString());
                dispose();
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        box4.add(Box.createHorizontalGlue());
        box4.add(ok);
        box4.add(Box.createHorizontalStrut(12));
        box4.add(cancel);

        jLabel1.setPreferredSize(jLabel1.getPreferredSize());
        jLabel2.setPreferredSize(jLabel1.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box0);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box4);
        setContentPane(mainBox);
        setLocationRelativeTo(null);

        pack();

        setResizable(false);
    }

}
