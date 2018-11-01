package game.entities.creatures;

import game.entities.items.Inventory;
import game.entities.items.Item;

public class Character extends Creature {

    private Inventory inventory;

    public Character(String name, int maxHealth, int damage) {
        super(name, maxHealth, damage);
        inventory = new Inventory();
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
        Item i = inventory.getItemByName(itemName);
        return i != null ? i.describe() : "";
    }

    public boolean pickupItem(String itemName) {
        Inventory roomItems = location.getInventory();
        Item item = roomItems.getItemByName(itemName);
        if (item != null && item.isPickupable()) {
            return inventory.addItem(roomItems.removeItem(item));
        }
        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
