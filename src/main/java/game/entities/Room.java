package game.entities;

import game.entities.creatures.Creature;
import game.entities.items.Item;
import game.entities.obstacles.Obstacle;
import game.world.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room implements Describable {

    private List<Item> items;
    private List<Creature> creatures;
    private List<Obstacle> obstacles;
    private Room[] exits;
    private String id;

    public Room() {
        items = new ArrayList<>();
        creatures = new ArrayList<>();
        obstacles = new ArrayList<>();
        exits = new Room[5];
        id = UUID.randomUUID().toString();
    }

    public void setExits(Room[] exits) {
        System.arraycopy(exits, 0, this.exits, 0, exits.length);
    }

    public String getExits() {
        List<String> dirs = new ArrayList<>();
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != null) {
                dirs.add(Direction.valueOf(i).name);
            }
        }
        return String.join(", ", dirs);
    }

    public Room getExit(Direction direction) {
        return exits[direction.index];
    }

    public void setExit(Room room, Direction dir) {
        exits[dir.index] = room;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public String describeItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equals(itemName)) {
                return i.describe();
            }
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            return ((Room) o).id.equals(this.id);
        }
        return false;
    }

    @Override
    public String describe() {
        StringBuilder desc = new StringBuilder();
        desc.append("A plain stone room with no distinguishing features. (WIP)\n");
        return desc.toString();
    }

    @Override
    public String getName() {
        return "a room";
    }
}
