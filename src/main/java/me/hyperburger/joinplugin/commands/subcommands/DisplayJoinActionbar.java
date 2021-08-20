package me.hyperburger.joinplugin.commands.subcommands;

import com.cryptomorin.xseries.messages.ActionBar;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisplayJoinActionbar extends SubCommand {

    private final JoinPlugin joinplugin;
    public DisplayJoinActionbar(JoinPlugin joinplugin) {
        this.joinplugin = joinplugin;
    }

    @Override
    public String getName() { return "joinactionbar"; }

    @Override
    public String getDescription() { return "Displays the currently set join ActionBar."; }

    @Override
    public String getSyntax() { return "/jp joinactionbar"; }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        Configuration config = plugin.getConfig();

        if (player.hasPermission("joinplugin.command.displayjoinactionbar")) {
            ActionBar.sendActionBar(joinplugin, player, Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join ActionBar.Message"))
                    .replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())))), config.getLong("Join ActionBar.Duration") * 20L);
        } else {
            Ucolor.NOPERM(player, "joinplugin.command.displayjoinactionbar");
        }
    }
}
