package com.github.hank9999.ChatForward;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChatForward_Commands implements TabExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("chatforword")) {
            if (commandSender.hasPermission("ChatForward.Admin")) {
                if (strings.length == 0) {
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword help &rto get help"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("help")) {
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword help &rto get help"));
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword reload &rto reload"));
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword on &rto on"));
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword off &rto off"));
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword url &rto get url"));
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUse &7/chatforword url <url> &rto config url"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("reload")) {
                    ChatForward.plugin.reloadConfig();
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rReload Config"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("on")) {
                    ChatForward.plugin.getConfig().set("enable", true);
                    ChatForward.plugin.saveConfig();
                    ChatForward.plugin.reloadConfig();
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rChat Listen ON"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("off")) {
                    ChatForward.plugin.getConfig().set("enable", false);
                    ChatForward.plugin.saveConfig();
                    ChatForward.plugin.reloadConfig();
                    commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rChat Listen OFF"));
                    return true;
                }
                if (strings[0].equalsIgnoreCase("url")) {
                    if (strings.length == 1) {
                        commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rUrl: " + ChatForward.plugin.getConfig().getString("url")));
                        return true;
                    } else {
                        ChatForward.plugin.getConfig().set("url", strings[1]);
                        ChatForward.plugin.saveConfig();
                        ChatForward.plugin.reloadConfig();
                        commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &rConfig url: " + ChatForward.plugin.getConfig().getString("url")));
                    }
                }
            } else {
                commandSender.sendMessage(Lib.color_translate("&2[&eChatForward&2] &r&cYou don't have permission to use this command"));
                return true;
            }
        }
        return true;
    }

    private String[] Commands = {"help", "reload", "on", "off", "url"};
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) return new ArrayList<>();
        if (args.length == 0) return Arrays.asList(Commands);
        return Arrays.stream(Commands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }

}