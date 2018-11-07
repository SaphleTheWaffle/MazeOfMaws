package mazeofmaws.commands;

import game.Player;

public class Look implements Command {
    @Override
    public String run(Player player, String args) {
        if (args == null || args.equals("")) {
            return describeRoom(player);
        }
        return describeEntity(player, args.trim().toLowerCase());
    }

    @Override
    public String runNPCs(Player player) {
        return null;
    }

    @Override
    public boolean actionPerformed() {
        return false;
    }

    private String describeRoom(Player player) {
        return "You are in " + player.describeRoom();
    }

    private String describeEntity(Player player, String args) {
        String itemDesc = describeItem(player, args);
        String creatureDesc = describeCreature(player, args);

        if (!itemDesc.equals("") && !creatureDesc.equals("")) {
            return "There is both an item and a creature matching that name here. Please be more specific.";
        }
        if (!itemDesc.equals("")) {
            return itemDesc;
        }
        if (!creatureDesc.equals("")) {
            return creatureDesc;
        }
        return "There are no creatures or items matching that name here!";
    }

    private String describeItem(Player player, String args) {
        return player.describeItem(args);
    }

    private String describeCreature(Player player, String args) {
        return player.describeCreature(args);
    }
}
