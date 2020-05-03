package com.github.philipkoivunen.trendy_trails;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.github.hornta.commando.CarbonArgument;
import com.github.hornta.commando.CarbonCommand;
import com.github.hornta.commando.Commando;
import com.github.hornta.messenger.MessageManager;
import com.github.hornta.messenger.MessagesBuilder;
import com.github.hornta.messenger.Translation;
import com.github.hornta.messenger.Translations;
import com.github.hornta.versioned_config.Configuration;
import com.github.hornta.versioned_config.ConfigurationBuilder;
import com.github.philipkoivunen.trendy_trails.commandhandlers.ColorHandler;
import com.github.philipkoivunen.trendy_trails.commandhandlers.TrailHandler;
import com.github.philipkoivunen.trendy_trails.config.InitialVersion;
import com.github.philipkoivunen.trendy_trails.constants.ColorConstants;
import com.github.philipkoivunen.trendy_trails.constants.ConfigConstants;
import com.github.philipkoivunen.trendy_trails.constants.MessageConstants;
import com.github.philipkoivunen.trendy_trails.file.FileApi;
import com.github.philipkoivunen.trendy_trails.objects.PlayerTrailsHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class TrendyTrailsPlugin extends JavaPlugin {
    private static TrendyTrailsPlugin instance;
    private Commando commando;
    private ProtocolManager protocolManager;
    private Configuration<ConfigConstants> configuration;
    private Translations translations;
    private PlayerTrailsHolder playerTrailsHolder;
    private FileApi fileApi;
    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        instance = this;
        setupConfig();
        setupMessages();
        playerTrailsHolder = new PlayerTrailsHolder();
        Bukkit.getPluginManager().registerEvents(new EventListener(playerTrailsHolder), this);
        fileApi = new FileApi(this);
        setupCommands();
        fileApi.fetchUsers();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return commando.handleAutoComplete(sender, command, args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return  commando.handleCommand(sender, command, args);
    }

    private void setupConfig() {
        File cfgFile = new File(getDataFolder(), "config.yml");
        ConfigurationBuilder<ConfigConstants> cb = new ConfigurationBuilder<>(this, cfgFile);
        cb.addVersion(new InitialVersion());
        configuration = cb.run();
    }

    private void setupMessages() {
        MessageManager messageManager = new MessagesBuilder()
          .add(MessageConstants.NO_PERMISSION, "no_permission")
          .add(MessageConstants.EFFECT_SET_SUCCESS, "effect_set_success")
          .add(MessageConstants.EFFECT_HANDLE_FAILED, "effect_handle_failed")
          .add(MessageConstants.EFFECT_REMOVED_SUCCESS, "effect_removed_success")
          .add(MessageConstants.COLOR_NOT_FOUND, "color_not_found")
          .add(MessageConstants.DEFAULT_ERROR, "default_error")
          .add(MessageConstants.MISSING_ARGUMENT, "missing_argument")
          .add(MessageConstants.CONFIGURATION_RELOADED, "configuration_reloaded")
          .add(MessageConstants.NO_ACTIVE_TRAIL, "no_active_trail")
          .build();

        translations = new Translations(this, messageManager);
        Translation translation = translations.createTranslation(configuration.get(ConfigConstants.LANGUAGE));
        messageManager.setTranslation(translation);
    }

    private void setupCommands() {
        commando = new Commando();
        commando.setNoPermissionHandler((CommandSender sender, CarbonCommand command) -> MessageManager.sendMessage(sender, MessageConstants.NO_PERMISSION));

        commando.addCommand("trail set")
          .withHandler(new TrailSet(playerTrailsHolder, fileApi))
          .withArgument(
            new CarbonArgument.Builder("effect")
              .setHandler(new TrailHandler())
              .create()
          )
          .withArgument(new CarbonArgument.Builder("color").setDefaultValue(CommandSender.class, ColorConstants.WHITE.name()).setHandler(new ColorHandler()).create())
          .requiresPermission("trendytrails.trail.set.[0]")
          .preventConsoleCommandSender();

        commando
          .addCommand("trail clear")
          .withHandler(new TrailClear(playerTrailsHolder, fileApi))
          .requiresPermission("trendytrails.trail.clear")
          .preventConsoleCommandSender();

        commando
          .addCommand("trail reload")
          .withHandler(new TrailsReload())
          .requiresPermission("trendytrails.trail.reload");
    }

    public static TrendyTrailsPlugin getInstance() {
        return instance;
    }

    public Configuration<ConfigConstants> getConfiguration() {
        return configuration;
    }

    public Translations getTranslations() {
        return translations;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public PlayerTrailsHolder getPlayerTrailsHolder() { return playerTrailsHolder; }
}
