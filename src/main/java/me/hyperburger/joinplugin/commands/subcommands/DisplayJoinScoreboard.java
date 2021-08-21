package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisplayJoinScoreboard extends SubCommand {

    @Override
    public String getName() { return "joinbossbar"; }

    @Override
    public String getDescription() { return "Displays the currently set join ActionBar."; }

    @Override
    public String getSyntax() { return "/jp joinbossbar"; }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {

    }
}
