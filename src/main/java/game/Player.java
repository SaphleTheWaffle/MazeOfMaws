package game;

import game.entities.Room;
import game.entities.creatures.Character;
import game.entities.items.Item;
import game.world.Direction;
import game.world.generation.RoomMap;
import game.world.generation.builders.MazeBuilder;
import game.world.generation.builders.PrisonBuilder;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;

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
    }

    public GameState getState() {
        return state;
    }

    public boolean startGame() {
        if (state == GameState.NOT_STARTED) {
            state = GameState.STARTED;
            maze = new PrisonBuilder();
            maze.build();
            character = new Character();
            character.move(maze.getEntrance());
            return true;
        }
        return false;
    }

    public boolean endGame() {
        if (state != GameState.NOT_STARTED) {
            state = GameState.NOT_STARTED;
            return true;
        }
        return false;
    }

    public String move(Direction direction) {
        Room newRoom = character.getLocation().getExit(direction);
        if (character.getLocation().isExitLocked(direction)) {
            return character.getLocation().getObstacle(direction).getBlockingMessage();
        }
        if (newRoom != null) {
            character.move(newRoom);
            return "Moving towards the " + direction.name +", you find yourself in " + character.enterRoom();
        }
        return "There is no exit in that direction!";
    }

    public String useItem(String itemName, String target) {
        Item item = character.getInventory().getItemByName(itemName);
        if (item == null) {
            return "Could not find an item matching that name!";
        }
        if (item.getId().contains("key")) {
            return useKey(target, item);
        }
        if (item.use()) {
            return "You used " + item.getName() + ".";
        }
        return "You cannot use that!";
    }

    private String useKey(String target, Item item) {
        List<Direction> directions = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (target.contains(dir.name)) {
                directions.add(dir);
            }
        }
        if (directions.size() != 1) {
            return "Which door would you like to unlock?";
        }

        Direction direction = directions.get(0);
        Room currentRoom = character.getLocation();
        if (currentRoom.unlockDoor(direction, item)) {
            return currentRoom.getObstacle(direction).getUnblockedMessage();
        }
        return "That door is not locked.";
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

    public String describeCreature(String creatureName) {
        return character.getLocation().describeCreature(creatureName);
    }

    public String attack(String creatureName) {
        return character.attack(creatureName);
    }

    public String enemyTurn() {
        return character.getLocation().creatureActions(character);
    }

    String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDead() {
        if (state != GameState.STARTED) {
            return false;
        }
        return character.getHealth() <= 0;
    }

    public RoomMap getMap() {
        return maze.getMap();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Player && ((Player) o).id.equals(this.id);

    }
}
