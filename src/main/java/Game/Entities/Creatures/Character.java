package Game.Entities.Creatures;

public class Character extends Creature {

    public Character(String name, int maxHealth, int damage) {
        super(name, maxHealth, damage);
    }

    @Override
    public String describe() {
        return null;
    }
}
