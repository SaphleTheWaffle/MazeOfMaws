package game.entities.items;

import game.entities.Describable;

public abstract class Item implements Describable {
    private boolean pickupable;

    public final boolean isPickupable() {
        return pickupable;
    }
}
