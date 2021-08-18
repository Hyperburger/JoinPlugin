package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import me.hyperburger.joinplugin.utilis.Utilis;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Maintenance extends SubCommand {
    @Override
    public String getName() {
        return "Maintenance";
    }

    @Override
    public String getDescription() {
        return "Sets the server on maintenance mode.";
    }

    @Override
    public String getSyntax() {
        return "/jp maintenance";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        if (player.hasPermission("joinplugin.command.maintenance")) {
            if (!plugin.getConfig().getBoolean("General.Maintenance.Enabled")) {
                for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                    if (!allPlayers.hasPermission("joinplugin.maintenance") || !allPlayers.hasPermission("joinplugin.command.maintenance")) {
                        allPlayers.kickPlayer(Ucolor.colorize(plugin.getConfig().getString("General.Maintenance.Kick Message")));
                    }
                }
                plugin.getConfig().set("General.Maintenance.Enabled", true);
                plugin.saveConfig();
                Ucolor.sendMessage(player, plugin.getConfig().getString("General.Maintenance.Maintenance Enabled"));

            } else {
                plugin.getConfig().set("General.Maintenance.Enabled", false);
                plugin.saveConfig();
                Ucolor.sendMessage(player, plugin.getConfig().getString("General.Maintenance.Maintenance Disabled"));
            }
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.maintenance");
        }
    }
}
