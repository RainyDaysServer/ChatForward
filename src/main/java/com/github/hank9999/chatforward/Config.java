package com.github.hank9999.chatforward;

public class Config {
    public static Boolean enable = true;
    public static String url = "http://127.0.0.1:8081/post";
    public static String server = "XXX";

    public static void loadConfig() {
        ChatForward.plugin.saveDefaultConfig();
        ChatForward.plugin.reloadConfig();

        setValue();
    }

    public static void reloadConfig() {
        ChatForward.plugin.saveDefaultConfig();
        ChatForward.plugin.reloadConfig();

        setValue();
    }

    public static void saveConfig() {
        ChatForward.plugin.saveConfig();
        reloadConfig();

        setValue();
    }

    public static void setConfig(String path, Object value) {
        ChatForward.plugin.getConfig().set(path, value);
    }

    public static void setValue() {
        enable = ChatForward.plugin.getConfig().getBoolean("enable");
        url = ChatForward.plugin.getConfig().getString("url");
        server = ChatForward.plugin.getConfig().getString("server");

        if (url == null) {
            url = "http://127.0.0.1:8081/post";
        }

        if (server == null) {
            server = "XXX";
        }
    }
}
