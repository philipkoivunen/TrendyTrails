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
        this.playerTrailsHolder.removeTrail((Player) commandSender);
        MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_REMOVED_SUCCESS);
    }
}
