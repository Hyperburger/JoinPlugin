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

    public JoinPlugin plugin;
    public JoinListener(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Configuration config = plugin.getConfig();
        ConfigurationSection rewardSection = config.getConfigurationSection("Groups");

        if (player.hasPlayedBefore()) {
            if (config.getBoolean("Enable Join Message By Groups")) {           /* Group Section Enabled/Disabled */
                if (rewardSection != null) {                                           /* Checking that the section actually exists and isn't null. */
                    for (String key : rewardSection.getKeys(false)) {           /* Each Key = one id. */
                        ConfigurationSection idSection = rewardSection.getConfigurationSection(key);     /* We now have idSection and can access it. */
                        String permission = idSection.getString("permission");                    /* Getting the permission from the player/group. */
                        if (player.hasPermission(String.valueOf(permission))) {
                            if (config.getBoolean("SupportEssentialXVanish")) {                     /* Essentials support. */
                                if (plugin.checkEssentials()) {                                            /* If essentials actually exists. */
                                    if (JoinPlugin.essentials.getUser(player.getUniqueId()).isVanished()) {         /* Checking the player is actually vanished. */
                                        event.setJoinMessage("");                                                   /* Setting the message to null/nothing. */
                                    } else {
                                        event.setJoinMessage(Ucolor.colorize(idSection.getString("Join Message"))
                                                .replace("%player%", player.getName()
                                                        .replace("%playerdisplayname%", player.getDisplayName())));
                                    }
                                }
                            } else {
                                event.setJoinMessage(Ucolor.colorize(idSection.getString("Join Message"))
                                        .replace("%player%", player.getName()                           /* If essentials doesn't work send this instead. */
                                        .replace("%playerdisplayname%", player.getDisplayName())));
                            }

                            // Sound: "" | Section
                            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                                try {
                                    if (!idSection.getString("Sound").equalsIgnoreCase("")) {
                                        allPlayers.playSound(player.getLocation(), Sound.valueOf(idSection.getString("Sound")), 1, 1); // Sound
                                    }
                                } catch (IllegalArgumentException e) {
                                    Utilis.logMessage(this.getClass(), "[JoinPlugin] The sound " + idSection.getString("Sound") + " doesn't exist in your server version!");
                                }
                            }

                            // Firework: "" | Section
                            if (idSection.getBoolean("Firework")) {
                                Utilis.spawnFireworks(player.getLocation(), 1);
                            }

                            // Commands: "" | Section
                            for (String s : idSection.getStringList("commands")) {
                                Utilis.configCommand(s, player);
                            }
                        }
                    }

                    // TODO: Make titles for 1.8
                    if (!JoinPlugin.mc18()) {
                        if (plugin.getConfig().getBoolean("Join Title.Enabled")) {
                            player.sendTitle(config.getString("Join Title.Title"),
                                    config.getString("Join Title.SubTitle"),
                                    config.getInt("Join Title.fadeIn"),
                                    config.getInt("Join Title.Stay"),
                                    config.getInt("Join Title.fadeOut"));
                        }
                    }
                }

                /*
                When the player first joins.
                Featuring all the stuff that normal join has.
                 */
            } else {
                event.setJoinMessage(Ucolor.colorize(config.getString("First Join.Message"))
                        .replace("%player%", player.getName()
                                .replace("%playerdisplayname%", player.getDisplayName())));

                for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                    try {
                        allPlayers.playSound(player.getLocation(), Sound.valueOf(config.getString("Sound")), 1, 1); // Sound
                    } catch (IllegalArgumentException e) {
                        Utilis.logMessage(this.getClass(), "[JoinPlugin] The sound " + config.getString("Sound") + " doesn't exist in your server version!"); }
                }
                // Firework
                if (config.getBoolean("First Join.Firework.Enabled")) {
                    Utilis.spawnFireworks(player.getLocation(), 1);
                }

                for (String commands : config.getStringList("First Join.Commands")) {
                    Utilis.configCommand(commands, player);
                }
            }
        }
    }
}
