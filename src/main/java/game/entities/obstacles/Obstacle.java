package game.entities.obstacles;

import game.entities.items.Item;
import game.world.Direction;

public class Obstacle {
    private Direction blockingDir;
    private String keyId;
    private boolean active;

    public Obstacle(Direction blockingDir, String keyId, boolean active) {
        this.blockingDir = blockingDir;
        this.keyId = keyId;
        this.active = active;
    }

    public Direction getDirection() {
        return blockingDir;
    }

    public boolean isActive() {
        return active;
    }

    public String getBlockingMessage() {
        return "You try your best to pull the large door, but to no avail. Pushing on it causes it to not budge so much as an inch. "
                + "It looks like you will need to find the key to be able to open this door.";
    }

    public String getUnblockedMessage() {
        return "You slide the key into the keyhole. It takes all your strength to turn it around in the lock, but eventually you are " +
                "rewarded with a loud 'click!'. The door is now unlocked.";
    }

    public boolean checkItem(Item item) {
        return active && item != null && item.getId().equals(keyId);
    }

    public boolean deactivate() {
        if (active) {
            active = false;
            return true;
        }
        return false;
    }
}
