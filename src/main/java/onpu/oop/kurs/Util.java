package onpu.oop.kurs;

public class Util {
    private static int speed = 400;
    private static String startLevel = "level-1.lvl";

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Util.speed = speed;
    }

    public static String getStartLevel() {
        return startLevel;
    }

    public static void setStartLevel(String startLevel) {
        Util.startLevel = startLevel;
    }
}
