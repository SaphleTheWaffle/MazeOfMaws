package MazeOfMaws.Commands;

import Game.Player;

public class Look implements Command {
    @Override
    public String run(Player player, String args) {
        return player.getCharacter().getLocation().describe();
    }
}
