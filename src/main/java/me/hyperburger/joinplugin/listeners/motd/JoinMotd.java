package me.hyperburger.joinplugin.listeners.motd;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMotd implements Listener {


    private final JoinPlugin plugin;
    public JoinMotd(JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinMotd(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            if (plugin.getConfig().getBoolean("MOTD.JoinMOTD.Enabled")) {
                for (String joinMotdMessages : plugin.getConfig().getStringList("MOTD.JoinMOTD.Message")) {

                    Ucolor.sendMessage(player, joinMotdMessages
                            .replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())));
                }
            }

        } else {
            if (plugin.getConfig().getBoolean("JoinMOTD.FirstJoinMOTD.Enabled")) {
                for (String s : plugin.getConfig().getStringList("JoinMOTD.FirstJoinMOTD.Message")) {

                    Ucolor.sendMessage(player, s
                            .replace("%player%", player.getName())
                            .replace("%playerdisplayname", player.getDisplayName()));
                }
            }
        }
    }
}
