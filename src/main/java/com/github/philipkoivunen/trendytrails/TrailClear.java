package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import org.bukkit.command.CommandSender;

public class TrailClear implements ICommandHandler {
    private final TrailSummoner trailSummoner;

    public TrailClear(TrailSummoner trailSummoner) {
        this.trailSummoner = trailSummoner;
    }
    @Override
    public void handle(CommandSender commandSender, String[] strings, int i) {
        this.trailSummoner.setIsActive(false);
        this.trailSummoner.setUseDust(false);
        MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_REMOVED_SUCCESS);
    }
}
