package game.entities;

import game.entities.items.Inventory;
import game.entities.templates.Encounter;
import game.entities.templates.RoomType;
import game.world.Direction;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Room {

    private Inventory inventory;
    private Encounter encounter;
    private Room[] exits;
    private RoomType type;
    private boolean visited;
    private String id;
    private final static char[] SYMBOLS = {'□', 'o', 'o', '╔', 'o', '╗', '═', '╦', 'o', '║', '╚', '╠', '╝', '╣', '╩', '╬'};

    public Room() {
        inventory = new Inventory();
        exits = new Room[5];
        id = UUID.randomUUID().toString();
        visited = false;
    }

    public Room getExit(Direction direction) {
        if (direction != null) {
            return exits[direction.index];
        }
        return null;
    }

    public void setExit(Room room, Direction dir) {
        exits[dir.index] = room;
    }

    public boolean isExitLocked(Direction dir) {
        return encounter != null && encounter.isBlocking() && encounter.getExit().equals(dir);
    }

    public int numberOfExits() {
        int num = 0;
        for (Room r : exits) {
            if (r != null) {
                num++;
            }
        }
        return num;
    }

    public char getSymbol() {
        int north = bitwiseIsExit(Direction.NORTH.index) << 3;
        int west = bitwiseIsExit(Direction.WEST.index) << 2;
        int east = bitwiseIsExit(Direction.EAST.index) << 1;
        int south = bitwiseIsExit(Direction.SOUTH.index);
        return SYMBOLS[north | west | east | south];
    }

    private int bitwiseIsExit(int index) {
        return (exits[index] != null) ? 1 : 0;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean hasType() {
        return type != null;
    }

    public void visit() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean equals(Object o) {
        return this.getClass() == o.getClass() && ((Room) o).id.equals(this.id);
    }

    public String describe(boolean detailed) {
        return (detailed ? getDetailedDescription() : getShortDescription());
    }

    private String getDetailedDescription() {
        return StringUtils.bold(type.getName()) + StringUtils.SEPARATOR +
                type.getDescription() + StringUtils.SEPARATOR +
                ((inventory.size() > 0) ? (formatItemsString() + StringUtils.SEPARATOR) : "") +
                formatExitsString();
    }

    private String getShortDescription() {
        return StringUtils.bold(type.getName()) + StringUtils.SEPARATOR +
                ((inventory.size() > 0) ? (formatItemsString() + StringUtils.SEPARATOR) : "") +
                formatExitsString();
    }

    private String formatItemsString() {
        return "In this room you see " + listItems();
    }

    private String listItems() {
        List<String> things = inventory.getItemNames().stream()
                .map(StringUtils::underline)
                .collect(Collectors.toList());
        return String.join(", ", things);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private String formatExitsString() {
        return "Exits: " + listExits();
    }

    String listExits() {
        List<String> dirs = new ArrayList<>();
        Direction blockedExit = lockedDoor();
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != null) {
                if (blockedExit != null && i == blockedExit.index) {
                    dirs.add(StringUtils.italics(Direction.valueOf(i).name) + " (locked)");
                } else {
                    dirs.add(StringUtils.italics(Direction.valueOf(i).name));
                }
            }
        }
        return StringUtils.listify(dirs);
    }

    private Direction lockedDoor() {
        if (encounter != null && encounter.isBlocking()) {
            return encounter.getExit();
        }
        return null;
    }

    public List<String> getTypeCategories() {
        return type.getCategories();
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Encounter getEncounter() {
        return encounter;
    }
}
