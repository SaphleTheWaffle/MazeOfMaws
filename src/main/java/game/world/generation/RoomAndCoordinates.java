package game.world.generation;

import game.entities.Room;
import game.world.Direction;

class RoomAndCoordinates {
    private Coordinates coords;
    private Room room;

    RoomAndCoordinates(Coordinates coords, Room room) {
        this.coords = coords;
        this.room = room;
    }

    Room getRoom() {
        return room;
    }

    void setRoom(Room room) {
        this.room = room;
    }

    Coordinates getCoords() {
        return coords;
    }

    Coordinates getNeighbour(Direction dir) {
        return coords.getNeighbour(dir);
    }
}
