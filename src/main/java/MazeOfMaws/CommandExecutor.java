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
        gameMap = new HashMap<>();
        menuMap.put("welcome", new Welcome());
        menuMap.put("start", new StartGame());
        menuMap.put("status", new SendStatus());

        gameMap.put("status", new SendStatus());
        gameMap.put("end", new EndGame());
        gameMap.put("look", new Look());
        gameMap.put("go", new Go());
    }

    String sendWelcomeMessage(MessageReceivedEvent event) {
        return executeCommand(null, "welcome", event.getAuthor().getName());
    }

    String executeCommand(Player player, String command, String args) {
        if (player == null || player.getState() == GameState.NOT_STARTED) {
            return menuMap.get(command).run(player, args);
        }
        return gameMap.get(command).run(player, args);
    }
}
