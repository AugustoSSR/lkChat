package com.developer.lk.chat.chat;

import com.developer.lk.api.manager.SimplesClansAPI;
import com.developer.lk.api.manager.TittleAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.chat.Main;
import com.developer.lk.rankup.type.RankUP;
import com.developer.lk.scoreboard.tablist.TabScore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Local implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void aoEnviar (final AsyncPlayerChatEvent event) {

        final Player jogador = event.getPlayer();
        final int range = 25;
        final String prefix = TabScore.getPrefixFromPlayer(jogador);
        if (!jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR) && (Main.getInstance().getConfig().getString("Chat.Modo").equalsIgnoreCase("Desabilitado"))) {
            jogador.sendMessage("§cO chat esta desabilitado no momento.");
            TittleAPI.sendTitle(jogador, "§cChat", "§7O chat foi desativado por um administrador.", 40);
            event.setCancelled(true);
            return;
        }

        if (event.isCancelled()) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(true);

        String mensagem = event.getMessage();
        if (jogador.hasPermission(PermissionAPI.PERMISSION_BASIC)) {
            mensagem = event.getMessage().replace("&0", "§0").replace("&1", "§1").replace("&2", "§2").replace("&3", "§3").replace("&4", "§4").replace("&5", "§5").replace("&6", "§6").replace("&7", "§7").replace("&8", "§8").replace("&9", "§9").replace("&a", "§a").replace("&b", "§b").replace("&c", "§c").replace("&d", "§d").replace("&e", "§e").replace("&f", "§f");
        }

        final String chatClan = "§e[l] " + ChatColor.RESET + prefix + ChatColor.RESET + RankUP.getRank(jogador) + " " + SimplesClansAPI.getTag(jogador) + " §f" + jogador.getName() + "§7: §e" + mensagem;
        final String ninguemPerto = "§cNão tem ninguem proximo de você =(";
        if (Bukkit.getOnlinePlayers().size() == 1) {
            jogador.sendMessage(ninguemPerto);
            jogador.sendMessage(chatClan);
            return;
        }

        jogador.sendMessage(chatClan);
        String s = "S";

        for (final Player todos : Bukkit.getOnlinePlayers()) {
            if (todos.getWorld().getName().equalsIgnoreCase(jogador.getWorld().getName()) && todos.getLocation().distance(jogador.getLocation()) <= range && !todos.getName().equalsIgnoreCase(jogador.getName())) {
                todos.sendMessage(chatClan);
                if (!s.equalsIgnoreCase("S")) {
                    continue;
                }
                s = "N";
            }
        }

        if (s.equalsIgnoreCase("S")) {
            jogador.sendMessage(ninguemPerto);
        }
    }

}
