package com.github.philipkoivunen.trendytrails;

import com.github.philipkoivunen.trendytrails.helpers.ColorHelper;
import com.github.philipkoivunen.trendytrails.objects.PlayerTrailsHolder;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventListener implements Listener {
    private PlayerTrailsHolder playerTrailsHolder;


    public EventListener(PlayerTrailsHolder playerTrailsHolder) {
        this.playerTrailsHolder = playerTrailsHolder;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        TrailSummoner currentTrail = this.playerTrailsHolder.getTrail(event.getPlayer());
        if(currentTrail != null) {
            if(!currentTrail.isActive) return;
            if(currentTrail.useDust && currentTrail.currentColor != null) {
                int[] colors = ColorHelper.resolveColor(currentTrail.currentColor.toLowerCase());
                currentTrail.player.getWorld().spawnParticle(currentTrail.particle, currentTrail.player.getLocation().getX(), currentTrail.player.getLocation().getY() + 0.5, currentTrail.player.getLocation().getZ(), 0, new Particle.DustOptions(Color.fromRGB(colors[0], colors[1], colors[2]), 2));
            }
            else currentTrail.player.getWorld().spawnParticle(currentTrail.particle, currentTrail.player.getLocation().getX(), currentTrail.player.getLocation().getY() + 0.5, currentTrail.player.getLocation().getZ(), 0);
        }
    }
}
