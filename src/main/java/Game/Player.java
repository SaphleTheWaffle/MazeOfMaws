package Game;

import Game.Entities.Creatures.Character;
import Game.Entities.Room;
import net.dv8tion.jda.core.entities.User;

public class Player {
    private String id;
    private String name;
    private GameState state;
    private Maze maze;

    public Character getCharacter() {
        return character;
    }

    private Character character;

    public Player(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.state = GameState.NOT_STARTED;
        this.maze = new Maze();
    }

    public GameState getState() {
        return state;
    }

    public boolean startGame() {
        if (state == GameState.NOT_STARTED) {
            state = GameState.STARTED;
            maze.generateMaze();
            character = new Character("Test character", 100, 1);
            character.move(maze.getEntrance());
            return true;
        }
        return false;
    }

    public boolean endGame() {
        if (state != GameState.NOT_STARTED) {
            state = GameState.NOT_STARTED;
            maze = new Maze();
            return true;
        }
        return false;
    }

    public boolean move(int direction) {
        Room newRoom = character.getLocation().getExit(direction);
        if (newRoom != null) {
            character.move(newRoom);
            return true;
        }
        return false;
    }

    String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            return ((Player) o).id.equals(this.id);
        }

        return false;
    }
}
