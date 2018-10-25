package Game.World.Generation;

import Game.Entities.Room;
import Game.World.Direction;

import java.util.Random;

public class MazeBuilder {

    private Random random;
    private RoomMap map;

    public Room build() {
        random = new Random();
        map = new RoomMap();
        Room entrance = new Room();
        map.setRoomAt(entrance, 0, 0);

        while (map.size() < 25) {
            for (int i = 0; i < map.size(); i++) {
                buildConnectingRooms(map.getRoomByIndex(i));
            }
        }
        return entrance;
    }

    private void buildConnectingRooms(RoomAndCoordinates rac) {
        if (rac == null) {
            return;
        }
        for (int i = 0; i < Direction.values().length - 1; i++) {
            Coordinates neighbour = rac.getNeighbour(Direction.valueOf(i));
            if (random.nextInt(100) > 50 && neighbour.isValid()) {
                if (map.getRoomAt(neighbour) == null) {
                    map.setRoomAt(new Room(), neighbour);
                }
                Room thisRoom = rac.getRoom();
                Room connection = map.getRoomAt(neighbour).getRoom();
                thisRoom.setExit(connection, Direction.valueOf(i));
                connection.setExit(thisRoom, Direction.valueOf(i).getOpposite());
            }
        }
    }
}