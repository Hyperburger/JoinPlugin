package me.hyperburger.joinplugin.commands.subcommands;

import featherpowders.ui.PlayerUI;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.menu.MenuCustomMessagesPlayers;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CustomMessages extends SubCommand {
    @Override
    public String getName() {
        return "custommessage";
    }

    @Override
    public String getDescription() {
        return "Open the custom message join and leave menu.";
    }

    @Override
    public String getSyntax() {
        return "/jp custommessage";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        if (player.hasPermission("joinplugin.command.custommessages")) {
            MenuCustomMessagesPlayers ui = new MenuCustomMessagesPlayers(player);
            PlayerUI.openUI(player, ui);
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.custommessages");
        }
    }
}
