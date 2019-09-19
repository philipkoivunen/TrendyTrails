package com.github.philipkoivunen.trendytrails;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.github.hornta.carbon.*;
import com.github.philipkoivunen.trendytrails.commandhandlers.ColorHandler;
import com.github.philipkoivunen.trendytrails.commandhandlers.TrailHandler;
import com.github.philipkoivunen.trendytrails.constants.ColorConstants;
import com.github.philipkoivunen.trendytrails.messages.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public class Trails extends JavaPlugin {
    private Carbon carbon;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        carbon = new Carbon();
        carbon.setNoPermissionHandler((CommandSender sender, CarbonCommand command) -> {
            MessageHandler.sendMessage(sender, "Du har inte tillräcklig behörighet!", true);
        });

        TrailSummoner trailSummoner = new TrailSummoner(this);
        Bukkit.getPluginManager().registerEvents(trailSummoner, this);

        carbon.addCommand("trail set")
                .withHandler(trailSummoner)
                .withArgument(
                        new CarbonArgument.Builder("effect")
                                .setHandler(new TrailHandler())
                                .create()
                )
                .withArgument(new CarbonArgument.Builder("color").setDefaultValue(CommandSender.class, ColorConstants.WHITE.name()).setHandler(new ColorHandler()).create())
                .requiresPermission("trendytrails.trail.set.[0]")
                .preventConsoleCommandSender();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return carbon.handleAutoComplete(sender, command, args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return  carbon.handleCommand(sender, command, args);
    }
}
