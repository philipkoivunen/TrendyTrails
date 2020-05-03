package com.github.philipkoivunen.trendy_trails.commandhandlers;

import com.github.hornta.commando.ValidationResult;
import com.github.hornta.commando.completers.IArgumentHandler;
import com.github.hornta.messenger.MessageManager;
import com.github.philipkoivunen.trendy_trails.constants.MessageConstants;
import com.github.philipkoivunen.trendy_trails.constants.TrailConstants;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TrailHandler implements IArgumentHandler {
    public Set<String> getItems(CommandSender sender, String argument, String[] prevArgs) {
        return Arrays.stream(TrailConstants.values())
                .map(TrailConstants::name)
                .map(String::toLowerCase)
                .filter(state -> state.startsWith(argument.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public boolean test(Set<String> items, String argument) {
        return items.contains(argument.toLowerCase());
    }

    @Override
    public void whenInvalid(ValidationResult result) {
        MessageManager.sendMessage(result.getCommandSender(), MessageConstants.EFFECT_HANDLE_FAILED);
    }
}
