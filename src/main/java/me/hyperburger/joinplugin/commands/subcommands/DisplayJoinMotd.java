package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisplayJoinMotd extends SubCommand {


    @Override
    public String getName() {
        return "joinmotd";
    }

    @Override
    public String getDescription() {
        return "Displays the currently set join MOTD.";
    }

    @Override
    public String getSyntx() {
        return "/jp joinmotd";
    }

    @Override
    public void perfrom(Player player, String[] args, Plugin plugin) {
      if (player.hasPermission("joinplugin.command.displayjoinmotd")) {
          for (String s : plugin.getConfig().getStringList("MOTD.JoinMOTD.Message")) {
              Ucolor.sendMessage(player, s);
          }
      } else {
          Ucolor.NOPERM(player, "joinplugin.command.displayjoinmotd");
      }
    }
}
