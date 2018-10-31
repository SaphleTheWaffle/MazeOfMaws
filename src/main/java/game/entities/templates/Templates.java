package game.entities.templates;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Templates {
    private List<RoomType> roomTypes;
    private Random random;

    private static final Templates INSTANCE = new Templates();

    private Templates() {
        random = new Random();
        try {
            buildRoomTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Templates getInstance() {
        return INSTANCE;
    }

    private void buildRoomTypes() throws IOException {
        roomTypes = new ArrayList<>();

        String roomTypesString = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/RoomTypes.json")));
        JSONObject obj = new JSONObject(roomTypesString);
        JSONArray array = obj.getJSONArray("types");
        List<Object> types = array.toList();

        for (int i = 0; i < array.length(); i++) {
            HashMap<String, Object> type = (HashMap<String, Object>) types.get(i);
            String id = (String) type.get("id");
            String name = (String) type.get("name");
            String description = (String) type.get("description");
            List<String> categories = (List<String>) type.get("categories");
            int rarity = (int) type.get("rarity");
            roomTypes.add(new RoomType(id, name, description, categories, rarity));
        }
    }

    public RoomType getRoomType() {
        int maxRandom = 0;
        for (RoomType rt : roomTypes) {
            maxRandom += rt.getRarity();
        }
        int roomSelector = random.nextInt(maxRandom);
        for (RoomType rt : roomTypes) {
            if ((roomSelector -= rt.getRarity()) <= 0) {
                return rt;
            }
        }
        System.out.println("No element found!");
        return null;
    }

    public RoomType getByCategory(String category) {
        List<RoomType> types = roomTypes.stream()
                .filter(e -> e.getCategories().contains(category))
                .collect(Collectors.toList());
        int index = random.nextInt(types.size());
        return types.get(index);
    }

    public List<RoomType> getNumberByCategory(String category, int num) {
        List<RoomType> results = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            results.add(getByCategory(category));
        }
        return results;
    }
}
