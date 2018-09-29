package MazeOfMaws;

import Game.Game;
import Game.Player;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private final String prefix;
    private Game game;

    MessageListener(String prefix, Game game) {
        this.prefix = prefix;
        this.game = game;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String contents = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        if (event.getAuthor().isBot()) {
            return;
        }

        System.out.println("Message received from " + event.getAuthor().getName());

        if (game.addPlayer(new Player(event.getAuthor()))) {
            sendWelcomeMessage(event);
            return;
        }

        if (contents.equals(prefix + "status")) {
            sendStatus(event, channel);
        } else if (contents.equals(prefix + "start")) {
            startGame(event, channel);
        } else if (contents.equals(prefix + "end")) {
            endGame(event, channel);
        }
    }

    private void startGame(MessageReceivedEvent event, MessageChannel channel) {
        if (game.getPlayer(event.getAuthor().getId()).startGame()) {
            channel.sendMessage("Game started. Good luck!").queue();
        } else {
            channel.sendMessage("You already have a game running.").queue();
        }
    }

    private void endGame(MessageReceivedEvent event, MessageChannel channel) {
        if (game.getPlayer(event.getAuthor().getId()).endGame()) {
            channel.sendMessage("Game ended.").queue();
        } else {
            channel.sendMessage("You don't currently have an active game running.").queue();
        }
    }

    private void sendStatus(MessageReceivedEvent event, MessageChannel channel) {
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

    private void sendWelcomeMessage(MessageReceivedEvent event) {
        event.getChannel().sendMessage("Hi, " + event.getAuthor().getName() + "!").queue();
        event.getChannel().sendMessage("Welcome to the Maze of Maws! Currently a work in progress.").queue();
    }
}
