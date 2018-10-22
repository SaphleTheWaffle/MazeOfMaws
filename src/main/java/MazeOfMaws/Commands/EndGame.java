package MazeOfMaws.Commands;

import Game.Player;

public class EndGame implements Command {
    @Override
    public String run(Player player, String args) {
        if (player.endGame()) {
            return "Game ended.";
        }
        return "You do not currently have a game running.";
    }
}
