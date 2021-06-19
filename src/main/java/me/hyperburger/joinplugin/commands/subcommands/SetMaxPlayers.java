package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetMaxPlayers extends SubCommand {


    @Override
    public String getName() {
        return "setmaxplayers";
    }

    @Override
    public String getDescription() {
        return "sets the max players on the server.";
    }

    @Override
    public String getSyntx() {
        return "/jp setmaxplayers number";
    }

    @Override
    public void perfrom(Player player, String[] args, Plugin plugin) {
        if (args.length > 1){

        }
    }
}
