package me.hyperburger.joinplugin.listeners.motd;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.DefaultFontInfo;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMotd implements Listener {

    private final static int CENTER_PX = 154;

    private final JoinPlugin plugin;
    public JoinMotd(JoinPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinMotd(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            if (plugin.getConfig().getBoolean("MOTD.JoinMOTD.Enabled")) {
                for (String joinMotdMessages : plugin.getConfig().getStringList("MOTD.JoinMOTD.Message")) {
                    if (joinMotdMessages.startsWith("centered:")) {

                        joinMotdMessages = joinMotdMessages.replace("centered:", ""); // remove centered
                        String[] lines = Ucolor.translateColorCodes(joinMotdMessages).split("\n", 40); //translate hex color so can centered it
                        StringBuilder returnMessage = new StringBuilder();

                        for (String line : lines) {
                            int messagePxSize = 0;
                            boolean previousCode = false;
                            boolean isBold = false;

                            for (char c : line.toCharArray()) {
                                if (c == '§') {
                                    previousCode = true;
                                } else if (previousCode) {
                                    previousCode = false;
                                    isBold = c == 'l';
                                } else {
                                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                                    messagePxSize = isBold ? messagePxSize + dFI.getBoldLength() : messagePxSize + dFI.getLength();
                                    messagePxSize++;
                                }
                            }
                            int toCompensate = CENTER_PX - messagePxSize / 2;
                            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
                            int compensated = 0;
                            StringBuilder sb = new StringBuilder();
                            while(compensated < toCompensate){
                                sb.append(" ");
                                compensated += spaceLength;
                            }
                            returnMessage.append(sb.toString()).append(line).append("\n");
                        }
                        Ucolor.sendMessage(player, Placeholders.replace(player, returnMessage.toString()
                                .replace("%player%", player.getName()
                                        .replace("%playerdisplayname%", player.getDisplayName()))).toString());
                    } else {
                        Ucolor.sendMessage(player, Placeholders.replace(player, joinMotdMessages
                                .replace("%player%", player.getName()
                                        .replace("%playerdisplayname%", player.getDisplayName()))));
                    }
                }
            }

        } else {
            if (plugin.getConfig().getBoolean("JoinMOTD.FirstJoinMOTD.Enabled")) {
                for (String s : plugin.getConfig().getStringList("JoinMOTD.FirstJoinMOTD.Message")) {
                    if (s.startsWith("centered:")) {

                        s = s.replace("centered:", ""); // remove centered
                        String[] lines = Ucolor.translateColorCodes(s).split("\n", 40); //translate hex color so can centered it
                        StringBuilder returnMessage = new StringBuilder();

                        for (String line : lines) {
                            int messagePxSize = 0;
                            boolean previousCode = false;
                            boolean isBold = false;

                            for (char c : line.toCharArray()) {
                                if (c == '§') {
                                    previousCode = true;
                                } else if (previousCode) {
                                    previousCode = false;
                                    isBold = c == 'l';
                                } else {
                                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                                    messagePxSize = isBold ? messagePxSize + dFI.getBoldLength() : messagePxSize + dFI.getLength();
                                    messagePxSize++;
                                }
                            }
                            int toCompensate = CENTER_PX - messagePxSize / 2;
                            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
                            int compensated = 0;
                            StringBuilder sb = new StringBuilder();
                            while(compensated < toCompensate){
                                sb.append(" ");
                                compensated += spaceLength;
                            }
                            returnMessage.append(sb.toString()).append(line).append("\n");
                        }
                        Ucolor.sendMessage(player, Placeholders.replace(player, returnMessage.toString()
                                .replace("%player%", player.getName()
                                        .replace("%playerdisplayname%", player.getDisplayName()))));
                    } else {
                        Ucolor.sendMessage(player, Placeholders.replace(player, s
                                .replace("%player%", player.getName()
                                        .replace("%playerdisplayname%", player.getDisplayName()))));
                    }
                }
            }
        }
    }
}
