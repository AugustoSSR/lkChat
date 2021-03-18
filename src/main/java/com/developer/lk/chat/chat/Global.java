package com.developer.lk.chat.chat;

import com.developer.lk.api.manager.SimplesClansAPI;
import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.chat.Main;
import com.developer.lk.rankup.type.RankUP;
import com.developer.lk.scoreboard.tablist.TabScore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Global implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;


        if (command.getName().equalsIgnoreCase("g")) {

            if (args.length == 0) {
                jogador.sendMessage("§4§lERRO: §7Digite /g ( mensagem )");
                return true;
            }

            String mensagem = "";
            for (final String part : args) {
                if (mensagem != null) {
                    mensagem = String.valueOf(mensagem) + " ";
                }
                mensagem = String.valueOf(mensagem) + part;
            }
            if (!jogador.hasPermission(PermissionAPI.PERMISSION_MEMBER)
                    && (Main.getInstance().getConfig().getString("Chat.Modo").equalsIgnoreCase("Desabilitado"))) {
                jogador.sendMessage("§cO chat esta desabilitado no momento.");
                return true;
            }
            final String prefix = TabScore.getPrefixFromPlayer(jogador);
            for (final Player todos : Bukkit.getOnlinePlayers()) {
                todos.sendMessage("§7[g] " + ChatColor.RESET + prefix + ChatColor.RESET + RankUP.getRank(jogador) + " " + SimplesClansAPI.getTag(jogador) + " §f" + jogador.getName() + "§7:§e" + mensagem);
            }
            return true;
        }
        return false;
    }
}
