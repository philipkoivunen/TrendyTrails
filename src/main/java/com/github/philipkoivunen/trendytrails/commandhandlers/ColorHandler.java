package com.github.philipkoivunen.trendytrails.commandhandlers;

import com.github.hornta.carbon.ValidationResult;
import com.github.hornta.carbon.completers.IArgumentHandler;
import com.github.philipkoivunen.trendytrails.constants.ColorConstants;
import com.github.philipkoivunen.trendytrails.constants.TrailConstants;
import com.github.philipkoivunen.trendytrails.messages.MessageHandler;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ColorHandler implements IArgumentHandler {
    public Set<String> getItems(CommandSender sender, String argument, String[] prevArgs) {
        return Arrays.stream(ColorConstants.values())
                .map(ColorConstants::name)
                .map((String s) -> s.toLowerCase())
                .filter(state -> state.startsWith(argument.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public boolean test(Set<String> items, String argument) {
        return items.contains(argument.toLowerCase());
    }

    @Override
    public void whenInvalid(ValidationResult result) {
        MessageHandler.sendMessage(result.getCommandSender(), "Det gick fel när effekt skulle sättas", true);
    }
}
