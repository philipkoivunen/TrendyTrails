package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.*;
import com.github.philipkoivunen.trendytrails.commandhandlers.TrailSetHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Trails extends JavaPlugin {
    private Carbon carbon;

    @Override
    public void onEnable() {
        carbon = new Carbon();

        TrailSummoner trailSummoner = new TrailSummoner(this);
        Bukkit.getPluginManager().registerEvents(trailSummoner, this);

        carbon.addCommand("trail set")
                .withHandler(trailSummoner)
                .withArgument(
                        new CarbonArgument.Builder("effect")
                                .setHandler(new TrailSetHandler())
                                .create()
                );
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
