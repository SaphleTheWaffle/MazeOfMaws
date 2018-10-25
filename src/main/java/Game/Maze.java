package Game;

import Game.Entities.Room;

class Maze {
    private Room[][] grid;
    private Room entrance;
    private MazeBuilder mb;

    Maze() {
        mb = new MazeBuilder();
    }

    void generateMaze() {
        entrance = mb.build(5, 5);
    }

    Room getEntrance() {
        return entrance;
    }

    Room getRoom(int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return null;
        }
        return grid[x][y];
    }
}
