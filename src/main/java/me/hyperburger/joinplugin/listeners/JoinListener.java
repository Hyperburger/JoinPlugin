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
    public JoinListener(JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Configuration config = plugin.getConfig();
        ConfigurationSection rewardSection = config.getConfigurationSection("Groups");

        if (player.hasPlayedBefore()) {
            if (config.getBoolean("Enable Join Message By Groups")) {
                // If group section is enabled in the config

                if (rewardSection != null) {
                    // We have a reward section

                    for (String key : rewardSection.getKeys(false)) {
                        // Each key = one id

                        ConfigurationSection idSection = rewardSection.getConfigurationSection(key);
                        // We have idSection

                        String permission = idSection.getString("permission");  // Permission

                        if (player.hasPermission(String.valueOf(permission))) {

                            // Essentials Section
                            if (config.getBoolean("SupportEssentialXVanish")){
                                if (plugin.checkEssentials()) { // If essentials exists
                                    if (JoinPlugin.essentials.getUser(player.getUniqueId()).isVanished()) { // If vanish is enabled
                                        event.setJoinMessage(""); // Setting the message to nothing
                                    } else {
                                        event.setJoinMessage(Ucolor.colorize(idSection.getString("Join Message"))
                                                .replace("%player%", player.getName()
                                                .replace("%playerdisplayname%", player.getDisplayName())));
                                    }
                                }
                            } else {
                                event.setJoinMessage(Ucolor.colorize(idSection.getString("Join Message")).replace("%player%", player.getName().replace("%playerdisplayname%", player.getDisplayName()))); // Sending message if support isn't enabled
                            }

                            // Sound: "" | Section
                            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                                try {
                                    allPlayers.playSound(player.getLocation(), Sound.valueOf(idSection.getString("Sound")), 1, 1); // Sound
                                }catch (IllegalArgumentException e) {
                                    Utilis.logMessage(this.getClass(),"[JoinPlugin] The sound " +idSection.getString("Sound") + " doesn't exist in your server version!");
                                    }
                            }

                            // Firework: "" | Section
                            if (idSection.getBoolean("Firework")){
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
                    } else {
                        System.out.println("[JoinPlugin] -> cant send title! Minecraft Server Version: 1.8");
                        System.out.println("[JoinPlugin] -> Only works on 1.9+");
                    }
                }
            }

            /*
            First Join Section
            This needs to be transferred to a new class.
            To make the code as clean and organized as possible.
             */

        } else {
            event.setJoinMessage(Ucolor.colorize(config.getString("First Join.Message")).
                    replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())));
            System.out.println(player.getName() + " has joined for the first time!");
            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                try {
                    allPlayers.playSound(player.getLocation(), Sound.valueOf(config.getString("Sound")), 1, 1); // Sound
                }catch (IllegalArgumentException e) {
                    Utilis.logMessage(this.getClass(),"[JoinPlugin] The sound " + config.getString("Sound") + " doesn't exist in your server version!");
                }
            }
            if (config.getBoolean("First Join.Firework.Enabled")){
                Utilis.spawnFireworks(player.getLocation(), 1);
            }
            for (String commands : config.getStringList("First Join.Commands")){
                Utilis.configCommand(commands, player);
            }
        }
    }
}
