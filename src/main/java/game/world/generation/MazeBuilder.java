package game.world.generation;

import game.entities.Room;
import game.entities.templates.Templates;
import game.world.Direction;

import java.util.Random;

public class MazeBuilder {

    private Random random = new Random();
    private RoomMap map;
    private Templates templates;

    public RoomMap getMap() {
        return map;
    }

    public Room build() {
        map = new RoomMap();
        templates = Templates.getInstance();
        Room entrance = new Room();
        entrance.setType(templates.getEntrance());
        map.setRoomAt(entrance, 0, 0, false);

        while (map.size() < 25) {
            for (int i = 0; i < map.size(); i++) {
                buildConnectingRooms(map.getRoomByIndex(i));
                if (map.size() >= 25) {
                    break;
                }
            }
        }
        setRoomTypes();
        return entrance;
    }

    private void buildConnectingRooms(RoomAndCoordinates rac) {
        if (rac == null || (rac.isCorridor() && random.nextInt(4) < 3)) {
            return;
        }
        int i = random.nextInt(Direction.values().length);
        Direction dir = Direction.valueOf(i);

        if (random.nextInt(2) > 0) {
            RoomAndCoordinates neighbour = buildConnection(rac, dir, true);
            buildConnection(neighbour, dir, false);
        }
    }

    private RoomAndCoordinates buildConnection(RoomAndCoordinates origin, Direction dir, boolean corridor) {
        Coordinates neighbour = origin.getNeighbour(dir);
        if (overThreshold(origin) || overThreshold(map.getRoomAt(neighbour))) {
            return origin;
        }
        if (map.getRoomAt(neighbour) == null) {
            map.setRoomAt(new Room(), neighbour, corridor);
        }
        Room thisRoom = origin.getRoom();
        RoomAndCoordinates connection = map.getRoomAt(neighbour);
        thisRoom.setExit(connection.getRoom(), dir);
        connection.getRoom().setExit(thisRoom, dir.getOpposite());
        return connection;
    }

    private boolean overThreshold(RoomAndCoordinates rac) {
        return rac != null && rac.isCorridor() && rac.getRoom().numberOfExits() > 2;
    }

    private void setRoomTypes() {
        for (RoomAndCoordinates rac : map.getRooms()) {
            if (!rac.getRoom().hasType()) {
                rac.getRoom().setType(templates.getRoomType());
            }
        }
    }
}