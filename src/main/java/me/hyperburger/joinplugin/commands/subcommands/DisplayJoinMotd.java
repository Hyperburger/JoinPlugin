package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.DefaultFontInfo;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisplayJoinMotd extends SubCommand {

    private final static int CENTER_PX = 154;

    @Override
    public String getName() {
        return "joinmotd";
    }

    @Override
    public String getDescription() {
        return "Displays the currently set join MOTD.";
    }

    @Override
    public String getSyntax() {
        return "/jp joinmotd";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
      if (player.hasPermission("joinplugin.command.displayjoinmotd")) {
          Ucolor.sendMessage(player, "    &fJoin MOTD from config.yml" );
          Ucolor.sendMessage(player, "    &8&m--------------------&f");
          player.sendMessage(" ");
          for (String s : plugin.getConfig().getStringList("MOTD.JoinMOTD.Message")) {
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
          player.sendMessage(" ");
          Ucolor.sendMessage(player, "    &8&m--------------------&f");
      } else {
          Ucolor.NOPERM(player, "joinplugin.command.displayjoinmotd");
      }
    }
}
