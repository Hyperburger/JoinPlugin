package me.hyperburger.joinplugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JoinPluginHelper {

    public static HashMap<UUID, Integer> maxPlayers = new HashMap<>();

    public static void clearPlayerMarking(Player player) {
        maxPlayers.remove(player.getUniqueId());
    }
}
