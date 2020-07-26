package com.github.hank9999.chatforward;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.URLEncoder;

public class ChatListener implements Listener {
    //监听用户名 和 用户消息内容
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
        if (ChatForward.plugin.getConfig().getBoolean("enable")) {
            Player player = event.getPlayer();
            if (!event.isCancelled()) {  // 判断是否被取消
                String username = player.getDisplayName();  //获取用户名
                username = ChatColor.stripColor(username.substring(username.indexOf("]") + 1));  //裁剪
                String text = event.getMessage();  //获取消息
                String perm;
                if (player.hasPermission("chatresend.admin")) {  //判断权限组
                    perm = "ADMIN";
                } else {
                    perm = "USUAL";
                }

                //拼接params
                String params = "name=" + Libs.urlEncoder(username) +
                        "&perm=" + perm +
                        "&text=" + Libs.urlEncoder(text) +
                        "&server=" + Libs.urlEncoder(Config.server);

                (new BukkitRunnable() {
                    public void run() {
                            Libs.sendPost(params);  //发送消息
                    }
                }).runTaskAsynchronously(ChatForward.plugin);
            }
        }
    }
}
