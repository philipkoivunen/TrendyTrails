package com.github.philipkoivunen.trendy_trails;

import com.github.hornta.commando.ICommandHandler;
import com.github.hornta.messenger.MessageManager;
import com.github.hornta.messenger.Translation;
import com.github.philipkoivunen.trendy_trails.constants.ConfigConstants;
import com.github.philipkoivunen.trendy_trails.constants.MessageConstants;
import org.bukkit.command.CommandSender;

public class TrailsReload implements ICommandHandler {
    public void handle(CommandSender commandSender, String[] strings, int typedArgs) {
        TrendyTrailsPlugin.getInstance().getConfiguration().reload();
        Translation translation = TrendyTrailsPlugin.getInstance().getTranslations().createTranslation(TrendyTrailsPlugin.getInstance().getConfiguration().get(ConfigConstants.LANGUAGE));
        MessageManager.getInstance().setTranslation(translation);
        MessageManager.sendMessage(commandSender, MessageConstants.CONFIGURATION_RELOADED);
    }
}
