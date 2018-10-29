package game.entities.items;

public abstract class Item {
    private boolean pickupable;

    public final boolean isPickupable() {
        return pickupable;
    }

    public abstract String getName();

    public abstract String describe();
}
