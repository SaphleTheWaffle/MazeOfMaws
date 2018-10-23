package Game.Entities.Creatures;

import Game.Entities.Coordinates;
import Game.Entities.Describable;

public abstract class Creature implements Describable {
    private int maxHealth;
    private int currentHealth;
    private int damage;
    protected Coordinates coords;
    private String name;

    public Creature(String name, int maxHealth, int damage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.damage = damage;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void changeHealth(int delta) {
        currentHealth += delta;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public String toString() {
        return name;
    }
}
