package MazeOfMaws;

import Game.Game;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

class CommandExecutor {
    private Game game;

    CommandExecutor(Game game) {
        this.game = game;
    }

    void startGame(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if (game.getPlayer(event.getAuthor().getId()).startGame()) {
            channel.sendMessage("Game started. Good luck!").queue();
        } else {
            channel.sendMessage("You already have a game running.").queue();
        }
    }

    void endGame(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if (game.getPlayer(event.getAuthor().getId()).endGame()) {
            channel.sendMessage("Game ended.").queue();
        } else {
            channel.sendMessage("You don't currently have an active game running.").queue();
        }
    }

    void sendStatus(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        switch (game.getPlayer(event.getAuthor().getId()).getState()) {
            case NOT_STARTED:
                channel.sendMessage("You do not currently have a game running.").queue();
                break;
            case STARTED:
                channel.sendMessage("You are currently in game.").queue();
                break;
            case IN_COMBAT:
                channel.sendMessage("You are currently in combat.").queue();
                break;
            default:
                channel.sendMessage("You are somehow in a game state that doesn't exist.").queue();
        }
    }

    void sendWelcomeMessage(MessageReceivedEvent event) {
        event.getChannel().sendMessage("Hi, " + event.getAuthor().getName() + "!").queue();
        event.getChannel().sendMessage("Welcome to the Maze of Maws! Currently a work in progress.").queue();
    }
}
