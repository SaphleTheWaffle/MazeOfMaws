import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MazeOfMaws extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = getToken();
        if (token.length() < 1) {
            System.out.println("No login token found! Please store it at the following path:");
            System.out.println("MazeOfMaws/src/main/resources/token.txt");
            return;
        }
        builder.setToken(getToken());
        builder.addEventListener(new MessageListener("!"));
        builder.buildAsync();
    }

    private static String getToken() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get("src/main/resources/token.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded);
    }
}
