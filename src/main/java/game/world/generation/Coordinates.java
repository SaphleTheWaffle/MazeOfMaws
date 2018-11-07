package game.world.generation;

import game.world.Direction;

public class Coordinates {
    private int x;
    private int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates getNeighbour(Direction dir) {
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
        if (o.getClass() == this.getClass()) {
            Coordinates c = (Coordinates) o;
            return this.x == c.getX() && this.y == c.getY();
        }
        return false;
    }
}
