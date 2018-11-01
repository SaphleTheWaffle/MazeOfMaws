package game.entities.templates;

import game.world.Direction;

public class Encounter {
    private String id;
    private boolean blocking;
    private Direction exit;
    private boolean hidden;

    public Encounter(String id, boolean blocking, Direction exit, boolean hidden) {
        this.id = id;
        this.blocking = blocking;
        this.exit = exit;
        this.hidden = hidden;
    }

    public String getId() {
        return id;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public Direction getExit() {
        return exit;
    }

    public boolean isHidden() {
        return hidden;
    }
}
