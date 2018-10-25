package Game.Entities;

import Game.Direction;
import Game.Entities.Creatures.Creature;
import Game.Entities.Items.Item;
import Game.Entities.Obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Room implements Describable {

    private List<Item> items;
    private List<Creature> creatures;
    private List<Obstacle> obstacles;
    private Room[] exits;

    public Room() {
        items = new ArrayList<>();
        creatures = new ArrayList<>();
        obstacles = new ArrayList<>();
        exits = new Room[5];
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

    public Room getExit(int direction) {
        return exits[direction];
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
