package com.github.philipkoivunen.trendytrails.message;

import org.bukkit.command.CommandSender;

public class MessageHandler {
    public static void sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(message);
    }
}
