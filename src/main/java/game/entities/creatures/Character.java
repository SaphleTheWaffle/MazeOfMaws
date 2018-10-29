package game.entities.creatures;

import game.entities.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Character extends Creature {

    private List<Item> inventory = new ArrayList<>();

    public Character(String name, int maxHealth, int damage) {
        super(name, maxHealth, damage);
    }

    public String enterRoom() {
        boolean visited = location.isVisited();
        location.visit();
        return location.describe(!visited);
    }

    public String describeRoom() {
        return location.describe(true);
    }

    public String describeItem(String itemName) {
        for (Item i : inventory) {
            if (i.getName().equals(itemName)) {
                return i.describe();
            }
        }
        return "";
    }
}
