package com.github.hank9999.ChatForward;

import com.github.hank9999.ChatForward.ChatForward;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ChatListen implements Listener {
    //监听用户名 和 用户消息内容
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
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
            try {
                text = URLEncoder.encode(text, "UTF-8");  //对内容进行url编码
            } catch (Exception e) {
                ChatForward.plugin.getLogger().warning(ChatColor.RED + "Error: UnsupportedEncodingException");
                text = "";
            }
            String params = "name=" + username + "&perm=" + perm + "&text=" + text;  //拼接params
            ChatForward.plugin.getServer().getScheduler().scheduleAsyncDelayedTask(ChatForward.plugin, new Runnable() {
                @Override
                public void run() {
                    try {
                        sendPost(params);  //发送消息
                    } catch (Exception e) {
                        ChatForward.plugin.getLogger().warning(ChatColor.RED + "Error: " + e);
                    }
                }
            }, 0L);
        }
    }

    private void sendPost(String urlParameters) throws Exception {

        String url = "http://127.0.0.1:8081/post";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "zh;cn;zh-CN;en-US,en;q=0.5");

        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        ChatForward.plugin.getLogger().info("ReSend Chat: " + urlParameters + " Server return: " + responseCode + " " + response.toString());
    }
}
