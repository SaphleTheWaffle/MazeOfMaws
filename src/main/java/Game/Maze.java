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
                exits[0] = getRoom(i-1, j);
                exits[1] = getRoom(i, j-1);
                exits[2] = getRoom(i, j+1);
                exits[3] = getRoom(i+1, j);
                exits[4] = null;
                grid[i][j].setExits(exits);
            }
        }
    }

    public Room getRoom(int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return null;
        }
        return grid[y][x];
    }
}
