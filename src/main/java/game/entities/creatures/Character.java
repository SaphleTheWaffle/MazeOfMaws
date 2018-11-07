package game.entities.creatures;

import game.entities.Room;
import game.entities.items.Inventory;
import game.entities.items.Item;

public class Character {

    private Inventory inventory;
    private Room location;

    public Character() {
        inventory = new Inventory();
    }

    public Room getLocation() {
        return location;
    }

    public void move(Room location) {
        this.location = location;
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
        return item != null && item.isPickupable() && inventory.addItem(roomItems.removeItem(item));
    }

    public Inventory getInventory() {
        return inventory;
    }
}
