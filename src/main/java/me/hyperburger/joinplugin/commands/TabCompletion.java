package me.hyperburger.joinplugin.commands;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();

        Configuration config = JoinPlugin.getPlugin(JoinPlugin.class).getConfig();
        ConfigurationSection section = config.getConfigurationSection("Groups");

        List<String> groups = new ArrayList<>(section.getKeys(false));

        if (args.length == 1) {
            if (sender.hasPermission("joinplugin.view"))
                list.add("reload");
                list.add("joinactionbar");
                list.add("joinbossbar");
                list.add("joinmotd");
                list.add("jointitle");
                list.add("maintenance");
                list.add("menu");
                list.add("servermotd");
                list.add("setmaxplayers");
                list.add("permissions");
                list.add("whitelist");
                list.add("test");
            if (sender.hasPermission("joinplugin.command.custommessages")) {
                list.add("custommessage");
            }
        }
        if (args.length == 2) {
            if (args[0].startsWith("test")) {
                groups.forEach(group -> list.add(group.toString()));
            }
        }

        return args[args.length - 1].isEmpty() ? list : list.stream().filter(string -> string.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());

    }
}
