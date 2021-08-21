package me.hyperburger.joinplugin.events;

import featherpowders.ui.PlayerUI;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.JoinPluginHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatEventsHandler implements Listener {

    private final JoinPlugin plugin;
    public ChatEventsHandler(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        processEventAsync(event, player, message);
    }

    private void processEventAsync(AsyncPlayerChatEvent event, Player player, String message) {
        if (JoinPluginHelper.maxPlayers.containsKey(player.getUniqueId())) {
            event.setCancelled(true);

            Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(JoinPlugin.class), () -> {
                if (message.equalsIgnoreCase("cancel")) {
                    player.sendMessage("§cSet max player canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }

                double value;
                try {
                    value = Double.parseDouble(message);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cInvalid input, offer canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }

                int players = (int) Math.round(value);
                if (players < 1) {
                    player.sendMessage("§Invaild numbers, set max player canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }

                JoinPluginHelper.maxPlayers.get(player.getUniqueId());
                plugin.getConfig().set("MOTD.MaxPlayers.MaxPlayers", Integer.parseInt(String.valueOf(players)));
                plugin.saveConfig();
                player.sendMessage("§8[§5JoinPlugin§8]§d Max player set to " + players);
            });
        }
    }
}
