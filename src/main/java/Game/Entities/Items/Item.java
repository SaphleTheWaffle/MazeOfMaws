package Game.Entities.Items;

import Game.Entities.Describable;

public abstract class Item implements Describable {
    private boolean pickupable;

    public final boolean isPickupable() {
        return pickupable;
    }
}
