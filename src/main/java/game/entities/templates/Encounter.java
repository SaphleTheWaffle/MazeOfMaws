package game.entities.templates;

import game.world.Direction;

public class Encounter {
    private String id;
    private Direction exit;
    private boolean active;

    public Encounter(String id, Direction exit, boolean active) {
        this.id = id;
        this.exit = exit;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public Direction getExit() {
        return exit;
    }

    public boolean isActive() {
        return active;
    }
}
