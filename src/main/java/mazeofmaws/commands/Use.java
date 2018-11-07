package mazeofmaws.commands;

import game.Player;

public class Use implements Command {
    @Override
    public String run(Player player, String args) {
        int indexOfSplit = args.indexOf("on");
        String itemName = indexOfSplit > 0 ? args.substring(0, indexOfSplit) : args;
        String target = indexOfSplit > 0 ? args.substring(indexOfSplit + 2) : "";
        return player.useItem(itemName.trim().toLowerCase(), target.trim().toLowerCase());
    }

    @Override
    public String runNPCs(Player player) {
        return player.enemyTurn();
    }
}
