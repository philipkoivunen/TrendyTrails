package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class FoxSummoner implements ICommandHandler, Listener {
    private final Plugin plugin;
    private Fox fox;
    private Player player;

    public FoxSummoner(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event){
        Vector vector = this.player.getLocation().toVector().subtract(this.fox.getVelocity()).normalize();
        this.fox.setVelocity(vector);
    };
    @Override
    public void handle(CommandSender commandSender, String[] args, int i) {
        Player player = (Player) commandSender;
        World world = player.getWorld();

        Fox fox = (Fox) world.spawnEntity(player.getLocation(), EntityType.FOX);
        this.fox = fox;
        this.player = player;
        this.fox.setSleeping(true);
    }
}
