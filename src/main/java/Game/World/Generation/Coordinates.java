package Game.World.Generation;

import Game.World.Direction;

public class Coordinates {
    private int x;
    private int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Coordinates getNeighbour(Direction dir) {
        switch (dir) {
            case NORTH:
                return new Coordinates(x, y-1);
            case WEST:
                return new Coordinates(x-1, y);
            case EAST:
                return new Coordinates(x+1, y);
            case SOUTH:
                return new Coordinates(x, y+1);
        }
        return this;
    }

    boolean isValid() {
        return x >= 0 && y >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinates) {
            Coordinates c = (Coordinates) o;
            return this.x == c.getX() && this.y == c.getY();
        }
        return false;
    }
}
