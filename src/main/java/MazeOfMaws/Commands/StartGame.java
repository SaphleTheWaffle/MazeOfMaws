package MazeOfMaws.Commands;

import Game.Player;

public class StartGame implements Command {
    @Override
    public String run(Player player, String args) {
        if (player.startGame()) {
            return "Game started. Good luck!";
        }
        return "You already have a game running.";
    }
}
