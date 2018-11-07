package mazeofmaws.commands;

import game.Player;

public class Use implements Command {

    private boolean completed = false;

    @Override
    public String run(Player player, String args) {
        int indexOfSplit = args.indexOf("on");
        String itemName = indexOfSplit > 0 ? args.substring(0, indexOfSplit) : args;
        String target = indexOfSplit > 0 ? args.substring(indexOfSplit + 2) : "";
        String res = player.useItem(itemName.trim().toLowerCase(), target.trim().toLowerCase());
        completed = !res.endsWith("?") && !res.endsWith("!");
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
