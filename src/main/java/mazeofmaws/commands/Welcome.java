package mazeofmaws.commands;

import game.Player;

public class Welcome implements Command {
    @Override
    public String run(Player player, String args) {
        return "Hi, " + args + "!\n" + "Welcome to the Maze of Maws! Currently a work in progress.";
    }

    @Override
    public String runNPCs(Player player) {
        return null;
    }

    @Override
    public boolean actionPerformed() {
        return false;
    }
}
