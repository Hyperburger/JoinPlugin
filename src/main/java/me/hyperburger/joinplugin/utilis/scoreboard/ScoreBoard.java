package me.hyperburger.joinplugin.utilis.scoreboard;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoard {
    static Plugin plugin = JoinPlugin.getPlugin(JoinPlugin.class);

    ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();

}
