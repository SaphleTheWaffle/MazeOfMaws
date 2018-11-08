package game.entities.creatures;

import game.entities.Room;

public class Creature {
    private String name;
    private String description;
    private Room location;

    private int maxHealth;
    private int currentHealth;
    private int damage;
    private boolean alive;

    public Creature(String name, String description, int maxHealth, int damage, Room location) {
        this.name = name;
        this.description = description;
        this.location = location;

        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
        this.alive = true;
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

    public String act() {
        if (currentHealth <= 0) {
            alive = false;
            return name + " lets out a shrill shriek as it collapses to the ground! Moments later, its body fades away like a bad CG effect.";
        }
        return name + " is loafing about!";
    }

    public boolean isAlive() {
        return alive;
    }
}
