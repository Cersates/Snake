package onpu.oop.kurs.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackgroundPanel.class);

    private String imageName;

    public BackgroundPanel(String image_mame) {
        this.imageName = "img/" + image_mame;
        LOGGER.info("Create panel with background \"" + this.imageName + "\"");

    }

    @Override
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File(imageName));
        } catch (IOException e) {
        }
        g.drawImage(im, 0, 0, null);
    }
}
