package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Help extends SubCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays the help menu.";
    }

    @Override
    public String getSyntax() {
        return "/jp help";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        player.performCommand("jp");
    }
}
