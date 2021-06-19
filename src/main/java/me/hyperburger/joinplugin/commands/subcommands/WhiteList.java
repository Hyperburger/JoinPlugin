package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WhiteList extends SubCommand {


    @Override
    public String getName() {
        return "whitelist";
    }

    @Override
    public String getDescription() {
        return "Toggles the server's whitelist.";
    }

    @Override
    public String getSyntx() {
        return "/jp whitelist";
    }

    @Override
    public void perfrom(Player player, String[] args, Plugin plugin) {
        if (player.hasPermission("joinplugin.command.whitelist")) {
            if (!plugin.getServer().hasWhitelist()) {
                plugin.getServer().setWhitelist(true);
                Ucolor.sendMessage(player, "&d&oServer has been whitelisted!");
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (!player1.hasPermission("joinplugin.command.whitelist")) {
                        player1.kickPlayer(Ucolor.colorize(plugin.getConfig().getString("General.Whitelist.Kick Message")));
                    }
                }
            } else {
                plugin.getServer().setWhitelist(false);
                Ucolor.sendMessage(player, "&d&oWhiltelist has turned off!");

            }
            } else{
            Ucolor.NOPERM(player, "joinplugin.command.whitelist");
        }
    }
}
