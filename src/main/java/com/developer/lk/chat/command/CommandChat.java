package com.developer.lk.chat.command;

import com.developer.lk.api.manager.TittleAPI;
import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.chat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;

        if (command.getName().equalsIgnoreCase("chat")) {
            if (!jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                MessegeAPI.send(jogador, MessegeAPI.COMMAND_NOT_PERMISSION);
                return true;
            }

            if (Main.getInstance().getConfig().getString("Chat.Modo").equalsIgnoreCase("Habilitado")) {
                Main.getInstance().getConfig().set("Chat.Modo", "Desabilitado");
                TittleAPI.sendTitle(jogador, "§cChat", "§7O chat foi desativado por um administrador.", 40);
                jogador.sendMessage("§cChat desativado.");
            } else {
                Main.getInstance().getConfig().set("Chat.Modo", "Habilitado");
                jogador.sendMessage("§aChat ativado.");
                TittleAPI.sendTitle(jogador, "§cChat", "§7O chat foi ativado por um administrador.", 40);
            }

        }
        return false;
    }
}
