package game.world.generation;

import game.entities.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomMap {
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

    public ArrayList<RoomAndCoordinates> getRooms() {
        return rooms;
    }

    void setRoomAt(Room room, int x, int y, boolean corridor) {
        setRoomAt(room, new Coordinates(x, y), corridor);
    }

    void setRoomAt(Room room, Coordinates coords, boolean corridor) {
        for (RoomAndCoordinates rac : rooms) {
            if (rac.getCoords().equals(coords)) {
                rac.setRoom(room);
                return;
            }
        }
        rooms.add(new RoomAndCoordinates(coords, room, corridor));
    }

    public List<RoomAndCoordinates> getDeadEnds() {
        return rooms.stream()
                .filter(e -> e.getRoom().numberOfExits() == 1)
                .collect(Collectors.toList());
    }
}
