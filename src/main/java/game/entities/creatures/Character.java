package game.entities.creatures;

import game.entities.Room;
import game.entities.items.Inventory;
import game.entities.items.Item;

import java.util.Random;

public class Character {

    private Inventory inventory;
    private Room location;
    private Random random;
    private int maxHealth;
    private int currentHealth;

    public Character() {
        inventory = new Inventory();
        random = new Random();
        currentHealth = 10;
        maxHealth = 10;
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

    public String attack(String enemyName) {
        Creature enemy = location.getCreatureByName(enemyName);
        if (enemy == null) {
            return "There is nothing you can attack here by that name.";
        }
        int damage = random.nextInt(3) + 3;

        enemy.changeHealth(0 - damage);

        return "You attack the " + enemy.getName() + ", dealing " + damage + " damage!";
    }

    public int getHealth() {
        return currentHealth;
    }

    void changeHealth(int delta) {
        currentHealth += delta;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else if (currentHealth < 0) {
            currentHealth = 0;
        }
    }
}
