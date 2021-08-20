package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import me.hyperburger.joinplugin.utilis.Utilis;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisplayJoinBossBar extends SubCommand {

    @Override
    public String getName() { return "joinbossbar"; }

    @Override
    public String getDescription() { return "Displays the currently set join ActionBar."; }

    @Override
    public String getSyntax() { return "/jp joinbossbar"; }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        Configuration config = plugin.getConfig();

        if (player.hasPermission("joinplugin.command.displayjoinbossbar")) {
            Utilis.sendBossbar(player,
                    Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join BossBar.Message")))),
                    config.getInt("Join BossBar.Duration"),
                    config.getString("Join BossBar.Style"),
                    config.getString("Join BossBar.Color"));
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.displayjoinbossbar");
        }
    }
}
