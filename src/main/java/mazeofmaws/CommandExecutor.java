package mazeofmaws;

import game.GameState;
import game.Player;
import mazeofmaws.commands.*;
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
        gameMap.put("map", new Map());
        gameMap.put("get", new Get());
        gameMap.put("use", new Use());
        gameMap.put("attack", new Attack());
    }

    String sendWelcomeMessage(MessageReceivedEvent event) {
        return executeCommand(null, "welcome", event.getAuthor().getName());
    }

    String executeCommand(Player player, String command, String args) {
        Command cmd;
        if (player == null || player.getState() == GameState.NOT_STARTED) {
            cmd = menuMap.get(command);
        } else {
            cmd = gameMap.get(command);
        }
        StringBuilder sb = new StringBuilder();
        if (cmd != null) {
            sb.append(cmd.run(player, args));
            if (cmd.actionPerformed()) {
                String npcTurn = cmd.runNPCs(player);
                if (npcTurn != null && !npcTurn.equals("")) {
                    sb.append(npcTurn);
                }
            }
        }
        if (player != null && player.isDead()) {
            sb.append("You are dead. Not big surprise.");
            player.endGame();
        }
        return sb.toString();
    }
}
