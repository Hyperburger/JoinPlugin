package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class MaintenanceListener implements Listener {

    private JoinPlugin plugin;
    public MaintenanceListener(JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(ServerListPingEvent event){
        if (plugin.getConfig().getBoolean("General.Maintenance.Enabled")){
            event.setMotd(Ucolor.colorize(
                    plugin.getConfig().getString("General.Maintenance.MOTD.Line-1")
                            + "\n" +
                     plugin.getConfig().getString("General.Maintenance.MOTD.Line-2")));
        }
    }

    @EventHandler
    public void onMaintenance(PlayerLoginEvent event) {
        if (plugin.getConfig().getBoolean("General.Maintenance.Enabled")) {
            if (!event.getPlayer().hasPermission("joinplugin.maintenance")) {
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(Ucolor.colorize(plugin.getConfig().getString("General.Maintenance.Maintenance Enabled")));
                event.getPlayer().kickPlayer(Ucolor.colorize(plugin.getConfig().getString("General.Maintenance.Maintenance Enabled")));
            }
        }
    }
}
