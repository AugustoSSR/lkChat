package com.developer.lk.chat;

import com.developer.lk.api.server.ServerAPI;
import com.developer.lk.chat.chat.Global;
import com.developer.lk.chat.chat.Local;
import com.developer.lk.chat.chat.Staff;
import com.developer.lk.chat.command.CommandChat;
import com.developer.lk.chat.command.CommandTell;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

    @Override
    public void onEnable() {

        ServerAPI.getConsole().sendMessage("");
        ServerAPI.getConsole().sendMessage("    §a§l[lkChat] §f Plugin sendo inicializado.");
        ServerAPI.getConsole().sendMessage("    §a§l[lkChat] §f Versão " + getServer().getBukkitVersion());
        ServerAPI.getConsole().sendMessage("");
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new Local(), this);
        getCommand("g").setExecutor(new Global());
        getCommand("s").setExecutor(new Staff());
        getCommand("chat").setExecutor(new CommandChat());
        getCommand("tell").setExecutor(new CommandTell());

    }

    @Override
    public void onDisable() {
        ServerAPI.getConsole().sendMessage("");
        ServerAPI.getConsole().sendMessage("    §a§l[lkChat] §f Plugin sendo finalizado.");
        ServerAPI.getConsole().sendMessage("    §a§l[lkChat] §f Versão " + getServer().getBukkitVersion());
        ServerAPI.getConsole().sendMessage("");
        saveConfig();
    }
}
