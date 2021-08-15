package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import me.hyperburger.joinplugin.utilis.Utilis;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final JoinPlugin plugin;
    public JoinListener(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Configuration config = plugin.getConfig();
        ConfigurationSection rewardSection = config.getConfigurationSection("Groups");

        if (!player.hasPlayedBefore()) return;
        if (!config.getBoolean("Enable Join Message By Groups")) return;
        if (rewardSection == null) return;

        for (String key : rewardSection.getKeys(false)) {

            ConfigurationSection idSection = rewardSection.getConfigurationSection(key);     /* We now have idSection and can access it. */
            String permission = idSection.getString("permission");

            if (permission != null && player.hasPermission((permission))) {

                // EssentialsX Vanish Support
                if (config.getBoolean("SupportEssentialXVanish") && plugin.checkEssentials()) {
                    if (JoinPlugin.essentials.getUser(player.getUniqueId()).isVanished()){
                        event.setJoinMessage("");

                    } else {
                        setCustomJoinMessage(event, player, idSection); // Perform this if essentials is enabled.
                    }
                }

                setCustomJoinMessage(event, player, idSection);  // Perform this if essentials is disabled.

                // Perform Sounds
                for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                    try {
                        if (!idSection.getString("Sound").equalsIgnoreCase("")) {
                            allPlayers.playSound(player.getLocation(), Sound.valueOf(idSection.getString("Sound")), 1, 1);
                        }
                    } catch (IllegalArgumentException e) {
                        Utilis.logMessage(this.getClass(), "[JoinPlugin] The sound " + idSection.getString("Sound") + " doesn't exist in your server version!");
                    }
                }

                // Perform Fireworks
                if (idSection.getBoolean("Firework")) {
                    Utilis.spawnFireworks(player.getLocation(), 1);
                }

                // Perform Commands
                for (String s : idSection.getStringList("commands")) {
                    Utilis.configCommand(s, player);
                }
            }
        }

        // Perform Titles
        if (!JoinPlugin.mc18()) {       //TODO: Titles for 1.8
            if (plugin.getConfig().getBoolean("Join Title.Enabled")) {
                player.sendTitle(config.getString("Join Title.Title"),
                        config.getString("Join Title.SubTitle"),
                        config.getInt("Join Title.fadeIn"),
                        config.getInt("Join Title.Stay"),
                        config.getInt("Join Title.fadeOut"));
            }
        }
    }

    /**
     * Set the join message to a custom one and support Hex Colors and Placeholders.
     * Use in this class only to avoid confusion.
     *
     * @param event     The player join event.
     * @param player    The player to send the message to.
     * @param idSection The Configuration Section of the config.yml I.G: Groups:
     */
    private void setCustomJoinMessage(PlayerJoinEvent event, Player player, ConfigurationSection idSection) {

        event.setJoinMessage(Ucolor.translateColorCodes(String.valueOf(idSection.getString("Join Message"))
                .replace("%player%", player.getName()
                .replace("%playerdisplayname%", player.getDisplayName()))));

    }
}


