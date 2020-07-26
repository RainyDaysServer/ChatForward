package com.github.hank9999.chatforward;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Commands implements TabExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("chatforward")) {
            if (commandSender.hasPermission("ChatForward.Admin")) {
                if (strings.length == 0) {
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward help &rto get help"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("help")) {
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward help &rto get help"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward reload &rto reload"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward on &rto on"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward off &rto off"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward url &rto get url"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward url <url> &rto config url"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward server &rto get server name"));
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUse &7/chatforward server <ServerName> &rto config server name"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("reload")) {
                    ChatForward.plugin.reloadConfig();
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rReload Config"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("on")) {
                    Config.setConfig("enable", true);
                    Config.saveConfig();
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rChat Listen ON"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("off")) {
                    Config.setConfig("enable", false);
                    Config.saveConfig();
                    commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rChat Listen OFF"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("url")) {
                    if (strings.length == 1) {
                        commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rUrl: " + Config.url));
                        return true;
                    } else {
                        Config.setConfig("url", strings[1]);
                        Config.saveConfig();
                        commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rConfig url: " + Config.url));
                        return true;
                    }
                }
                if (strings[0].equalsIgnoreCase("server")) {
                    if (strings.length == 1) {
                        commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rServerName: " + Config.server));
                        return true;
                    } else {
                        Config.setConfig("server", strings[1]);
                        Config.saveConfig();
                        commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &rConfig ServerName: " + Config.server));
                    }
                }
            } else {
                commandSender.sendMessage(Libs.color("&2[&eChatForward&2] &r&cYou don't have permission to use this command"));
                return true;
            }
        }
        return true;
    }

    private final String[] Commands = {"help", "reload", "on", "off", "url", "server"};
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) {
            return Collections.emptyList();
        }
        return Arrays.stream(Commands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }

}
