package game.world.generation;

import game.entities.Room;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RoomMapTest {

    @Test
    public void setRoomAt_givenCoordinates_setsRoomToBeAtCorrectCoordinates() {
        Coordinates coords = new Coordinates(3, 4);
        RoomMap rm = new RoomMap();
        Room room = new Room();

        rm.setRoomAt(room, coords, false);
        RoomAndCoordinates rac = rm.getRoomAt(coords);
        assertEquals(room, rac.getRoom());
        assertEquals(coords, rac.getCoords());
    }

    @Test
    public void size_afterRoomsAdded_returnsCorrectNumber() {
        int rooms = 5;
        RoomMap rm = new RoomMap();

        for (int i = 0; i < rooms; i++) {
            rm.setRoomAt(new Room(), new Coordinates(i, 0), false);
        }

        assertEquals(rooms, rm.size());
    }

    @Test
    public void setRoomAt_coordinatesAlreadyAdded_changesRoomInsteadOfAdding() {
        RoomMap rm = new RoomMap();
        Room room1 = new Room();
        Room room2 = new Room();
        Coordinates coords = new Coordinates(4, 5);

        rm.setRoomAt(room1, coords, false);
        rm.setRoomAt(room2, coords, false);

        assertEquals(1, rm.size());
        assertEquals(room2, rm.getRoomAt(coords).getRoom());
    }
}