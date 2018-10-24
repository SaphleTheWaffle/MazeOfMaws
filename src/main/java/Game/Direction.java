package Game;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH(0, "north"),
    WEST(1, "west"),
    EAST(2, "east"),
    SOUTH(3, "south"),
    DOWN(4, "down");

    public int index;
    public String name;

    Direction(final int index, String name) {
        this.index = index;
        this.name = name;
    }

    private static Map<Integer, Direction> map = new HashMap<>();

    static {
        for (Direction dir : Direction.values()) {
            map.put(dir.index, dir);
        }
    }

    public static Direction valueOf(int index) {
        return map.get(index);
    }
}
