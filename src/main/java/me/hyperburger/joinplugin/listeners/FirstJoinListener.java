package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import me.hyperburger.joinplugin.utilis.Utilis;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinListener implements Listener {

    private final JoinPlugin plugin;
    public FirstJoinListener (JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Configuration config = plugin.getConfig();

        if (!player.hasPlayedBefore()) {

            event.setJoinMessage(Ucolor.colorize(config.getString("First Join.Message"))
                    .replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())));

            // Perform Firework
            if (config.getBoolean("First Join.Firework.Enabled")) {
                Utilis.spawnFireworks(player.getLocation(), 1);
            }

            // Perform Commands
            for (String commands : config.getStringList("First Join.Commands")) {
                Utilis.configCommand(commands, player);
            }
        }
    }
}
