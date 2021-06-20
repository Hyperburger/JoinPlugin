package me.hyperburger.joinplugin.commands;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.commands.subcommands.*;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {


    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    private JoinPlugin plugin;

    public CommandManager(JoinPlugin plugin){
        this.plugin = plugin;

        subCommands.add(new Help());
        subCommands.add(new ReloadCommand());
        subCommands.add(new ServerMotd());
        subCommands.add(new DisplayJoinMotd());
        subCommands.add(new WhiteList());
        subCommands.add(new Maintenance());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission("joinplugin.view")) {


            if (args.length > 0) {
                for (SubCommand count : subCommands) {
                    if (args[0].equalsIgnoreCase(count.getName())) {
                        count.perfrom(player, args, plugin);
                    }
                }
            } else {
                player.sendMessage(" ");
                player.sendMessage(Ucolor.colorize("    &5&m----------&d&l JOIN PLUGIN &fv" + JoinPlugin.version + " &5&m----------"));
                player.sendMessage(" ");
                for (int i = 0; i < getSubCommands().size(); i++) {
                    player.sendMessage(Ucolor.colorize(" &8[&3&l*&8] &f" + getSubCommands().get(i).getSyntx() + " &d- &7" + getSubCommands().get(i).getDescription()));
                }
                player.sendMessage(" ");
                player.sendMessage(Ucolor.colorize(" &8[&3&l?&8] &7Author: HyperBurger"));
                player.sendMessage(" ");
                player.sendMessage(Ucolor.colorize("    &5&m----------&d&l  &5&m----------&f  &5&m----------"));
                player.sendMessage(" ");
                return true;
                }
            return true;
            } else {
                Ucolor.sendMessage(player, "&d&lJOIN PLUGIN &f&oBy HyperBurger");
                return true;
            }
        }

        return true;
    }


    public ArrayList<SubCommand> getSubCommands (){
        return subCommands;
    }
}
