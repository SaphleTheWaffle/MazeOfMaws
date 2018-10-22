package MazeOfMaws.Commands;

import Game.Player;

public class Welcome implements Command {
    @Override
    public String run(Player player, String args) {
        return "Hi, " + args + "!\n" + "Welcome to the Maze of Maws! Currently a work in progress.";
    }
}
