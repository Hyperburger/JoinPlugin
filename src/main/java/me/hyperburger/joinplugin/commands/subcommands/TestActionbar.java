package me.hyperburger.joinplugin.commands.subcommands;

import com.cryptomorin.xseries.messages.ActionBar;
import com.cryptomorin.xseries.messages.Titles;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TestActionbar extends SubCommand {

    private final JoinPlugin joinplugin;
    public TestActionbar(JoinPlugin joinplugin) {
        this.joinplugin = joinplugin;
    }

    @Override
    public String getName() { return "testactionbar"; }

    @Override
    public String getDescription() { return "Return test actionbar."; }

    @Override
    public String getSyntax() { return "/jp testactionbar"; }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        Configuration config = plugin.getConfig();

        ActionBar.sendActionBar(joinplugin, player, Ucolor.translateColorCodes(String.valueOf(config.getString("Join ActionBar.Message"))
                .replace("%player%", player.getName()
                        .replace("%playerdisplayname%", player.getDisplayName()))), config.getLong("Join ActionBar.Duration") * 20);
    }
}
