package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Menu extends SubCommand {

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public String getDescription() {
        return "Opens the main menu of the plugin.";
    }

    @Override
    public String getSyntx() {
        return "/jp menu";
    }

    @Override
    public void perfrom(Player player, String[] args, Plugin plugin) {


    }
}
