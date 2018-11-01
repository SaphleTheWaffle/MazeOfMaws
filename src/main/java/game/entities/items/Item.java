package game.entities.items;

import utils.StringUtils;

public class Item {
    private String id;
    private boolean pickupable;
    private String name;
    private String description;

    public Item(String id, boolean pickupable, String name, String description) {
        this.id = id;
        this.pickupable = pickupable;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public final boolean isPickupable() {
        return pickupable;
    }

    public String getName() {
        return name;
    }

    public String describe() {
        return StringUtils.bold(name) + StringUtils.SEPARATOR + description;
    }
}
