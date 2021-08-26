package me.hyperburger.joinplugin.events;

import featherpowders.ui.PlayerUI;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.JoinPluginHelper;
import me.hyperburger.joinplugin.manager.FileManager;
import me.hyperburger.joinplugin.menu.MenuGUI;
import me.hyperburger.joinplugin.messages.MessageType;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatEventsHandler implements Listener {

    private final JoinPlugin plugin;
    public ChatEventsHandler(JoinPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        processEventAsync(event, player, message);
    }

    private void processEventAsync(AsyncPlayerChatEvent event, Player player, String message) {
        ConfigurationSection rewardSection = plugin.getConfig().getConfigurationSection("Groups");

        if (JoinPluginHelper.maxPlayers.containsKey(player.getUniqueId())) {
            event.setCancelled(true);

            Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(JoinPlugin.class), () -> {
                if (message.equalsIgnoreCase("cancel")) {
                    player.sendMessage("§cSet max player canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }

                double value;
                try {
                    value = Double.parseDouble(message);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cInvalid input, set max player canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }

                int players = (int) Math.round(value);
                if (players < 1) {
                    player.sendMessage("§Invaild numbers, set max player canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }

                plugin.getConfig().set("MOTD.MaxPlayers.MaxPlayers", Integer.parseInt(String.valueOf(players)));
                plugin.saveConfig();
                player.sendMessage("§8[§5JoinPlugin§8]§d Max player set to " + players);
                PlayerUI.openUI(player, new MenuGUI(player, players));
                JoinPluginHelper.clearPlayerMarking(player);
            });
        }
        if (JoinPluginHelper.messageSet.containsKey(player.getUniqueId())) {
            event.setCancelled(true);

            Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(JoinPlugin.class), () -> {
                if (message.equalsIgnoreCase("cancel")) {
                    player.sendMessage("§cSet message canceled");
                    JoinPluginHelper.clearPlayerMarking(player);
                    return;
                }
                // Create Group
                MessageType type = JoinPluginHelper.messageSet.get(player.getUniqueId());
                if (type == MessageType.CREATE_GROUP) {
                    if (!plugin.getConfig().contains("Groups." + message)) {
                        plugin.getConfig().set("Groups." + message + ".permission", message + ".join");
                        plugin.getConfig().set("Groups." + message + ".Join Message", message + " join");
                        plugin.getConfig().set("Groups." + message + ".Quit Message", message + " quit");
                        plugin.getConfig().set("Groups." + message + ".Firework", true);
                        plugin.getConfig().set("Groups." + message + ".Sound", "BLOCK_NOTE_BLOCK_CHIME");
                        plugin.getConfig().set("Groups." + message + ".commands", new ArrayList<String>());
                        plugin.saveConfig();
                        player.sendMessage("§8[§5JoinPlugin§8]§d Create group §a" + message + "§d successfully");
                        JoinPluginHelper.clearPlayerMarking(player);
                    } else {
                        player.sendMessage("§8[§5JoinPlugin§8]§c The group §a" + message + "§c is already exist, cancelled create...");
                        JoinPluginHelper.clearPlayerMarking(player);
                    }
                }
                if (type == MessageType.GROUP_PERMISSION) {
                    HashMap<String, Object> options = (HashMap<String, Object>) JoinPluginHelper.setOptions.get(player.getUniqueId());
                    String group = (String) options.get("Groups");
                    plugin.getConfig().set("Groups." + group + ".permission", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join permission of group " + group +" have set to §f" + message);
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if(type == MessageType.CREATE_GROUP_COMMANDS) {
                    HashMap<String, Object> options = (HashMap<String, Object>) JoinPluginHelper.setOptions.get(player.getUniqueId());
                    String group = (String) options.get("Groups");
                    List<String> commands = plugin.getConfig().getStringList("Groups." + group + ".commands");
                    commands.add(message);
                    plugin.getConfig().set("Groups." + group + ".commands", commands);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join command of group " + group +" have been created §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if(type == MessageType.EDIT_GROUP_COMMANDS) {
                    HashMap<String, Object> options = (HashMap<String, Object>) JoinPluginHelper.setOptions.get(player.getUniqueId());
                    String group = (String) options.get("Groups");
                    int index = (int) options.get("Index");
                    List<String> commands = plugin.getConfig().getStringList("Groups." + group + ".commands");
                    commands.set(index, message);
                    plugin.getConfig().set("Groups." + group + ".commands", commands);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join command of group §r" + group + " at index: §r" + index + "§d have been set §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_MESSAGE) {
                    HashMap<String, Object> options = (HashMap<String, Object>) JoinPluginHelper.setOptions.get(player.getUniqueId());
                    String group = (String) options.get("Groups");
                    plugin.getConfig().set("Groups." + group + ".Join Message", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join message of group " + group +" have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.LEAVE_MESSAGE) {
                    HashMap<String, Object> options = (HashMap<String, Object>) JoinPluginHelper.setOptions.get(player.getUniqueId());
                    String group = (String) options.get("Groups");
                    plugin.getConfig().set("Groups." + group + ".Leave Message", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Leave message of group " + group +" have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.CUSTOM_JOIN_MESSAGE) {
                    FileConfiguration data = plugin.getFileManager().getFileConfig(FileManager.Files.PLAYERS_CUSTOM);
                    data.set(player.getUniqueId().toString() + ".Join Message", message);
                    plugin.getFileManager().saveFileConfig(data, FileManager.Files.PLAYERS_CUSTOM);
                    player.sendMessage("§8[§5JoinPlugin§8]§d You custom join message have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.CUSTOM_LEAVE_MESSAGE) {
                    FileConfiguration data = plugin.getFileManager().getFileConfig(FileManager.Files.PLAYERS_CUSTOM);
                    data.set(player.getUniqueId().toString() + ".Quit Message", message);
                    plugin.getFileManager().saveFileConfig(data, FileManager.Files.PLAYERS_CUSTOM);
                    player.sendMessage("§8[§5JoinPlugin§8]§d You custom leave message have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                // Sound
                if (type == MessageType.JOIN_SOUND) {
                    HashMap<String, Object> options = (HashMap<String, Object>) JoinPluginHelper.setOptions.get(player.getUniqueId());
                    String group = (String) options.get("Groups");
                    plugin.getConfig().set("Groups." + group + ".Sound", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join sound of group " + group +" have set to §r" + message);
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                // Title
                if (type == MessageType.JOIN_TITLE) {
                    plugin.getConfig().set("Join Title.Title", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join title message have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_SUBTITLE) {
                    plugin.getConfig().set("Join Title.SubTitle", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join subtitle message have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_TITLE_FADEIN) {
                    double value;
                    try {
                        value = Double.parseDouble(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid input, set fade in title canceled");
                        JoinPluginHelper.clearPlayerMarking(player);
                        return;
                    }

                    int fadein = (int) Math.round(value);

                    plugin.getConfig().set("Join Title.fadeIn", Integer.parseInt(String.valueOf(fadein)));
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join title fade in have been set to §r" + fadein + " second(s)");
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_TITLE_STAY) {
                    double value;
                    try {
                        value = Double.parseDouble(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid input, set stay title canceled");
                        JoinPluginHelper.clearPlayerMarking(player);
                        return;
                    }

                    int stay = (int) Math.round(value);

                    plugin.getConfig().set("Join Title.Stay", Integer.parseInt(String.valueOf(stay)));
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join title fade in have been set to §r" + stay + " second(s)");
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_TITLE_FADEOUT) {
                    double value;
                    try {
                        value = Double.parseDouble(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid input, set fade in title canceled");
                        JoinPluginHelper.clearPlayerMarking(player);
                        return;
                    }

                    int fadeout = (int) Math.round(value);

                    plugin.getConfig().set("Join Title.fadeOut", Integer.parseInt(String.valueOf(fadeout)));
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join title fade in have been set to §r" + fadeout + " second(s)");
                    JoinPluginHelper.clearPlayerMarking(player);
                }

                // ActionBar
                if (type == MessageType.JOIN_ACTIONBAR) {
                    plugin.getConfig().set("Join ActionBar.Message", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join actionbar message have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_ACTIONBAR_DURATION) {
                    double value;
                    try {
                        value = Double.parseDouble(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid input, set actionbar duration canceled");
                        JoinPluginHelper.clearPlayerMarking(player);
                        return;
                    }

                    int stay = (int) Math.round(value);

                    plugin.getConfig().set("Join ActionBar.Duration", Integer.parseInt(String.valueOf(stay)));
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join actionbar have been set to §r" + stay + " second(s)");
                    JoinPluginHelper.clearPlayerMarking(player);
                }

                // BossBar
                if (type == MessageType.JOIN_BOSSBAR) {
                    plugin.getConfig().set("Join BossBar.Message", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join bossbar message have set to §r" + Ucolor.translateColorCodes(message));
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_BOSSBAR_STYLE) {
                    plugin.getConfig().set("Join BossBar.Style", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join bossbar style have set to §r" + message);
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_BOSSBAR_COLOR) {
                    plugin.getConfig().set("Join BossBar.Color", message);
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join bossbar color have set to §r" + message);
                    JoinPluginHelper.clearPlayerMarking(player);
                }
                if (type == MessageType.JOIN_BOSSBAR_DURATION) {
                    double value;
                    try {
                        value = Double.parseDouble(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cInvalid input, set actionbar duration canceled");
                        JoinPluginHelper.clearPlayerMarking(player);
                        return;
                    }

                    int stay = (int) Math.round(value);

                    plugin.getConfig().set("Join BossBar.Duration", Integer.parseInt(String.valueOf(stay)));
                    plugin.saveConfig();
                    player.sendMessage("§8[§5JoinPlugin§8]§d Join actionbar have been set to §r" + stay + " second(s)");
                    JoinPluginHelper.clearPlayerMarking(player);
                }
            });
        }
    }
}
