package onpu.oop.kurs;

import onpu.oop.kurs.forms.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Start Main class");
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        System.out.println("ssss");
    }

}
