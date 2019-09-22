package com.github.philipkoivunen.trendytrails.objects;

import com.github.philipkoivunen.trendytrails.TrailSummoner;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerTrailsHolder {
    private HashMap<UUID, TrailSummoner> playerTrails = new HashMap<>();

    public void addTrail(Player player, TrailSummoner trailSummoner) {
        removeTrail(player);
        playerTrails.put(player.getUniqueId(), trailSummoner);
    }

    public TrailSummoner getTrail(Player player) {
        return playerTrails.get(player.getUniqueId());
    }

    public void removeTrail(Player player) {
        playerTrails.remove(player.getUniqueId());
    }
}
