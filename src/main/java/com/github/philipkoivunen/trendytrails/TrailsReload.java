package com.github.philipkoivunen.trendytrails;

import com.github.hornta.carbon.ICommandHandler;
import com.github.hornta.carbon.message.MessageManager;
import com.github.hornta.carbon.message.Translation;
import com.github.philipkoivunen.trendytrails.constants.ConfigConstants;
import com.github.philipkoivunen.trendytrails.constants.MessageConstants;
import org.bukkit.command.CommandSender;

public class TrailsReload implements ICommandHandler {
    public void handle(CommandSender commandSender, String[] strings, int typedArgs) {
    try {
        Trails.getInstance().getConfiguration().reload();
    } catch (Exception e) {
        MessageManager.sendMessage(commandSender, MessageConstants.CONFIGURATION_RELOAD_FAILED);
        return;
    }
    Translation translation = Trails.getInstance().getTranslations().createTranslation(Trails.getInstance().getConfiguration().get(ConfigConstants.LANGUAGE));
    MessageManager.getInstance().setPrimaryTranslation(translation);
    MessageManager.sendMessage(commandSender, MessageConstants.CONFIGURATION_RELOADED);
}

}
