package mazeofmaws.commands;

import game.Player;
import game.world.Direction;

public class Go implements Command {
    @Override
    public String run(Player player, String args) {
        Direction direction = Direction.from(args.trim());
        if (direction != null && player.move(direction)) {
            return "Moving through the " + args.toLowerCase() + "ern exit, you find yourself in " + player.describeRoom();
        }
        return "There is no exit in that direction (\"" + args.toLowerCase() + "\").";
    }
}
