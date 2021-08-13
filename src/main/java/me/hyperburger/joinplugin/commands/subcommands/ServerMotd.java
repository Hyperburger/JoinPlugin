package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ServerMotd extends SubCommand {

    @Override
    public String getName() {
        return "servermotd";
    }

    @Override
    public String getDescription() {
        return "Displays the currently set server motd.";
    }

    @Override
    public String getSyntax() {
        return "/jp servermotd";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        if (player.hasPermission("joinplugin.command.displayservermotd")) {
            player.sendMessage(Ucolor.colorize(
                    plugin.getConfig().getString("MOTD.ServerMOTD.Line-1") + "\n" +
                            plugin.getConfig().getString("MOTD.ServerMOTD.Line-2")));
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.displayservermotd");
        }
    }
}
