import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class MazeOfMaws extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NDg3ODY2NDIxMTk2MzU3NjMz.DpFDhw.vxKwHy43m7NerhbY8n5S3ejcCwQ";
        builder.setToken(token);
        builder.addEventListener(new MessageListener("!"));
        builder.buildAsync();
    }
}
