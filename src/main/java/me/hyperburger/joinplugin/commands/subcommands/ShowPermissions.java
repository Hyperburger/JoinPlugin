package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ShowPermissions extends SubCommand {


    @Override
    public String getName() {
        return "permissions";
    }

    @Override
    public String getDescription() {
        return "Shows all the plugin permissions.";
    }

    @Override
    public String getSyntax() {
        return "/jp permissions";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        List<String> permissions = new ArrayList<>();

        permissions.add("joinplugin.view");
        permissions.add("joinplugin.command.whitelist");
        permissions.add("joinplugin.command.maxplayers");
        permissions.add("joinplugin.command.displayservermotd");
        permissions.add("joinplugin.command.maintenance");
        permissions.add("joinplugin.command.permissions");
        permissions.add("joinplugin.command.testgroup");
        permissions.add("joinplugin.command.reload");
        permissions.add("joinplugin.serverfull");

        player.sendMessage(" ");
        player.sendMessage(Ucolor.colorize("    &5&m--------&d&l JOIN PLUGIN &fPermissions &5&m--------"));
        player.sendMessage(" ");

        for (String s : permissions){
            Ucolor.sendMessage(player, "&8[&3&l*&8] &f" + s.toLowerCase());
        }

        player.sendMessage(" ");
        player.sendMessage(Ucolor.colorize(" &8[&3&l?&8] &7Author: HyperBurger"));
        player.sendMessage(" ");
        player.sendMessage(Ucolor.colorize("    &5&m------------&d&l  &5&m-----------&f  &5&m------------"));
        player.sendMessage(" ");


    }
}
