package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final JoinPlugin plugin;

    public QuitListener(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        Configuration config = plugin.getConfig();
        ConfigurationSection rewardSection = config.getConfigurationSection("Groups");

        if (!config.getBoolean("Enable Join Message By Groups")) return;
        if (rewardSection == null) return;

        for (String key : rewardSection.getKeys(false)) {

            ConfigurationSection idSection = rewardSection.getConfigurationSection(key);     /* We now have idSection and can access it. */
            String permission = idSection.getString("permission");

            if (permission != null && player.hasPermission((permission))) {

                if (config.getBoolean("SupportEssentialXVanish") && plugin.checkEssentials()) {
                    if (JoinPlugin.essentials.getUser(player.getUniqueId()).isVanished()) {
                        event.setQuitMessage("");

                    } else {
                        setCustomQuitMessage(event, player, idSection); // Perform this if essentials is enabled.
                    }
                }

                setCustomQuitMessage(event, player, idSection);  // Perform this if essentials is disabled.

            }
        }
    }

    /**
     * Set the quit message to a custom one and support Hex Colors and Placeholders.
     * Use in this class only to avoid confusion.
     *
     * @param event     The player join event.
     * @param player    The player to send the message to.
     * @param idSection The Configuration Section of the config.yml I.G: Groups:
     */
    private void setCustomQuitMessage(PlayerQuitEvent event, Player player, ConfigurationSection idSection) {

        event.setQuitMessage(Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(idSection.getString("Quit Message"))
                .replace("%player%", player.getName()
                .replace("%playerdisplayname%", player.getDisplayName())))));

    }
}



