package com.github.hank9999.chatforward;

import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Libs
{
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendPost(String urlParameters) {

        try {
            String url = Config.url;
            if (url == null) {
                return;
            }
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String urlEncoder(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
