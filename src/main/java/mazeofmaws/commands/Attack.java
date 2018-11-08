package mazeofmaws.commands;

import game.Player;

public class Attack implements Command {
    private boolean completed = false;

    @Override
    public String run(Player player, String args) {
        String res = player.attack(args.trim().toLowerCase());
        completed = res.endsWith("!");
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
