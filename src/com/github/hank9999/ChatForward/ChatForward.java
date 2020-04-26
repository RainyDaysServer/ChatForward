package com.github.hank9999.ChatForward;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public final class ChatForward extends JavaPlugin {

    public static ChatForward plugin;

    //当插件被Load(加载)时执行
    @Override
    public void onLoad() {
        getLogger().info(ChatColor.BLUE + "消息转发插件正在加载");
    }

    //当插件被Enable(开启)时执行
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        reloadConfig();
        getLogger().info(ChatColor.BLUE + "消息转发插件已启用");
        getLogger().info(ChatColor.GOLD + "版本v1.3");
        getServer().getPluginManager().registerEvents(new ChatListen(), this);
        getServer().getPluginCommand("chatforword").setExecutor(new ChatForward_Commands());
        getServer().getPluginCommand("chatforword").setTabCompleter(new ChatForward_Commands());
    }


    //当插件被Disable(关闭)时执行
    @Override
    public void onDisable() {
        plugin = null;
        getLogger().info(ChatColor.BLUE + "消息转发插件已停用");
    }
}