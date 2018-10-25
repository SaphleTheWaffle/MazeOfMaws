package Game;

import Game.Entities.Room;

import java.util.Random;
import java.util.Vector;

public class MazeBuilder {

    private int dimX;
    private int dimY;
    private Random random;
    private Vector<Room> rooms;

    public Room build(int x, int y) {
        this.dimX = x;
        this.dimY = y;
        random = new Random();
        rooms = new Vector<>(x*y);
        rooms.setSize(x*y);
        Room entrance = new Room();
        rooms.add(getIndexFromPos(0, 0), entrance);

        while (rooms.size() < 25) {
            for (int i = 0; i < rooms.capacity(); i++) {
                buildConnectingRooms(rooms.get(i), i);
            }
        }
        return entrance;
    }

    private int getIndexFromPos(int x, int y) {
        return x + (y * dimY);
    }

    private void buildConnectingRooms(Room room, int index) {
        if (room == null) {
            return;
        }
        for (int i = 0; i < Direction.values().length; i++) {
            int indexOfNeighbour = indexOfNeighbour(index, Direction.valueOf(i));
            if (random.nextInt(100) > 50 && indexOfNeighbour >= 0) {
                Room neighbour = rooms.get(indexOfNeighbour);
                if (neighbour == null) {
                    neighbour = new Room();
                }
                room.setExit(neighbour, Direction.valueOf(i));
                neighbour.setExit(room, Direction.valueOf(i).getOpposite());
                rooms.add(indexOfNeighbour, neighbour);
            }
        }
    }

    private int getXFromIndex(int index) {
        if (index < 0) {
            return -1;
        }
        return index % dimY;
    }

    private int getYFromIndex(int index) {
        if (index < 0) {
            return -1;
        }
        return index / dimY;
    }

    private int indexOfNeighbour(int index, Direction dir) {
        if (index < 0) {
            return -1;
        }
        int x = getXFromIndex(index);
        int y = getYFromIndex(index);

        switch(dir) {
            case NORTH:
                return getIndexFromPos(x, y-1);
            case WEST:
                return getIndexFromPos(x-1, y);
            case EAST:
                return getIndexFromPos(x+1, y);
            case SOUTH:
                return getIndexFromPos(x, y+1);
            case DOWN:
                return index;
        }
        return -1;
    }
}