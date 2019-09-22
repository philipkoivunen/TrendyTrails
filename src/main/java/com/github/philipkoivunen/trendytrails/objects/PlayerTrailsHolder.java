package com.github.philipkoivunen.trendytrails.objects;

import com.github.philipkoivunen.trendytrails.TrailSummoner;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerTrailsHolder {
    private HashMap<String, TrailSummoner> playerTrails = new HashMap<>();

    public void addTrail(Player player, TrailSummoner trailSummoner) {
        removeTrail(player);
        playerTrails.put(player.getDisplayName(), trailSummoner);
    }

    public TrailSummoner getTrail(Player player) {
        return playerTrails.get(player.getDisplayName());
    }

    public void removeTrail(Player player) {
        TrailSummoner currentTrail = this.getTrail(player);
        if(currentTrail != null) {
            currentTrail.unRegisterMoveEvent();
            playerTrails.remove(player.getDisplayName());
        }
    }
}
