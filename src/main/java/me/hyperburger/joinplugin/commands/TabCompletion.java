package me.hyperburger.joinplugin.commands;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {

    private final List<String> tabList = new ArrayList<>();
    private final JoinPlugin plugin;
    private final CommandManager commandManager;

    public TabCompletion(JoinPlugin plugin, CommandManager commandManager){
        this.plugin = plugin;
        this.commandManager = commandManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1){
            if (sender.hasPermission("joinplugin.view")){
               for (int i = 0; i < commandManager.getSubCommands().size(); i++){
                   tabList.add(commandManager.getSubCommands().get(i).getName());
               }
               return tabList;
            }
        }
        return null;
    }


}
