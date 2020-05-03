package com.github.philipkoivunen.trendy_trails;

import com.github.philipkoivunen.trendy_trails.helpers.ColorHelper;
import com.github.philipkoivunen.trendy_trails.objects.PlayerTrailsHolder;
import com.github.philipkoivunen.trendy_trails.packetWrapper.WrapperPlayServerWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import com.comphenix.protocol.wrappers.WrappedParticle;

import java.lang.reflect.InvocationTargetException;

public class EventListener implements Listener {
    private final PlayerTrailsHolder playerTrailsHolder;

    public EventListener(PlayerTrailsHolder playerTrailsHolder) {
        this.playerTrailsHolder = playerTrailsHolder;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) throws InvocationTargetException {
        TrailSummoner currentTrail = this.playerTrailsHolder.getTrail(event.getPlayer().getUniqueId());
        if(currentTrail != null) {
            WrapperPlayServerWorldParticles wpswp = new WrapperPlayServerWorldParticles();
            wpswp.setNumberOfParticles(1);
            wpswp.setLongDistance(true);
            Location playerLocation = event.getPlayer().getLocation();
            if(!currentTrail.isActive) return;
            if(currentTrail.useDust && currentTrail.currentColor != null) {
                int[] colors = ColorHelper.resolveColor(currentTrail.currentColor.toLowerCase());
                wpswp.setParticleType(WrappedParticle.create(currentTrail.particle, new Particle.DustOptions(Color.fromRGB(colors[0], colors[1], colors[2]), 2)));
            } else {
                wpswp.setParticleType(WrappedParticle.create(currentTrail.particle, new Particle.DustOptions(Color.fromRGB(250, 250, 250), 2)));
            }
            wpswp.setX((float) playerLocation.getX());
            wpswp.setY((float) (playerLocation.getY() + .5));
            wpswp.setZ((float) playerLocation.getZ());
            for(Player p : Bukkit.getOnlinePlayers()) {
                TrendyTrailsPlugin.getInstance().getProtocolManager().sendServerPacket(p, wpswp.getHandle());
            }
        }
    }
}
