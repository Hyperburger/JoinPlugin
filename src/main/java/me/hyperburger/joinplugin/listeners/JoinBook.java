package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class JoinBook implements Listener {

    private final JoinPlugin plugin;

    public JoinBook(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String Path = plugin.getConfig().getString("First Join.Book");

        if (!player.hasPlayedBefore()){
            if (plugin.getConfig().getBoolean("First Join.Book.Enabled")){
                ItemStack book = new ItemStack(Material.valueOf(plugin.getConfig().getString("First Join.Book.Item")));
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                int bookSlot = plugin.getConfig().getInt("First Join.Book.Slot");

                bookMeta.setTitle(Ucolor.colorize(Path + ".Title"));
                bookMeta.setAuthor(Ucolor.colorize(Path + ".Author"));
                for (String msg : plugin.getConfig().getStringList(Path + ".Pages")){
                    bookMeta.setPages(msg.replace("%line%", "\n"));
                }

                player.getInventory().setItem(bookSlot, book);
            }
        }


    }

}
