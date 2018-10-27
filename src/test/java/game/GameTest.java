package game;

import net.dv8tion.jda.core.entities.User;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class GameTest {

    @Test
    public void addPlayer_registersPlayer() {
        String id = "1234";
        User userMock = mock(User.class);
        when(userMock.getId()).thenReturn(id);
        Game game = new Game();
        Player player = new Player(userMock);
        game.addPlayer(player);
        assertEquals(player, game.getPlayer(id));
    }
}