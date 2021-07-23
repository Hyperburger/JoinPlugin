package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private JoinPlugin plugin;

    public QuitListener(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        Configuration config = plugin.getConfig();
        ConfigurationSection rewardSection = config.getConfigurationSection("Groups");

        if (config.getBoolean("Enable Join Message By Groups")) {

            if (rewardSection != null) {

                for (String key : rewardSection.getKeys(false)) {

                    ConfigurationSection idSection = rewardSection.getConfigurationSection(key);

                    String permission = idSection.getString("permission");

                    if (player.hasPermission(permission)) {

                        event.setQuitMessage(Ucolor.colorize(idSection.getString("Quit Message").replace("%player%", player.getName().replace("%playerdisplayname", player.getDisplayName()))));

                        for (String s : idSection.getStringList("commands")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", player.getName()));
                        }
                    }
                }
            }
        }
    }
}


