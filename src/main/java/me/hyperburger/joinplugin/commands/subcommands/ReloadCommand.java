package me.hyperburger.joinplugin.commands.subcommands;

import com.cryptomorin.xseries.messages.Titles;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ReloadCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config file.";
    }

    @Override
    public String getSyntax() {
        return "/jp reload";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        if (player.hasPermission("joinplugin.command.reload")) {
            Ucolor.sendMessage(player, "&a&lConfig Successfully Reloaded!");
            plugin.reloadConfig();
            Titles.sendTitle(player, 20, 2 * 20, 20, Ucolor.colorize("&a&lConfig"), Ucolor.colorize("&a&lSuccessfully Reloaded!"));
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.reload");
        }
    }
}
