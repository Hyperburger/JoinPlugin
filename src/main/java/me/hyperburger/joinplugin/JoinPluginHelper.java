package me.hyperburger.joinplugin;

import me.hyperburger.joinplugin.messages.MessageType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JoinPluginHelper {

    public static HashMap<UUID, MessageType> messageSet = new HashMap<>();
    public static HashMap<UUID, Integer> maxPlayers = new HashMap<>();

    public static void markJoinMessageSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_MESSAGE);
    }

    public static void markLeaveMessageSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.LEAVE_MESSAGE);
    }

    public static void clearPlayerMarking(Player player) {
        maxPlayers.remove(player.getUniqueId());
    }
}
