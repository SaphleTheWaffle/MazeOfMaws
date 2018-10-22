package MazeOfMaws.Commands;

import Game.Player;

public interface Command {
    String run(Player player, String args);
}
