package me.hyperburger.joinplugin.versions;

import org.bukkit.entity.Player;

public interface MessageManager {

    void sendActionBar(String message, Player player);
    void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle);
}
