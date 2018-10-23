package MazeOfMaws.Commands;

import Game.Player;

public class Go implements Command {
    @Override
    public String run(Player player, String args) {
        int direction;
        switch(args.toLowerCase()) {
            case "north":
                direction = 0;
                break;
            case "west":
                direction = 1;
                break;
            case "east":
                direction = 2;
                break;
            case "south":
                direction = 3;
                break;
            default:
                direction = 0;
        }
        if (player.move(direction)) {
            return "You move through the " + args.toLowerCase() + "ern exit.\n" + new Look().run(player, null);
        }
        return "There is no exit in that direction: \'" + args.toLowerCase() + "\'";
    }
}
