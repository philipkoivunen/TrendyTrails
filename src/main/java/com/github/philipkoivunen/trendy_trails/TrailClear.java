package com.github.philipkoivunen.trendy_trails;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.messenger.MessageManager;
import com.github.philipkoivunen.trendy_trails.constants.MessageConstants;
import com.github.philipkoivunen.trendy_trails.file.FileApi;
import com.github.philipkoivunen.trendy_trails.objects.PlayerTrailsHolder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailClear implements ICommandHandler {
    private final PlayerTrailsHolder playerTrailsHolder;
    private final FileApi fileApi;
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
