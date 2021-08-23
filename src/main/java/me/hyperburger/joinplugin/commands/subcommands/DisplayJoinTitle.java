package me.hyperburger.joinplugin.commands.subcommands;

import com.cryptomorin.xseries.messages.Titles;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisplayJoinTitle extends SubCommand {

    @Override
    public String getName() { return "jointitle"; }

    @Override
    public String getDescription() { return "Displays the currently set join Title."; }

    @Override
    public String getSyntax() { return "/jp jointitle"; }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        Configuration config = plugin.getConfig();
        if (player.hasPermission("joinplugin.command.displayjointitle")) {
            Titles.sendTitle(player,
                    config.getInt("Join Title.fadeIn") * 20,
                    config.getInt("Join Title.Stay") * 20,
                    config.getInt("Join Title.fadeOut") * 20,
                    Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join Title.Title")))),
                    Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join Title.SubTitle")))));
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.displayjointitle");
        }
    }
}
