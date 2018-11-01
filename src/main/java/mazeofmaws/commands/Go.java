package mazeofmaws.commands;

import game.Player;
import game.world.Direction;

public class Go implements Command {
    @Override
    public String run(Player player, String args) {
        Direction direction = Direction.from(args.trim());
        if (direction != null) {
            if (player.isExitLocked(direction)) {
                return "That door is locked!";
            }
            if (player.move(direction)) {
                return "Moving through the " + args.toLowerCase() + "ern exit, you find yourself in " + player.enterRoom();
            }
        }
        return "There is no exit in that direction (\"" + args.toLowerCase() + "\").";
    }
}
