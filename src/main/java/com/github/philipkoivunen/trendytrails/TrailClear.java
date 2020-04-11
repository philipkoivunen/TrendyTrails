package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import com.github.philipkoivunen.trendytrails.file.FileApi;
import com.github.philipkoivunen.trendytrails.objects.PlayerTrailsHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailClear implements ICommandHandler {
    private PlayerTrailsHolder playerTrailsHolder;
    private FileApi fileApi;
    public TrailClear(PlayerTrailsHolder playerTrailsHolder, FileApi fileApi) {
        this.fileApi = fileApi;
        this.playerTrailsHolder = playerTrailsHolder;
    }
    @Override
    public void handle(CommandSender commandSender, String[] strings, int i) {
        TrailSummoner currentTrail = this.playerTrailsHolder.getTrail(((Player) commandSender).getUniqueId());
        if(currentTrail == null) {
            MessageManager.sendMessage(commandSender, MessageConstants.NO_ACTIVE_TRAIL);
        }
        else{
            this.playerTrailsHolder.removeTrail(((Player) commandSender).getUniqueId());
            MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_REMOVED_SUCCESS);
            this.fileApi.setUserTrail(this.playerTrailsHolder);
        }
    }
}
