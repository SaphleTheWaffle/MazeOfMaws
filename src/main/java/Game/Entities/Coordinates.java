package Game.Entities;

public class Coordinates {
    private final int x;
    private final int y;
    private final int level;

    public Coordinates(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLevel() {
        return level;
    }
}
