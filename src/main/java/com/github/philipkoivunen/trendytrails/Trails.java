package com.github.philipkoivunen.trendytrails;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.github.hornta.carbon.*;
import com.github.hornta.carbon.config.ConfigType;
import com.github.hornta.carbon.config.Configuration;
import com.github.hornta.carbon.config.ConfigurationBuilder;
import com.github.hornta.carbon.message.MessageManager;
import com.github.hornta.carbon.message.MessagesBuilder;
import com.github.hornta.carbon.message.Translation;
import com.github.hornta.carbon.message.Translations;
import com.github.philipkoivunen.trendytrails.commandhandlers.ColorHandler;
import com.github.philipkoivunen.trendytrails.commandhandlers.TrailHandler;
import com.github.philipkoivunen.trendytrails.constants.ColorConstants;
import com.github.philipkoivunen.trendytrails.constants.ConfigConstants;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import com.github.philipkoivunen.trendytrails.helpers.ColorHelper;
import com.github.philipkoivunen.trendytrails.objects.PlayerTrailsHolder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.logging.Level;

public class Trails extends JavaPlugin {
    private static Trails instance;
    private Carbon carbon;
    private ProtocolManager protocolManager;
    private MessageManager messageManager;
    private Configuration configuration;
    private Translations translations;
    private PlayerTrailsHolder playerTrailsHolder;
    private Listener listener;
    @Override
    public void onEnable() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        carbon = new Carbon();
        this.instance = this;

        try {
            configuration = new ConfigurationBuilder(this)
                    .add(ConfigConstants.LANGUAGE, "language", ConfigType.STRING.STRING, "english")
                    .build();
        } catch (Exception e) {
            setEnabled(false);
            getLogger().log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        messageManager = new MessagesBuilder()
                .add(MessageConstants.NO_PERMISSION, "no_permission")
                .add(MessageConstants.EFFECT_SET_SUCCESS, "effect_set_success")
                .add(MessageConstants.EFFECT_HANDLE_FAILED, "effect_handle_failed")
                .add(MessageConstants.EFFECT_REMOVED_SUCCESS, "effect_removed_success")
                .add(MessageConstants.COLOR_NOT_FOUND, "color_not_found")
                .add(MessageConstants.DEFAULT_ERROR, "default_error")
                .add(MessageConstants.MISSING_ARGUMENT, "missing_argument")
                .add(MessageConstants.CONFIGURATION_RELOADED, "configuration_reloaded")
                .add(MessageConstants.CONFIGURATION_RELOAD_FAILED, "configuration_reload_failed")
                .add(MessageConstants.NO_ACTIVE_TRAIL, "no_active_trail")
                .build();

        translations = new Translations(this, messageManager);
        Translation translation = translations.createTranslation(configuration.get(ConfigConstants.LANGUAGE));
        Translation fallbackTranslation = translations.createTranslation("english");
        messageManager.setTranslation(translation, fallbackTranslation);

        playerTrailsHolder = new PlayerTrailsHolder();

        carbon.setNoPermissionHandler((CommandSender sender, CarbonCommand command) -> {
            MessageManager.sendMessage(sender, MessageConstants.NO_PERMISSION);
        });

        Bukkit.getPluginManager().registerEvents(new EventListener(playerTrailsHolder), this);

        carbon.addCommand("trail set")
                .withHandler(new TrailSet(playerTrailsHolder))
                .withArgument(
                        new CarbonArgument.Builder("effect")
                                .setHandler(new TrailHandler())
                                .create()
                )
                .withArgument(new CarbonArgument.Builder("color").setDefaultValue(CommandSender.class, ColorConstants.WHITE.name()).setHandler(new ColorHandler()).create())
                .requiresPermission("trendytrails.trail.set.[0]")
                .preventConsoleCommandSender();

        carbon
                .addCommand("trail clear")
                .withHandler(new TrailClear(playerTrailsHolder))
                .requiresPermission("trendytrails.trail.clear");

        carbon
                .addCommand("trail reload")
                .withHandler(new TrailsReload())
                .requiresPermission("trendytrails.trail.reload");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return carbon.handleAutoComplete(sender, command, args);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return  carbon.handleCommand(sender, command, args);
    }

    public static Trails getInstance() {
        return instance;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Translations getTranslations() {
        return translations;
    }
    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
