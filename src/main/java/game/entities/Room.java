package game.entities;

import game.entities.creatures.Creature;
import game.entities.items.Inventory;
import game.entities.items.Item;
import game.entities.obstacles.Obstacle;
import game.entities.templates.Encounter;
import game.entities.templates.RoomType;
import game.world.Direction;
import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Room {

    private Inventory inventory;
    private Encounter encounter;
    private Room[] exits;
    private List<Obstacle> obstacles;
    private List<Creature> creatures;
    private RoomType type;
    private boolean visited;
    private String id;
    private final static char[] SYMBOLS = {'□', 'o', 'o', '╔', 'o', '╗', '═', '╦', 'o', '║', '╚', '╠', '╝', '╣', '╩', '╬'};

    public Room() {
        inventory = new Inventory();
        exits = new Room[5];
        id = UUID.randomUUID().toString();
        visited = false;
        obstacles = new ArrayList<>();
        creatures = new ArrayList<>();
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
        return obstacles.stream()
                .filter(e -> e.getDirection().equals(dir))
                .anyMatch(Obstacle::isActive);
    }

    public Obstacle getObstacle(Direction dir) {
        return obstacles.stream()
                .filter(e -> e.getDirection().equals(dir))
                .findFirst()
                .orElse(null);
    }

    public boolean unlockDoor(Direction dir, Item item) {
        if (getExit(dir) == null) {
            return false;
        }
        Obstacle obstacleHere = getObstacle(dir);
        Obstacle obstacleNeighbour = getExit(dir).getObstacle(dir.getOpposite());

        return obstacleHere != null && obstacleNeighbour != null &&
                obstacleHere.checkItem(item) && obstacleNeighbour.checkItem(item) &&
                obstacleHere.deactivate() && obstacleNeighbour.deactivate();

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

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
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

    public boolean isTypeless() {
        return type == null;
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
                formatExitsString() +
                ((creatures.size() > 0) ? (StringUtils.SEPARATOR + formatNPCsString()) : "");
    }

    private String getShortDescription() {
        return StringUtils.bold(type.getName()) + StringUtils.SEPARATOR +
                ((inventory.size() > 0) ? (formatItemsString() + StringUtils.SEPARATOR) : "") +
                formatExitsString() +
                ((creatures.size() > 0) ? (StringUtils.SEPARATOR + formatNPCsString()) : "");
    }

    private String formatItemsString() {
        return "In this room you see " + listItems();
    }

    private String listItems() {
        List<String> things = inventory.getItemNames().stream()
                .map(StringUtils::underline)
                .collect(Collectors.toList());
        return StringUtils.listify(things);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private String formatExitsString() {
        if (isCorridor()) {
            return formatCorridorExitStrings();
        }
        if (getTypeCategories().contains("corridor")) {
            StringBuilder sb = new StringBuilder();

        }
        if (numberOfExits() == 1) {
            return "There is an exit to the " + listExits();
        }
        return "Exits are to the " + listExits();
    }

    private boolean isCorridor() {
        return type != null && getTypeCategories() != null && getTypeCategories().contains("corridor");
    }

    private String formatCorridorExitStrings() {
        List<String> corridors = new ArrayList<>();
        List<String> rooms = new ArrayList<>();
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != null && exits[i].isCorridor()) {
                corridors.add(getExitName(Direction.valueOf(i)));
            } else if (exits[i] != null && !exits[i].isCorridor()) {
                rooms.add(getExitName(Direction.valueOf(i)));
            }
        }

        String corridorString = "The corridor continues to the " + StringUtils.listify(corridors) + ".";
        String roomString = (rooms.size() > 1 ? "There are rooms to the " : "There is a room to the ") + StringUtils.listify(rooms) + ".";
        List<String> combined = rooms.size() > 0 ? Arrays.asList(corridorString, roomString) : Collections.singletonList(corridorString);
        return String.join("\n", combined);
    }

    String listExits() {
        List<String> dirs = new ArrayList<>();
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != null) {
                dirs.add(getExitName(Direction.valueOf(i)));
            }
        }
        return StringUtils.listify(dirs);
    }

    private String getExitName(Direction direction) {
        if (isExitLocked(direction)) {
            return StringUtils.italics(direction.name) + " (locked)";
        }
        return StringUtils.italics(direction.name);
    }
    private String formatNPCsString() {
        return listNPCs() +
                (creatures.size() > 1 ? " are " : " is ") +
                "here.";
    }

    private String listNPCs() {
        List<String> creatureNames = new ArrayList<>();
        for (Creature c : creatures) {
            creatureNames.add(c.getName());
        }
        return StringUtils.listify(creatureNames);
    }

    public List<String> getTypeCategories() {
        return type.getCategories();
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public Creature getCreatureByName(String creatureName) {
        return creatures.stream()
                .filter(e -> e.getName().toLowerCase().contains(creatureName))
                .findFirst()
                .orElse(null);
    }

    public boolean removeCreature(Creature creature) {
        return creatures.remove(creature);
    }

    public String creatureActions() {
        StringBuilder sb = new StringBuilder();
        for (Creature creature : creatures) {
            sb.append(StringUtils.SEPARATOR);
            sb.append(creature.act());
        }

        return sb.toString();
    }

    public String describeCreature(String creatureName) {
        for (Creature c : creatures) {
            if (c.getName().toLowerCase().equals(creatureName)) {
                return c.describe();
            }
        }
        return "";
    }
}
