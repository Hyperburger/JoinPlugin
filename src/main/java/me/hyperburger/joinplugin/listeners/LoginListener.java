package me.hyperburger.joinplugin.listeners;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    private final JoinPlugin plugin;
    public LoginListener(JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

            if (event.getPlayer().hasPermission("joinplugin.serverfull")){
                event.allow();
            } else {
                event.setResult(PlayerLoginEvent.Result.KICK_FULL);
                event.setKickMessage(Ucolor.colorize(plugin.getConfig().getString("MOTD.MaxPlayers.KickMessage")));
            }
        }
    }



}
