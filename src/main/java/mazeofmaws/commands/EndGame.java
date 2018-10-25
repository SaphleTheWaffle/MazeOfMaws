package mazeofmaws.commands;

import game.Player;

public class EndGame implements Command {
    @Override
    public String run(Player player, String args) {
        if (player.endGame()) {
            return "game ended.";
        }
        return "You do not currently have a game running.";
    }
}
