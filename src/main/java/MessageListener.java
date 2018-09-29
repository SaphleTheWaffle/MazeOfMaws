import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private final String prefix;

    MessageListener(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String contents = event.getMessage().getContentRaw();
        if (event.getAuthor().isBot() || !contents.startsWith(prefix)) {
            return;
        }

        System.out.println("Message received from " + event.getAuthor().getName());

        if (contents.equals(prefix + "bleep")) {
            event.getChannel().sendMessage("Bloop.").queue();
        }
    }
}
