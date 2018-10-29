package game.entities.creatures;

import game.entities.Room;

public abstract class Creature {
    private int maxHealth;
    private int currentHealth;
    private int damage;
    protected Room location;

    private String name;
    public Creature(String name, int maxHealth, int damage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.damage = damage;
    }

    public Room getLocation() {
        return location;
    }

    public void move(Room location) {
        this.location = location;
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
