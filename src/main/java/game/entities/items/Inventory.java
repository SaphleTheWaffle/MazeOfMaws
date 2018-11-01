package game.entities.items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public Item removeItem(Item item) {
        if (items.remove(item)) {
            return item;
        }
        return null;
    }

    public int size() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItemByName(String name) {
        return items.stream()
                .filter(item -> item.getName().contains(name))
                .findFirst()
                .orElse(null);
    }

    public List<String> getItemNames() {
        return items.stream()
                .map(Item::getName)
                .collect(Collectors.toList());
    }

    public Item getItemById(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String describeItem(String name) {
        return items.stream()
                .filter(item -> item.getName().contains(name))
                .findFirst()
                .map(Item::describe)
                .orElse("");
    }
}
