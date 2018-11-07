package mazeofmaws.commands;

import game.Player;
import game.world.Direction;

public class Go implements Command {
    @Override
    public String run(Player player, String args) {
        Direction direction = Direction.from(args.trim());
        return player.move(direction);
    }

    @Override
    public String runNPCs(Player player) {
        return player.enemyTurn();
    }
}
