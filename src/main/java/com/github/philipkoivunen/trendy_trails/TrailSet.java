package com.github.philipkoivunen.trendy_trails;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.messenger.MessageManager;
import com.github.philipkoivunen.trendy_trails.constants.MessageConstants;
import com.github.philipkoivunen.trendy_trails.constants.TrailConstants;
import com.github.philipkoivunen.trendy_trails.file.FileApi;
import com.github.philipkoivunen.trendy_trails.objects.PlayerTrailsHolder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrailSet implements ICommandHandler {
    private final PlayerTrailsHolder playerTrailsHolder;
    private final FileApi fileApi;

    public TrailSet(PlayerTrailsHolder playerTrailsHolder, FileApi fileApi) {
        this.playerTrailsHolder = playerTrailsHolder;
        this.fileApi = fileApi;
    }
    @Override
    public void handle(CommandSender commandSender, String[] args, int i) {
        this.playerTrailsHolder.addTrail(((Player) commandSender).getUniqueId(), new TrailSummoner());

        TrailSummoner currentTrail = this.playerTrailsHolder.getTrail(((Player) commandSender).getUniqueId());
        currentTrail.setPlayer(((Player) commandSender).getUniqueId());
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

        this.fileApi.setUserTrail(this.playerTrailsHolder);

        MessageManager.sendMessage(commandSender, MessageConstants.EFFECT_SET_SUCCESS);
    }
}
