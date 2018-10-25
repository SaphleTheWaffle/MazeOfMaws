package game.world.generation;

import game.entities.Room;

import java.util.ArrayList;

class RoomMap {
    private ArrayList<RoomAndCoordinates> rooms;

    RoomMap() {
        rooms = new ArrayList<>();
    }

    int size() {
        return rooms.size();
    }

    RoomAndCoordinates getRoomAt(Coordinates coords) {
        for (RoomAndCoordinates rac : rooms) {
            if (rac.getCoords().equals(coords)) {
                return rac;
            }
        }
        return null;
    }

    RoomAndCoordinates getRoomByIndex(int index) {
        return rooms.get(index);
    }

    void setRoomAt(Room room, int x, int y) {
        setRoomAt(room, new Coordinates(x, y));
    }

    void setRoomAt(Room room, Coordinates coords) {
        for (RoomAndCoordinates rac : rooms) {
            if (rac.getCoords().equals(coords)) {
                rac.setRoom(room);
                return;
            }
        }
        rooms.add(new RoomAndCoordinates(coords, room));
    }
}
