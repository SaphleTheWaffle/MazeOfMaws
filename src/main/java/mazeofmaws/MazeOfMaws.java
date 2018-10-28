package mazeofmaws;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MazeOfMaws {
    private static final String PROD_TOKEN_PATH = "../resources/main/token.txt";
    private static final String TEST_TOKEN_PATH = "src/main/resources/token.txt";

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = getToken();
        if (token.length() < 1) {
            System.out.println("No login token found! Please store it at the following path:");
            System.out.println("MazeOfMaws/src/main/resources/token.txt");
            return;
        }
        builder.setToken(token);
        builder.addEventListener(new MessageListener(""));
        builder.buildAsync();
    }

    private static String getToken() {
        byte[] encoded = new byte[0];
        try {
            if (new File(PROD_TOKEN_PATH).exists()) {
                encoded = Files.readAllBytes(Paths.get(PROD_TOKEN_PATH));
            } else {
                encoded = Files.readAllBytes(Paths.get(TEST_TOKEN_PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded);
    }
}
