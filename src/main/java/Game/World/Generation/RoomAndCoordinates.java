package Game.World.Generation;

import Game.Entities.Room;
import Game.World.Direction;

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
