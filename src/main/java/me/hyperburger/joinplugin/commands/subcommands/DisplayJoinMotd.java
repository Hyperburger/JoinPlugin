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
              Ucolor.sendMessage(player, "    " + s);
          }
          player.sendMessage(" ");
          Ucolor.sendMessage(player, "    &8&m--------------------&f");
      } else {
          Ucolor.NOPERM(player, "joinplugin.command.displayjoinmotd");
      }
    }
}
