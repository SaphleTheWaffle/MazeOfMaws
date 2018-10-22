package MazeOfMaws;

import Game.Player;
import MazeOfMaws.Commands.Command;
import MazeOfMaws.Commands.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.HashMap;

class CommandExecutor {

    private HashMap<String, Command> commandMap;

    CommandExecutor() {
        initCommands();
    }

    private void initCommands() {
        commandMap = new HashMap<>();
        commandMap.put("welcome", new Welcome());
        commandMap.put("status", new SendStatus());
        commandMap.put("start", new StartGame());
        commandMap.put("end", new EndGame());
    }

    String sendWelcomeMessage(MessageReceivedEvent event) {
        return executeCommand(null, "welcome", event.getAuthor().getName());
    }

    String executeCommand(Player player, String command, String args) {
        return commandMap.get(command).run(player, args);
    }
}
