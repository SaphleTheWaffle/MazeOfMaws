package Game.World;

import Game.Entities.Room;
import Game.World.Generation.MazeBuilder;

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
