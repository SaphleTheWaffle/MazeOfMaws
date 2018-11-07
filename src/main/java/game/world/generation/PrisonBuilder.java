package game.world.generation;

import game.entities.Room;
import game.entities.items.Item;
import game.entities.templates.RoomType;
import game.world.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrisonBuilder extends MazeBuilder {
    @Override
    public void build() {
        map = new RoomMap();
        entrance = null;

        createHallways();
        connectHallways();

        setDeadEndRoomTypes();
        setHallwayRoomTypes();

        createCells();
        setCellRoomTypes();
    }

    private void createHallways() {
        int startX = random.nextInt(3) + 1;
        int startY = random.nextInt(3) + 1;

        for (int i = 0; i < 5; i++) {
            map.setRoomAt(new Room(), startX, i, true);
            map.setRoomAt(new Room(), i, startY, true);
        }
    }

    private void connectHallways() {
        for (RoomAndCoordinates rac : map.getRooms()) {
            Room r = rac.getRoom();
            Coordinates c = rac.getCoords();

            for (Direction d : Direction.values()) {
                RoomAndCoordinates neighbour = map.getRoomAt(c.getNeighbour(d));
                if (neighbour != null) {
                    r.setExit(neighbour.getRoom(), d);
                }
            }
        }
    }

    private void setDeadEndRoomTypes() {
        List<RoomAndCoordinates> deadEnds = map.getDeadEnds();
        Collections.shuffle(deadEnds);
        List<RoomType> deadEndTypes = new ArrayList<>();
        deadEndTypes.add(templates.getByCategory("entrance"));
        deadEndTypes.add(templates.getByCategory("warden"));
        deadEndTypes.addAll(templates.getNumberByCategory("corridor", 2));
        for (int i = 0; i < deadEnds.size() && i < deadEndTypes.size(); i++) {
            Room currentRoom = deadEnds.get(i).getRoom();
            currentRoom.setType(deadEndTypes.get(i));
            if (i == 0) {
                entrance = currentRoom;
                entrance.visit();
            } else if (i == 1) {
                currentRoom.getInventory().addItem(new Item("key", true, "a simple key", "a small key, unadorned and crudely made."));
            }
        }
    }

    private void setHallwayRoomTypes() {
        for (RoomAndCoordinates rac : map.getRooms()) {
            Room r = rac.getRoom();
            if (!r.hasType()) {
                r.setType(templates.getByCategory("corridor"));
            }
        }
    }

    private void createCells() {
        List<RoomAndCoordinates> roomList = new ArrayList<>(map.getRooms());
        for (RoomAndCoordinates rac : roomList) {
            Room thisRoom = rac.getRoom();
            Coordinates coords = rac.getCoords();

            if (thisRoom.getTypeCategories().contains("corridor")) {
                for (int i = 0; i < Direction.values().length; i++) {
                    Direction dir = Direction.valueOf(i);
                    Coordinates neighbour = coords.getNeighbour(dir);
                    if (thisRoom.getExit(dir) == null && map.getRoomAt(neighbour) == null) {
                        Room newRoom = new Room();
                        map.setRoomAt(newRoom, neighbour, false);
                        connectRooms(thisRoom, dir, newRoom);
                    }
                }
            }
        }
    }

    private void setCellRoomTypes() {
        for (RoomAndCoordinates rac : map.getRooms()) {
            Room r = rac.getRoom();
            if (!r.hasType()) {
                r.setType(templates.getByCategory("cell"));
                if (random.nextBoolean()) {
                    setExitLocked(r, "key");
                }
            }
        }
    }
}
