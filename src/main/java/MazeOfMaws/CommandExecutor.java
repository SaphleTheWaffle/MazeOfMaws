package MazeOfMaws;

import Game.GameState;
import Game.Player;
import MazeOfMaws.Commands.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.HashMap;

class CommandExecutor {

    private HashMap<String, Command> menuMap;
    private HashMap<String, Command> gameMap;

    CommandExecutor() {
        initCommands();
    }

    private void initCommands() {
        menuMap = new HashMap<>();
        menuMap.put("welcome", new Welcome());
        menuMap.put("start", new StartGame());
        menuMap.put("status", new SendStatus());

        gameMap.put("status", new SendStatus());
        gameMap.put("end", new EndGame());
        gameMap.put("look", new Look());
    }

    String sendWelcomeMessage(MessageReceivedEvent event) {
        return executeCommand(null, "welcome", event.getAuthor().getName());
    }

    String executeCommand(Player player, String command, String args) {
        if (player == null) {
            return "This error should never occur. Something has gone very, very wrong.";
        }
        if (player.getState() == GameState.NOT_STARTED) {
            return menuMap.get(command).run(player, args);
        }
        return gameMap.get(command).run(player, args);
    }
}
