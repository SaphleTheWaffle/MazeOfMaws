package mazeofmaws.commands;

import game.Player;

public class SendStatus implements Command {
    @Override
    public String run(Player player, String args) {
        switch (player.getState()) {
            case NOT_STARTED:
                return "You do not currently have a game running.";
            case STARTED:
                return "You are currently in game.";
            case IN_COMBAT:
                return "You are currently in combat.";
            default:
                return "This is an error. If you don't mind, please let the developer know.";
        }
    }
}
