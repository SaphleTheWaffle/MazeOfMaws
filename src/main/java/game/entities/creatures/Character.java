package game.entities.creatures;

import game.entities.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Character extends Creature {

    List<Item> inventory = new ArrayList<>();

    public Character(String name, int maxHealth, int damage) {
        super(name, maxHealth, damage);
    }

    @Override
    public String describe() {
        return null;
    }

    @Override
    public String getName() {
        return "player";
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
