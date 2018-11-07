package mazeofmaws.commands;

import game.Player;

public interface Command {
    String run(Player player, String args);
    String runNPCs(Player player);
    boolean actionPerformed();
}
