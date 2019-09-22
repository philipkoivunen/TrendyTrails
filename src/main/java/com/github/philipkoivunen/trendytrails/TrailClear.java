package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import com.github.philipkoivunen.trendytrails.objects.PlayerTrailsHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailClear implements ICommandHandler {
    private PlayerTrailsHolder playerTrailsHolder;
    public TrailClear(PlayerTrailsHolder playerTrailsHolder) {
        this.playerTrailsHolder = playerTrailsHolder;
    }
    @Override
    public void handle(CommandSender commandSender, String[] strings, int i) {
        TrailSummoner currentTrail = this.playerTrailsHolder.getTrail((Player) commandSender);
        if(currentTrail == null) {
            MessageManager.sendMessage(commandSender, MessageConstants.NO_ACTIVE_TRAIL);
        }
        else{
            this.playerTrailsHolder.removeTrail((Player) commandSender);
            MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_REMOVED_SUCCESS);
        }
    }
}
