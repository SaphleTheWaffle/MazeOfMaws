package game.entities;

import game.world.Direction;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class RoomTest {

    @Test
    public void equals_returnsTrueForSameRoomObjectOnly() {
        Room room1 = new Room();
        Room room2 = new Room();

        assertEquals(room1, room1);
        assertNotEquals(room1, room2);
    }

    @Test
    public void setExit_setsConnectionInTheRightDirection() {
        Room room1 = new Room();
        Room room2 = new Room();
        Direction dir = Direction.NORTH;

        room1.setExit(room2, dir);
        assertEquals(room2, room1.getExit(dir));
    }

    @Test
    public void getExits_withExitsSet_givesCorrectString() {
        Room origin = new Room();
        Room[] exits = new Room[Direction.values().length];
        for (int i = 0; i < exits.length; i++) {
            exits[i] = new Room();
            origin.setExit(exits[i], Direction.valueOf(i));
        }

        assertEquals("north, west, east, south", origin.listExits());
    }
}