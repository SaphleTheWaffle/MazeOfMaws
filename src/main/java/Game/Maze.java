package Game;

import Game.Entities.Room;

public class Maze {
    private Room[][] grid;

    void generateMaze() {
        grid = new Room[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Room();
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Room[] exits = new Room[5];
                exits[0] = i == 0 ? null : grid[i-1][j];
                exits[1] = j == 0 ? null : grid[i][j-1];
                exits[2] = j >= grid.length - 1 ? null : grid[i][j+1];
                exits[3] = i >= grid[0].length - 1 ? null : grid[i+1][j];
                exits[4] = null;
                grid[i][j].setExits(exits);
            }
        }
    }

    public Room getRoom(int x, int y) {
        return grid[y][x];
    }
}
