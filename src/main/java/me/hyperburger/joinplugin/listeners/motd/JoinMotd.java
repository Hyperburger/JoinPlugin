package me.hyperburger.joinplugin.listeners.motd;

import me.hyperburger.joinplugin.configs.MotdFile;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMotd implements Listener {

    @EventHandler
    public void onJoinMotd(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        FileConfiguration motdConfig = MotdFile.getFile();

        if (player.hasPlayedBefore()) {
            if (motdConfig.getBoolean("MOTD.JoinMOTD.Enabled")) {
                for (String joinMotdMessages : motdConfig.getStringList("MOTD.JoinMOTD.Message")) {

                    Ucolor.sendMessage(player, joinMotdMessages
                            .replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())));
                }
            }

        } else {
            if (motdConfig.getBoolean("JoinMOTD.FirstJoinMOTD.Enabled")) {
                for (String s : motdConfig.getStringList("JoinMOTD.FirstJoinMOTD.Message")) {

                    Ucolor.sendMessage(player, s
                            .replace("%player%", player.getName())
                            .replace("%playerdisplayname", player.getDisplayName()));
                }
            }
        }
    }
}
