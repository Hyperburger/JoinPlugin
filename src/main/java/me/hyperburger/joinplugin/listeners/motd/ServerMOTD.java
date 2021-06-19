package me.hyperburger.joinplugin.listeners.motd;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerMOTD implements Listener {

    private JoinPlugin plugin;
    public ServerMOTD(JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(ServerListPingEvent event){
        if (plugin.getConfig().getBoolean("MOTD.ServerMOTD.Enabled")) {
            if (!plugin.getConfig().getBoolean("General.Maintenance.Enabled")) {
                event.setMotd(Ucolor.colorize(plugin.getConfig().getString("MOTD.ServerMOTD.Line-1") + "\n" + plugin.getConfig().getString("MOTD.ServerMOTD.Line-2")));
            }
        }

    }

}
