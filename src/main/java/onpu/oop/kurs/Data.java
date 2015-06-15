package onpu.oop.kurs;

public class Data {
    private static int speed = 400;
    private static String startLevel = "level-1.lvl";

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Data.speed = speed;
    }

    public static String getStartLevel() {
        return startLevel;
    }

    public static void setStartLevel(String startLevel) {
        Data.startLevel = startLevel;
    }
}
