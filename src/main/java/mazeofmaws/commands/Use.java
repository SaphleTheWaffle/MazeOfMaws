package mazeofmaws.commands;

import game.Player;

public class Use implements Command {
    @Override
    public String run(Player player, String args) {
        return player.useItem(args.trim().toLowerCase());
    }
}
