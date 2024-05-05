package codes.kooper.blockify.models;

import codes.kooper.blockify.Blockify;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class Audience {

    private boolean arePlayersHidden = false;
    private final Set<UUID> players;
    private final Map<UUID, Float> miningSpeeds;

    public Audience(Set<UUID> players) {
        this.players = players;
        this.miningSpeeds = new HashMap<>();
    }

    public Audience(Set<UUID> players, boolean arePlayersHidden) {
        this.players = players;
        this.arePlayersHidden = arePlayersHidden;
        this.miningSpeeds = new HashMap<>();
    }

    public Set<UUID> addPlayer(UUID player) {
        players.add(player);
        return players;
    }

    public Set<UUID> removePlayer(UUID player) {
        players.remove(player);
        return players;
    }

    public void setMiningSpeed(UUID player, float speed) {
        if (speed <= 0 || speed == 1) {
            Blockify.instance.getLogger().warning("Invalid mining speed for player " + player + ": " + speed);
            return;
        }
        miningSpeeds.put(player, speed);
    }

    public void resetMiningSpeed(UUID player) {
        miningSpeeds.remove(player);
    }

    public float getMiningSpeed(UUID player) {
        return miningSpeeds.getOrDefault(player, 1f);
    }

}