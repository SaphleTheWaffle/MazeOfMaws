package mazeofmaws.commands;

import game.Player;

public class Get implements Command {

    private boolean completed = false;

    @Override
    public String run(Player player, String args) {
        if (player.pickupItem(args)) {
            completed = true;
            return "Picked up the " + args.toLowerCase();
        }
        return "Couldn't pick up \"" + args.toLowerCase() + "\".";
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
