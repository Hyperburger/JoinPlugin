package me.hyperburger.joinplugin;

import me.hyperburger.joinplugin.messages.MessageType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JoinPluginHelper {

    public static HashMap<UUID, MessageType> messageSet = new HashMap<>();
    public static HashMap<UUID, Integer> maxPlayers = new HashMap<>();
    public static HashMap<UUID, Object> setOptions = new HashMap<>();

    // Message
    public static void markJoinMessageSet(Player player, String groups) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_MESSAGE);
        setOptions.put(player.getUniqueId(), new Object() {
            HashMap<String, Object> parse() {
                HashMap<String, Object> options = new HashMap<>();
                options.put("Groups", groups);
                return options;
            }
        }.parse());
    }

    public static void markLeaveMessageSet(Player player, String groups) {
        messageSet.put(player.getUniqueId(), MessageType.LEAVE_MESSAGE);
        setOptions.put(player.getUniqueId(), new Object() {
            HashMap<String, Object> parse() {
                HashMap<String, Object> options = new HashMap<>();
                options.put("Groups", groups);
                return options;
            }
        }.parse());
    }

    // Sound
    public static void markJoinSoundSet(Player player, String groups) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_SOUND);
        setOptions.put(player.getUniqueId(), new Object() {
            HashMap<String, Object> parse() {
                HashMap<String, Object> options = new HashMap<>();
                options.put("Groups", groups);
                return options;
            }
        }.parse());
    }

    // Create Group
    public static void markCreateGroup(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.CREATE_GROUP);
    }

    // Permission
    public static void markGroupPermissionSet(Player player, String groups) {
        messageSet.put(player.getUniqueId(), MessageType.GROUP_PERMISSION);
        setOptions.put(player.getUniqueId(), new Object() {
            HashMap<String, Object> parse() {
                HashMap<String, Object> options = new HashMap<>();
                options.put("Groups", groups);
                return options;
            }
        }.parse());
    }

    // Title
    public static void markJoinTitleSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_TITLE);
    }
    public static void markJoinSubTitleSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_SUBTITLE);
    }
    public static void markJoinTitleFadeInSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_TITLE_FADEIN);
    }
    public static void markJoinTitleStaySet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_TITLE_STAY);
    }
    public static void markJoinTitleFadeOutSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_TITLE_FADEOUT);
    }

    // ActionBar
    public static void markJoinActionBarSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_ACTIONBAR);
    }

    public static void markJoinActionBarDurationSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_ACTIONBAR_DURATION);
    }

    // BossBar
    public static void markJoinBossBarSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_BOSSBAR);
    }

    public static void markJoinBossBarStyleSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_BOSSBAR_STYLE);
    }

    public static void markJoinBossBarColorSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_BOSSBAR_COLOR);
    }

    public static void markJoinBossBarDurationSet(Player player) {
        messageSet.put(player.getUniqueId(), MessageType.JOIN_BOSSBAR_DURATION);
    }

    public static void clearPlayerMarking(Player player) {
        maxPlayers.remove(player.getUniqueId());
        messageSet.remove(player.getUniqueId());
        setOptions.remove(player.getUniqueId());
    }
}
