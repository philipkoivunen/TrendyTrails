package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import com.github.philipkoivunen.trendytrails.constants.TrailConstants;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailSet implements ICommandHandler {
    private final TrailSummoner trailSummoner;

    public TrailSet(TrailSummoner trailSummoner) {
        this.trailSummoner = trailSummoner;
    }
    @Override
    public void handle(CommandSender commandSender, String[] args, int i) {
        this.trailSummoner.setPlayer((Player) commandSender);
        if(args[0].toUpperCase().equals(TrailConstants.REDSTONE.name()) && args[1] != null) {
            this.trailSummoner.setParticle(args[0]);
            this.trailSummoner.setColor(args[1]);
            this.trailSummoner.setIsActive(true);
            this.trailSummoner.setUseDust(true);
        } else {
            this.trailSummoner.setParticle(args[0]);
            this.trailSummoner.setColor(null);
            this.trailSummoner.setIsActive(true);
            this.trailSummoner.setUseDust(false);
        }
        MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_SET_SUCCESS);
    }
}
