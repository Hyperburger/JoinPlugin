package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.configs.MotdFile;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetMaxPlayers extends SubCommand {

    @Override
    public String getName() {
        return "setmaxplayers";
    }

    @Override
    public String getDescription() {
        return "Sets the max players.";
    }

    @Override
    public String getSyntax() {
        return "/jp setmaxplayers <number>";
    }

    @Override
    public void perform(Player player, String[] args, Plugin pluginl) {

        if (args.length > 1){

            if (!player.hasPermission("joinplugin.command.maxplayers")) return;

            MotdFile.getFile().set("MOTD.MaxPlayers.MaxPlayers", Integer.parseInt(args[1]));
            MotdFile.saveFile();
            System.out.println("Saved motd.yml with new info.");
            Ucolor.sendMessage(player, "&d&lJOIN PLUGIN &fThe max players have been set to &d&n&o" + args[1]);

        }
    }
}
