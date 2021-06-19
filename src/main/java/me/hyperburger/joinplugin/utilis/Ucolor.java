package me.hyperburger.joinplugin.utilis;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Ucolor {

    public static String colorize(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(Player player, String message){
        player.sendMessage(colorize(message));
    }

    public static void NOPERM (Player player, String permissionMissing){
        sendMessage(player, "&c&lNO PERMISSION&f:&7 - " + permissionMissing);
    }
}
