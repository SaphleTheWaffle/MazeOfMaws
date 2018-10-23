package Game.Entities;

import Game.Entities.Creatures.Creature;
import Game.Entities.Items.Item;
import Game.Entities.Obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Room implements Describable {

    List<Item> items;
    List<Creature> creatures;
    List<Obstacle> obstacles;
    Room[] exits;

    public Room() {
        items = new ArrayList<>();
        creatures = new ArrayList<>();
        obstacles = new ArrayList<>();
        exits = new Room[5];
    }

    public void setExits(Room[] exits) {
        System.arraycopy(exits, 0, this.exits, 0, exits.length);
    }

    @Override
    public String describe() {
        StringBuilder desc = new StringBuilder();
        desc.append("A plain stone room with no distinguishing features. (WIP)\n");
        return desc.toString();
    }
}
