package me.hyperburger.joinplugin.utilis;

import me.hyperburger.joinplugin.listeners.JoinListener;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilis {

    public static boolean FirstJoiner(Player player){
        return player.hasPlayedBefore();
    }

    public static void configCommand (String s, Player player){
        if (s.startsWith("[player]")){
            Bukkit.dispatchCommand(player, s.replace("[player] ", "").replace("%player%", player.getName()));
        } else if (s.startsWith("[console]")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("[console] ", "").replace("%player%", player.getName()));

        }
    }

    public static String logMessage(Class<?> className, String message){

        Logger logger =Logger.getLogger(className.getName());
        logger.setLevel(Level.WARNING);
        logger.warning(message);

         return message;
    }

    public static void createItem(Material material, String Displayname, List<String> lore){

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(Ucolor.colorize(Displayname));
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
    }

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
