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
        this.executor = new CommandExecutor();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String contents = event.getMessage().getContentRaw();
        if (event.getAuthor().isBot()) {
            return;
        }

        System.out.println("Message received from " + event.getAuthor().getName());

        if (game.addPlayer(new Player(event.getAuthor()))) {
            event.getChannel().sendMessage(executor.sendWelcomeMessage(event)).queue();
            return;
        }

        String command = contents.substring(prefix.length()).split(" ")[0];
        String args = contents.substring(prefix.length() + command.length());

        String res = executor.executeCommand(game.getPlayer(event.getAuthor().getId()), command, args);

        event.getChannel().sendMessage(res).queue();
    }
}
