package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

public class SetMaxPlayers extends SubCommand {


    @Override
    public String getName() {
        return "setmaxplayers";
    }

    @Override
    public String getDescription() {
        return "Sets the max players.";
    }

    @Override
    public String getSyntx() {
        return "/jp setmaxplayers <number>";
    }

    @Override
    public void perfrom(Player player, String[] args, Plugin plugin) {
        if (args.length > 1){

            if (!player.hasPermission("joinplugin.command.maxplayers")) return;

            plugin.getConfig().set("MOTD.MaxPlayers.MaxPlayers", Integer.parseInt(args[1]));
            plugin.saveConfig();
            Ucolor.sendMessage(player, "&d&lJOIN PLUGIN &fThe max players have been set to &d&n&o" + args[1]);

        }
    }
}
