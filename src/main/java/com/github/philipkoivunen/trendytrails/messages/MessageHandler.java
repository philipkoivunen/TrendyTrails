package com.github.philipkoivunen.trendytrails.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageHandler {
    public static void sendMessage(CommandSender commandSender, String message, Boolean isWarning) {
        commandSender.sendMessage(isWarning ? ChatColor.RED + message : ChatColor.YELLOW + message);
    }
}
