package com.github.philipkoivunen.trendytrails;

import com.comphenix.protocol.PacketType;
import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import com.github.philipkoivunen.trendytrails.constants.TrailConstants;
import com.github.philipkoivunen.trendytrails.helpers.ColorHelper;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TrailSummoner implements Listener {
    private Particle particle;
    private Player player;
    private Boolean isActive = false;
    private Boolean useDust = false;
    private String currentColor;

    public void setParticle(String particle) { this.particle = Particle.valueOf(particle.toUpperCase()); }
    public void setPlayer(Player player) { this.player = player;}
    public void setColor(String color) { this.currentColor = color;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public void setUseDust(Boolean useDust) {this.useDust = useDust;}

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        if(!this.isActive) return;
        if(this.useDust && this.currentColor != null) {
            int[] colors = ColorHelper.resolveColor(this.currentColor.toLowerCase());
            this.player.spawnParticle(this.particle, this.player.getLocation().getX(), this.player.getLocation().getY() + 0.5, this.player.getLocation().getZ(), 0, new Particle.DustOptions(Color.fromRGB(colors[0], colors[1], colors[2]), 2));
        }
        else this.player.spawnParticle(this.particle, this.player.getLocation().getX(), this.player.getLocation().getY() + 0.5, this.player.getLocation().getZ(), 0);
    }
}
