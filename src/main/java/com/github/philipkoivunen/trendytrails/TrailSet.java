package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import com.github.philipkoivunen.trendytrails.constants.TrailConstants;
import com.github.philipkoivunen.trendytrails.objects.PlayerTrailsHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailSet implements ICommandHandler {
    private final PlayerTrailsHolder playerTrailsHolder;

    public TrailSet(PlayerTrailsHolder playerTrailsHolder) {
        this.playerTrailsHolder = playerTrailsHolder;
    }
    @Override
    public void handle(CommandSender commandSender, String[] args, int i) {
        this.playerTrailsHolder.addTrail((Player) commandSender, new TrailSummoner());
        TrailSummoner currentTrail = this.playerTrailsHolder.getTrail((Player) commandSender);
        currentTrail.setPlayer((Player) commandSender);
        if(args[0].toUpperCase().equals(TrailConstants.REDSTONE.name()) && args[1] != null) {
            currentTrail.setParticle(args[0]);
            currentTrail.setColor(args[1]);
            currentTrail.setIsActive(true);
            currentTrail.setUseDust(true);
        } else {
            currentTrail.setParticle(args[0]);
            currentTrail.setColor(null);
            currentTrail.setIsActive(true);
            currentTrail.setUseDust(false);
        }
        Bukkit.getPluginManager().registerEvents(currentTrail, Trails.getInstance());
        MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_SET_SUCCESS);
    }
}
