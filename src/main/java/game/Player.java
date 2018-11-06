package game;

import game.entities.Room;
import game.entities.creatures.Character;
import game.entities.items.Item;
import game.entities.templates.Encounter;
import game.world.Direction;
import game.world.generation.GenericMazeBuilder;
import game.world.generation.MazeBuilder;
import game.world.generation.PrisonBuilder;
import game.world.generation.RoomMap;
import net.dv8tion.jda.core.entities.User;

public class Player {
    private String id;
    private String name;
    private GameState state;
    private MazeBuilder maze;
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public Player(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.state = GameState.NOT_STARTED;
        this.maze = new PrisonBuilder();
    }

    public GameState getState() {
        return state;
    }

    public boolean startGame() {
        if (state == GameState.NOT_STARTED) {
            state = GameState.STARTED;
            maze.build();
            character = new Character("Test character", 100, 1);
            character.move(maze.getEntrance());
            return true;
        }
        return false;
    }

    public boolean endGame() {
        if (state != GameState.NOT_STARTED) {
            state = GameState.NOT_STARTED;
            maze = new GenericMazeBuilder();
            return true;
        }
        return false;
    }

    public boolean isExitLocked(Direction direction) {
        return character.getLocation().isExitLocked(direction);
    }

    public String move(Direction direction) {
        Room newRoom = character.getLocation().getExit(direction);
        if (character.getLocation().isExitLocked(direction)) {
            return character.getLocation().getEncounter().getBlockingMessage();
        }
        if (newRoom != null) {
            character.move(newRoom);
            return "Moving through the " + direction.name +"ern exit, you find yourself in " + character.enterRoom();
        }
        return "There is no exit in that direction!";
    }

    public String useItem(String itemName) {
        Encounter roomEncounter = character.getLocation().getEncounter();
        Item item = character.getInventory().getItemByName(itemName);
        if (roomEncounter != null && roomEncounter.checkUseItem(item)) {
            return roomEncounter.getRemoveBlockingMessage();
        }
        if (item != null && item.use()) {
            return "You used " + item.getName() + ".";
        }
        return "You can't use that!";
    }

    public String describeRoom() {
        return character.describeRoom();
    }

    public String describeItem(String itemName) {
        String res = character.describeItem(itemName);
        if (res.equals("")) {
            res = character.getLocation().getInventory().describeItem(itemName);
        }
        return res;
    }

    public boolean pickupItem(String itemName) {
        return character.pickupItem(itemName);
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
        return o instanceof Player && ((Player) o).id.equals(this.id);

    }
}
