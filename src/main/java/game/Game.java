package game;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> activePlayers = new ArrayList<>();

    public boolean addPlayer(Player player) {
        if (!activePlayers.contains(player)) {
            activePlayers.add(player);
            return true;
        }
        return false;
    }

    public Player getPlayer(String id) {
        for(Player p : activePlayers) {
            if (id.equals(p.getId())) {
                return p;
            }
        }
        return null;
    }
}
