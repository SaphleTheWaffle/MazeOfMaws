package Game.World;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH(0, "north") {
        @Override
        public Direction getOpposite() {
            return SOUTH;
        }
    },
    WEST(1, "west") {
        @Override
        public Direction getOpposite() {
            return EAST;
        }
    },
    EAST(2, "east") {
        @Override
        public Direction getOpposite() {
            return WEST;
        }
    },
    SOUTH(3, "south") {
        @Override
        public Direction getOpposite() {
            return NORTH;
        }
    };

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

    public Direction getOpposite() {
        return NORTH;
    }
}
