package mazeofmaws.commands;

import game.Player;

public class Look implements Command {
    @Override
    public String run(Player player, String args) {
        if (args == null || args.equals("")) {
            return describeRoom(player);
        }
        return describeItem(player, args.trim().toLowerCase());
    }

    private String describeRoom(Player player) {
        return "You are in " + player.describeRoom();
    }

    private String describeItem(Player player, String args) {
        String res = player.describeItem(args);
        return res.equals("") ? "Item not found!" : res;
    }
}
