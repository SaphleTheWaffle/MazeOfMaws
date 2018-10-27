package mazeofmaws.commands;

import game.Player;
import game.world.Direction;

public class Go implements Command {
    @Override
    public String run(Player player, String args) {
        Direction direction = Direction.valueOf(args);
        if (player.move(direction)) {
            return "You move through the " + args.toLowerCase() + "ern exit.\n" + new Look().run(player, null);
        }
        return "There is no exit in that direction: \'" + args.toLowerCase() + "\' (" + direction + ")";
    }
}
