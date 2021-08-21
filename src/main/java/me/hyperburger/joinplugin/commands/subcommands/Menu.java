package me.hyperburger.joinplugin.commands.subcommands;

import featherpowders.ui.PlayerUI;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.menu.MenuGUI;
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
    public String getSyntax() {
        return "/jp menu";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        MenuGUI ui = new MenuGUI(player, 0);
        PlayerUI.openUI(player, ui);
    }
}
