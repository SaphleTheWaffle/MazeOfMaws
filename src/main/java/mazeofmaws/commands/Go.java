package mazeofmaws.commands;

import game.Player;
import game.entities.Room;
import game.world.Direction;

public class Go implements Command {

    private boolean completed = false;

    @Override
    public String run(Player player, String args) {
        Direction direction = Direction.from(args.trim());
        Room oldRoom = player.getCharacter().getLocation();
        String res = player.move(direction);
        completed = !oldRoom.equals(player.getCharacter().getLocation());
        return res;
    }

    @Override
    public String runNPCs(Player player) {
        completed = false;
        return player.enemyTurn();
    }

    @Override
    public boolean actionPerformed() {
        return completed;
    }
}
