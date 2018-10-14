package MazeOfMaws;

import Game.Game;
import Game.Player;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private final String prefix;
    private Game game;
    private CommandExecutor executor;

    MessageListener(String prefix, Game game) {
        this.prefix = prefix;
        this.game = game;
        this.executor = new CommandExecutor(game);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String contents = event.getMessage().getContentRaw();
        if (event.getAuthor().isBot()) {
            return;
        }

        System.out.println("Message received from " + event.getAuthor().getName());

        if (game.addPlayer(new Player(event.getAuthor()))) {
            executor.sendWelcomeMessage(event);
            return;
        }

        if (contents.equals(prefix + "status")) {
            executor.sendStatus(event);
        } else if (contents.equals(prefix + "start")) {
            executor.startGame(event);
        } else if (contents.equals(prefix + "end")) {
            executor.endGame(event);
        }
    }
}
