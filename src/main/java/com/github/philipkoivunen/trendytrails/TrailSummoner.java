package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class TrailSummoner implements ICommandHandler, Listener {
    private final Plugin plugin;
    private Particle particle;
    private Player player;
    private Boolean isActive = false;

    public TrailSummoner(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        if(!this.isActive) return;;
        this.player.spawnParticle(this.particle, this.player.getLocation().getX(), this.player.getLocation().getY() + 0.5, this.player.getLocation().getZ(), 0);
    }

    @Override
    public void handle(CommandSender commandSender, String[] args, int i) {
        if(this.particle == null || !args[0].toUpperCase().equals(this.particle.name())) {
            this.particle = Particle.valueOf(args[0].toUpperCase());
            this.isActive = true;
        } else this.isActive = !isActive;
        this.player =  (Player) commandSender;

    }
}
