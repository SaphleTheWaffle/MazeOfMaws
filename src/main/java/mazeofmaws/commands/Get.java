package mazeofmaws.commands;

import game.Player;

public class Get implements Command {
    @Override
    public String run(Player player, String args) {
        if (player.pickupItem(args)) {
            return "Picked up the " + args.toLowerCase();
        }
        return "Couldn't pick up \"" + args.toLowerCase() + "\".";
    }
}
