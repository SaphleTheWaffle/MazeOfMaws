package Game.Entities.Creatures;

import Game.Entities.Coordinates;

public class Character extends Creature {

    public Character(String name, int maxHealth, int damage) {
        super(name, maxHealth, damage);
        this.coords = new Coordinates(0, 0, 1);
    }

    @Override
    public String describe() {
        return null;
    }
}
