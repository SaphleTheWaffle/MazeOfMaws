package game.entities.templates;

import game.entities.items.Item;
import game.world.Direction;

public class Encounter {
    private String id;
    private boolean blocking;
    private Direction exit;
    private boolean active;
    private String keyId;

    public Encounter(String id, boolean blocking, Direction exit, boolean active) {
        this.id = id;
        this.blocking = blocking;
        this.exit = exit;
        this.active = active;
        if (blocking) {
            keyId = "key";
        }
    }

    public String getId() {
        return id;
    }

    public boolean isBlocking() {
        return blocking && active;
    }

    public Direction getExit() {
        return exit;
    }

    public boolean isActive() {
        return active;
    }

    public String getBlockingMessage() {
        return "You try your best to pull the large door, but to no avail. Pushing on it causes it to not budge so much as an inch. "
                + "It looks like you will need to find the key to be able to open this door.";
    }

    public String getRemoveBlockingMessage() {
        return "You slide the key into the keyhole. It takes all your strength to turn it around in the lock, but eventually you are " +
                "rewarded with a loud 'click!'. The door is now unlocked.";
    }

    public boolean checkUseItem(Item item) {
        if (isBlocking() && item != null && item.getId().equals(keyId)) {
            active = false;
            return true;
        }
        return false;
    }
}