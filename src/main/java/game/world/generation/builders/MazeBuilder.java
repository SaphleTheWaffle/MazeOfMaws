package game.world.generation.builders;

import game.entities.Room;
import game.entities.obstacles.Obstacle;
import game.entities.templates.Templates;
import game.world.Direction;
import game.world.generation.RoomMap;

import java.util.Random;

public abstract class MazeBuilder {

    Random random;
    RoomMap map;
    Room entrance;
    Templates templates;

    MazeBuilder() {
        this.random = new Random();
        this.map = new RoomMap();
        this.templates = Templates.getInstance();
    }

    public abstract void build();

    public RoomMap getMap() {
        return map;
    }

    public Room getEntrance() {
        return entrance;
    }

    void setExitLocked(Room currentRoom, String keyId) {
        for (int i = 0; i < Direction.values().length; i++) {
            setExitLocked(currentRoom, Direction.valueOf(i), keyId);
        }
    }

    void setExitLocked(Room currentRoom, Direction direction, String keyId) {
        Room neighbour = currentRoom.getExit(direction);
        if (neighbour != null) {
            neighbour.addObstacle(new Obstacle(direction.getOpposite(), keyId, true));
            currentRoom.addObstacle(new Obstacle(direction, keyId, true));
        }
    }

    void connectRooms(Room thisRoom, Direction dir, Room neighbour) {
        thisRoom.setExit(neighbour, dir);
        neighbour.setExit(thisRoom, dir.getOpposite());
    }
}
