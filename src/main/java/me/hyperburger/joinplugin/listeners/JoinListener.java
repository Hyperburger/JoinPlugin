package me.hyperburger.joinplugin.listeners;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.EssentialsEntityListener;
import com.earth2me.essentials.User;
import com.earth2me.essentials.commands.Commandvanish;
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
import org.bukkit.permissions.Permission;

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

                        String permission = idSection.getString("permission");

                        if (player.hasPermission(String.valueOf(permission))) {
                            // Sending the join message & Sounds
                            if (config.getBoolean("SupportEssentialXVanish")){ // Checking if essentials support is enabled
                                if (plugin.checkEssentials()) { // if essentials exists
                                    if (JoinPlugin.essentials.getUser(player.getUniqueId()).isVanished()) { // Checking if the user is vanished
                                        event.setJoinMessage(""); // Setting the message to nothing
                                    } else {
                                        event.setJoinMessage(Ucolor.colorize(idSection.getString("Join Message")).replace("%player%", player.getName().replace("%playerdisplayname%", player.getDisplayName()))); // Sending message if support enabled
                                    }
                                }
                            } else {
                                event.setJoinMessage(Ucolor.colorize(idSection.getString("Join Message")).replace("%player%", player.getName().replace("%playerdisplayname%", player.getDisplayName()))); // Sending message if support isn't enabled
                            }
                            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                                    allPlayers.playSound(player.getLocation(), Sound.valueOf(idSection.getString("Sound")), 1, 1); // Sound
                            }

                            if (idSection.getBoolean("Firework")){
                                Utilis.spawnFireworks(player.getLocation(), 1);
                                    // Spawning a firework if enabled.
                            }

                            for (String s : idSection.getStringList("commands")) {
                                Utilis.configCommand(s, player);
                                // commands: "" | Section
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
        } else {
            event.setJoinMessage(Ucolor.colorize(config.getString("First Join.Message")).
                    replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())));
            System.out.println(player.getName() + " has joined for the first time!");
            for (Player allPlayers : Bukkit.getOnlinePlayers()){
                allPlayers.playSound(player.getLocation(), Sound.valueOf(config.getString("First Join.Sound")), 1, 1); // Sound
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
