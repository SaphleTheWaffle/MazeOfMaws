package game;

import net.dv8tion.jda.core.entities.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class PlayerTest {
    private User userMock1;
    private User userMock2;

    @BeforeMethod
    public void setUp() {
        userMock1 = mock(User.class);
        when(userMock1.getId()).thenReturn("123");
        userMock2 = mock(User.class);
        when(userMock2.getId()).thenReturn("456");
    }

    @Test
    public void startGame_setsGameStateToStarted() {
        Player player = new Player(userMock1);
        assertEquals(GameState.NOT_STARTED, player.getState());
        assertTrue(player.startGame());
        assertEquals(GameState.STARTED, player.getState());
    }

    @Test
    public void endGame_setsGameStateToNotStarted() {
        Player player = new Player(userMock1);
        player.startGame();
        assertTrue(player.endGame());
        assertEquals(GameState.NOT_STARTED, player.getState());
    }

    @Test
    public void equals_withSameUser_isTrueForBoth() {
        Player player1 = new Player(userMock1);
        Player player2 = new Player(userMock1);
        assertEquals(player1, player2);
        assertEquals(player2, player1);
    }

    @Test
    public void equals_withDifferentId_isFalseForBoth() {
        Player player1 = new Player(userMock1);
        Player player2 = new Player(userMock2);
        assertNotEquals(player1, player2);
        assertNotEquals(player2, player1);
    }
}