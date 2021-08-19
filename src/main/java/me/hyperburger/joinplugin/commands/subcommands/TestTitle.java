package me.hyperburger.joinplugin.commands.subcommands;

import com.cryptomorin.xseries.messages.Titles;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TestTitle extends SubCommand {

    @Override
    public String getName() { return "testtitle"; }

    @Override
    public String getDescription() { return "Return test title."; }

    @Override
    public String getSyntax() { return "/jp testtitle"; }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        Configuration config = plugin.getConfig();

        Titles.sendTitle(player,
                config.getInt("Join Title.fadeIn") * 20,
                config.getInt("Join Title.Stay") * 20,
                config.getInt("Join Title,fadeOut") * 20,
                Ucolor.translateColorCodes(String.valueOf(config.getString("Join Title.Title"))),
                Ucolor.translateColorCodes(String.valueOf(config.getString("Join Title.SubTitle"))));
    }
}
