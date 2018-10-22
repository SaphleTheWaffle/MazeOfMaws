package Game;

import Game.Entities.Room;

public class Maze {
    private Room[][] grid;

    public void generateMaze() {
        grid = new Room[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Room();
            }
        }
    }
}
