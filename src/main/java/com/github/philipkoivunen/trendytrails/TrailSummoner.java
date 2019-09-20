package com.github.philipkoivunen.trendytrails;

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
import org.bukkit.plugin.Plugin;

public class TrailSummoner implements ICommandHandler, Listener {
    private final Plugin plugin;
    private Particle particle;
    private Player player;
    private Boolean isActive = false;
    private Boolean useDust = false;
    private String currentColor;

    //TODO: move carbon code out to a new class and make the trailsummoner class more general so it can be used by other commandhandlers.
    public TrailSummoner(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        if(!this.isActive) return;
        if(this.useDust && this.currentColor != null) {
            int[] colors = ColorHelper.resolveColor(this.currentColor.toLowerCase());
            this.player.spawnParticle(this.particle, this.player.getLocation().getX(), this.player.getLocation().getY() + 0.5, this.player.getLocation().getZ(), 0, new Particle.DustOptions(Color.fromRGB(colors[0], colors[1], colors[2]), 2));
        }
        else this.player.spawnParticle(this.particle, this.player.getLocation().getX(), this.player.getLocation().getY() + 0.5, this.player.getLocation().getZ(), 0);
    }

    @Override
    public void handle(CommandSender commandSender, String[] args, int i) {
        if(this.particle == null || !args[0].toUpperCase().equals(this.particle.name())) {
            if(args[0].toUpperCase().equals(TrailConstants.REDSTONE.name()) && args[1] != null) {
                this.currentColor = args[1].toUpperCase();
                this.isActive = true;
                this.particle = Particle.valueOf(args[0].toUpperCase());
                this.useDust = true;

                MessageManager.sendMessage(this.player, MessageConstants.EFFECT_HANDLE_FAILED);
            } else {
                this.particle = Particle.valueOf(args[0].toUpperCase());
                this.isActive = true;
                this.useDust = false;
            }
        } else if(args[0].toUpperCase().equals(TrailConstants.REDSTONE.name()) && args[1] != null && args[1].toUpperCase() != currentColor) {
            this.currentColor = args[1].toUpperCase();
            this.useDust = true;
            MessageManager.sendMessage(this.player, MessageConstants.EFFECT_SET_SUCCESS);
        } else{
            this.isActive = false;
            this.useDust = false;

            MessageManager.sendMessage(this.player, MessageConstants.EFFECT_REMOVED_SUCCESS);
        }
        this.player =  (Player) commandSender;
    }
}
