package game.world.generation;

import game.entities.Room;
import game.entities.items.Item;
import game.entities.templates.Encounter;
import game.entities.templates.RoomType;
import game.world.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericMazeBuilder extends MazeBuilder {

    public GenericMazeBuilder() {
        super();
    }

    public RoomMap getMap() {
        return map;
    }

    public Room getEntrance() {
        return entrance;
    }

    @Override
    public void build() {
        map = new RoomMap();
        map.setRoomAt(new Room(), 0, 0, false);

        while (map.size() < 25) {
            for (int i = 0; i < map.size(); i++) {
                buildConnectingRooms(map.getRoomByIndex(i));
                if (map.size() >= 25) {
                    break;
                }
            }
        }
        setRoomTypes();
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
        setDeadEndRoomTypes();
        setRegularRoomTypes();
    }

    private void setRegularRoomTypes() {
        map.getRooms().stream()
                .filter(e -> !e.getRoom().hasType())
                .forEach(e -> e.getRoom().setType(templates.getRoomType()));
    }

    private void setDeadEndRoomTypes() {
        List<RoomAndCoordinates> deadEnds = map.getDeadEnds();
        Collections.shuffle(deadEnds);
        List<RoomType> deadEndTypes = new ArrayList<>();
        deadEndTypes.add(templates.getByCategory("entrance"));
        deadEndTypes.add(templates.getByCategory("boss"));
        deadEndTypes.addAll(templates.getNumberByCategory("cell", 4));

        for (int i = 0; i < deadEnds.size() && i < deadEndTypes.size(); i++) {
            Room currentRoom = deadEnds.get(i).getRoom();
            currentRoom.setType(deadEndTypes.get(i));
            if (i == 0) {
                entrance = currentRoom;
                entrance.visit();
            } else if (currentRoom.getTypeCategories().contains("boss")) {
                setExitLocked(currentRoom);
            } else {
                currentRoom.getInventory().addItem(new Item("key", true, "a large key", "A large, heavy key engraved with fancy designs."));
            }
        }
    }

    private void setExitLocked(Room currentRoom) {
        for (int i = 0; i < Direction.values().length; i++) {
            Room neighbour = currentRoom.getExit(Direction.valueOf(i));
            if (neighbour != null) {
                neighbour.setEncounter(new Encounter("test", true, Direction.valueOf(i).getOpposite(), true));
            }
        }
    }
}