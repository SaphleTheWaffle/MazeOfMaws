package mazeofmaws.commands;

import game.Player;
import game.entities.Room;
import game.world.generation.Coordinates;
import game.world.generation.RoomAndCoordinates;

import java.util.ArrayList;

public class Map implements Command {
    @Override
    public String run(Player player, String args) {
        Room[][] grid = buildGrid(player.getMap().getRooms());
        return print(grid, player);
    }

    private Room[][] buildGrid(ArrayList<RoomAndCoordinates> list) {
        int maxX = 0, maxY = 0;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

        for(RoomAndCoordinates rac : list) {
            Coordinates c = rac.getCoords();
            if (c.getX() > maxX) {
                maxX = c.getX();
            }
            if (c.getY() > maxY) {
                maxY = c.getY();
            }
            if (c.getX() < minX) {
                minX = c.getX();
            }
            if (c.getY() < minY) {
                minY = c.getY();
            }
        }
        maxX -= minX;
        maxY -= minY;
        Room[][] grid = new Room[maxY+1][maxX+1];

        for(RoomAndCoordinates rac : list) {
            Coordinates c = rac.getCoords();
            grid[c.getY() - minY][c.getX() - minX] = rac.getRoom();
        }
        return grid;
    }

    private String print(Room[][] grid, Player player) {
        StringBuilder sb = new StringBuilder("```\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null && grid[i][j].equals(player.getCharacter().getLocation())) {
                    sb.append("X");
                } else if (grid[i][j] != null && grid[i][j].isVisited()) {
                    sb.append(grid[i][j].getSymbol());
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.append("```");
        return sb.toString();
    }
}
