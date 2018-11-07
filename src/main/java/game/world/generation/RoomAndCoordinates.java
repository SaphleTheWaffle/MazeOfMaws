package game.world.generation;

import game.entities.Room;
import game.world.Direction;

public class RoomAndCoordinates {
    private Coordinates coords;
    private Room room;
    private boolean corridor;

    RoomAndCoordinates(Coordinates coords, Room room, boolean corridor) {
        this.coords = coords;
        this.room = room;
        this.corridor = corridor;
    }

    public Room getRoom() {
        return room;
    }

    void setRoom(Room room) {
        this.room = room;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public Coordinates getNeighbour(Direction dir) {
        return coords.getNeighbour(dir);
    }

    public boolean isCorridor() {
        return corridor;
    }
}
