package me.hyperburger.joinplugin.listeners.motd;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.configs.MotdFile;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerMOTD implements Listener {


    @EventHandler
    public void onPing(ServerListPingEvent event){
        if (MotdFile.getFile().getBoolean("MOTD.ServerMOTD.Enabled")) {
            if (!MotdFile.getFile().getBoolean("General.Maintenance.Enabled")) {
                event.setMotd(Ucolor.colorize(MotdFile.getFile().getString("MOTD.ServerMOTD.Line-1") + "\n" + MotdFile.getFile().getString("MOTD.ServerMOTD.Line-2")));
            }
        }

        event.setMaxPlayers(MotdFile.getFile().getBoolean("MOTD.MaxPlayers.Enabled") ? MotdFile.getFile().getInt("MOTD.MaxPlayers.MaxPlayers") : Bukkit.getOnlinePlayers().size() + 1);

    }

}
