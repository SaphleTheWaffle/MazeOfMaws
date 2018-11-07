package game.entities;

import game.entities.creatures.Creature;
import game.entities.items.Inventory;
import game.entities.items.Item;
import game.entities.obstacles.Obstacle;
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
        if (numberOfExits() == 1) {
            return "There is an exit to the " + listExits();
        }
        return "Exits are to the " + listExits();
    }

    String listExits() {
        List<String> dirs = new ArrayList<>();
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != null) {
                if (isExitLocked(Direction.valueOf(i))) {
                    dirs.add(StringUtils.italics(Direction.valueOf(i).name) + " (locked)");
                } else {
                    dirs.add(StringUtils.italics(Direction.valueOf(i).name));
                }
            }
        }
        return StringUtils.listify(dirs);
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

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public String creatureActions() {
        StringBuilder sb = new StringBuilder();
        for (Creature creature : creatures) {
            sb.append(StringUtils.SEPARATOR);
            sb.append(creature.act());
        }

        return sb.toString();
    }
}
