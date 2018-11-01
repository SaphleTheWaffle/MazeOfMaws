package game.entities;

import game.entities.creatures.Creature;
import game.entities.items.Item;
import game.entities.obstacles.Obstacle;
import game.entities.templates.Encounter;
import game.entities.templates.RoomType;
import game.world.Direction;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {

    private List<Item> items;
    private List<Creature> creatures;
    private List<Obstacle> obstacles;
    private Encounter encounter;
    private Room[] exits;
    private RoomType type;
    private boolean visited;
    private String id;
    private final static char[] SYMBOLS = {'□', 'o', 'o', '╔', 'o', '╗', '═', '╦', 'o', '║', '╚', '╠', '╝', '╣', '╩', '╬'};


    public Room() {
        items = new ArrayList<>();
        creatures = new ArrayList<>();
        obstacles = new ArrayList<>();
        exits = new Room[5];
        id = UUID.randomUUID().toString();
        visited = false;
    }

    public Room getExit(Direction direction) {
        return exits[direction.index];
    }

    public void setExit(Room room, Direction dir) {
        exits[dir.index] = room;
    }

    public boolean isExitLocked(Direction dir) {
        return encounter != null && encounter.isBlocking() && encounter.getExit().equals(dir);
    }

    public String describeItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equals(itemName)) {
                return i.describe();
            }
        }
        return "";
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
                type.getDescription() +
                ((items.size() > 0) ? (formatItemsString() + StringUtils.SEPARATOR) : StringUtils.SEPARATOR) +
                formatExitsString();
    }

    private String getShortDescription() {
        return StringUtils.bold(type.getName()) +
                ((items.size() > 0) ? (formatItemsString() + StringUtils.SEPARATOR) : StringUtils.SEPARATOR) +
                formatExitsString();
    }

    private String formatItemsString() {
        return "In this room you see " + listItems();
    }

    private String listItems() {
        List<String> things = new ArrayList<>();
        for (Item thing : items) {
            things.add(StringUtils.underline(thing.getName()));
        }
        return String.join(", ", things);
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
        return String.join(", ", dirs);
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
}
