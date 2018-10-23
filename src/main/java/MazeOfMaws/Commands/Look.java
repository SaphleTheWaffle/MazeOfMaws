package MazeOfMaws.Commands;

import Game.Entities.Room;
import Game.Player;

public class Look implements Command {
    @Override
    public String run(Player player, String args) {
        Room location = player.getCharacter().getLocation();
        return location.describe() + "\nExits: " + location.getExits();
    }
}
