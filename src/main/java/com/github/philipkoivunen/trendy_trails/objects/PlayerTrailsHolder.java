package com.github.philipkoivunen.trendy_trails.objects;

import com.github.philipkoivunen.trendy_trails.TrailSummoner;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class PlayerTrailsHolder {
    private final HashMap<UUID, TrailSummoner> playerTrails = new HashMap<>();

    public void addTrail(UUID uuid, TrailSummoner trailSummoner) {
        removeTrail(uuid);
        playerTrails.put(uuid, trailSummoner);
    }

    public TrailSummoner getTrail(UUID uuid) {
        return playerTrails.get(uuid);
    }

    public void removeTrail(UUID uuid) {
        playerTrails.remove(uuid);
    }

    public HashMap<UUID, TrailSummoner> getPlayerTrails() {
        return playerTrails;
    }
}
