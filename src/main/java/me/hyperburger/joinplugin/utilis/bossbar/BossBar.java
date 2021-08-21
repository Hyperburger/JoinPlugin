package me.hyperburger.joinplugin.utilis.bossbar;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BossBar {
    static Plugin plugin = JoinPlugin.getPlugin(JoinPlugin.class);

    public static void sendBossbar(Player player, String s, int duration, String style, String color) {
        BarColor barColor = BarColor.valueOf(color);
        BarStyle barStyle = BarStyle.valueOf(style);
        org.bukkit.boss.BossBar bossBar = Bukkit.getServer().createBossBar(s, barColor, barStyle);
        bossBar.addPlayer(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                bossBar.removePlayer(player);
            }
        }, (long)duration * 10L);
    }
}
