package game.world.generation;

import game.entities.Room;
import game.entities.templates.Templates;

import java.util.Random;

public abstract class MazeBuilder {

    Random random;
    RoomMap map;
    Room entrance;
    Templates templates;

    MazeBuilder() {
        this.random = new Random();
        this.map = new RoomMap();
        this.templates = Templates.getInstance();
    }

    public abstract void build();

    public RoomMap getMap() {
        return map;
    }

    public Room getEntrance() {
        return entrance;
    }
}
