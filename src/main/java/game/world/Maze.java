package game.world;

import game.entities.Room;
import game.world.generation.MazeBuilder;

public class Maze {
    private Room entrance;
    private MazeBuilder mb;

    public Maze() {
        mb = new MazeBuilder();
    }

    public void generateMaze() {
        entrance = mb.build();
    }

    public Room getEntrance() {
        return entrance;
    }
}
