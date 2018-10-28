package game.entities.templates;

import java.util.List;

public class RoomType {
    private String id;
    private String description;
    private List<String> categories;
    private int rarity;

    RoomType(String id, String description, List<String> categories, int rarity) {
        this.id = id;
        this.description = description;
        this.categories = categories;
        this.rarity = rarity;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    String getId() {
        return id;
    }

    int getRarity() {
        return rarity;
    }
}
