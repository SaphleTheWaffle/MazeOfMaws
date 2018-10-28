package game;

import game.entities.Room;
import game.entities.creatures.Character;
import game.world.Direction;
import game.world.Maze;
import game.world.generation.RoomMap;
import net.dv8tion.jda.core.entities.User;

public class Player {
    private String id;
    private String name;
    private GameState state;
    private Maze maze;
    private Character character;

    public Character getCharacter() {
        return character;
    }

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

    public boolean move(Direction direction) {
        Room newRoom = character.getLocation().getExit(direction);
        if (newRoom != null) {
            character.move(newRoom);
            return true;
        }
        return false;
    }

    public String describeItem(String itemName) {
        String res = character.describeItem(itemName);
        if (res.equals("")) {
            res = character.getLocation().describeItem(itemName);
        }
        return res;
    }

    String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoomMap getMap() {
        return maze.getMap();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            return ((Player) o).id.equals(this.id);
        }

        return false;
    }
}
