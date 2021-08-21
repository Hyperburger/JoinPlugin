package me.hyperburger.joinplugin.utilis;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilis {

    /**
     * Executable by players or console.
     * [player] | [console]
     * @param command The command line that will be executed.
     * @param player The player that the command will be executed from.
     * Supports placeholders. %player% - player's name.
     */
    public static void configCommand (String command, Player player){
        if (command.startsWith("[player]")){
            Bukkit.dispatchCommand(player, command.replace("[player] ", "").replace("%player%", player.getName()));
        } else if (command.startsWith("[console]")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("[console] ", "").replace("%player%", player.getName()));
        } else if (command.startsWith("[op]")) {
            boolean op = player.isOp();
            try {
                if(!op) player.setOp(!op);
                Bukkit.dispatchCommand(player, command.replace("[op] ", "").replace("%player%", player.getName()));
                player.sendMessage(String.valueOf(player.isOp()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!op) player.setOp(op);
                player.sendMessage(String.valueOf(player.isOp()));
            }
        }
    }

    /**
     * Send a message to all online players.
     * @param s The message to send to all players.
     * Doesn't support placeholders atm.
     */
    public static void sendMessageToAllPlayers(String s){
        for (Player allPlayers : Bukkit.getOnlinePlayers()){
            Ucolor.sendMessage(allPlayers, s);
        }
    }

    /**
     * Log message, send to console.
     * @param className the class that is using this log message.
     * @param message The message the OP wants to send.
     */
    public static String logMessage(Class<?> className, String message){

        Logger logger =Logger.getLogger(className.getName());
        logger.setLevel(Level.WARNING);
        logger.warning(message);

         return message;
    }

    /**
     * A fast way to create an item.
     * @param material The command line that will be executed.
     * @param displayname The display name of the item.
     * @param lore The item's lore.
     */
    public static void createItem(Material material, String displayname, List<String> lore){

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(Ucolor.colorize(displayname));
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
    }

    /**
     * Spawn a firework (Hard Coded)
     * @param location The chosen location, usually a player.
     * @param amount starts from 0.
     */
    public static void spawnFireworks(Location location, int amount){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.AQUA).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }
}
