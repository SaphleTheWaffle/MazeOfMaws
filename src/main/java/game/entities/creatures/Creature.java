package game.entities.creatures;

import java.util.Random;

public class Creature {
    private Random random;

    private String name;
    private String description;
    private CreatureState state;

    private int maxHealth;
    private int currentHealth;
    private int damage;
    private boolean alive;

    public Creature(String name, String description, int maxHealth, int damage) {
        this.name = name;
        this.description = description;

        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
        this.alive = true;
        this.state = CreatureState.UNAWARE;

        random = new Random();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String describe() {
        return description;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getDamage() {
        return damage;
    }

    void changeHealth(int delta) {
        currentHealth += delta;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public String act(Character character) {
        if (currentHealth <= 0) {
            alive = false;
            return name + " lets out a shrill shriek as it collapses to the ground! Moments later, its body fades away like a bad CG effect.";
        }
        if (state == CreatureState.UNAWARE) {
            state = CreatureState.HOSTILE;
            return name + " turns around to look at you, evil intent glinting in its eyes!";
        }
        if (state == CreatureState.HOSTILE) {
            int damage = random.nextInt(2) + 3;

            character.changeHealth(0 - damage);
            return name + " attacks you for " + damage + " damage!";
        }
        return name + " is loafing about!";
    }

    public boolean isAlive() {
        return alive;
    }
}
