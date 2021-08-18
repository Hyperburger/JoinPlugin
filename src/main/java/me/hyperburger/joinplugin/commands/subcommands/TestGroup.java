package me.hyperburger.joinplugin.commands.subcommands;

import me.hyperburger.joinplugin.commands.SubCommand;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TestGroup extends SubCommand {


    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "Information about the group.";
    }

    @Override
    public String getSyntax() {
        return "/jp test <group>";
    }

    @Override
    public void perform(Player player, String[] args, Plugin plugin) {
        ConfigurationSection groupSection = plugin.getConfig().getConfigurationSection("Groups");


        if (player.hasPermission("joinplugin.command.testgroup")) {
            if (args.length == 2) {
                String group = args[1];
                if (groupSection != null) {
                    for (String key : groupSection.getKeys(false)) {
                        ConfigurationSection idSection = groupSection.getConfigurationSection(key);     /* We now have idSection and can access it. */
                        if (idSection.getName().equalsIgnoreCase(group)) {

                            player.sendMessage(" ");
                            Ucolor.sendMessage(player, "&d&oGroup Information: &f&n" + group);
                            player.sendMessage(" ");
                            Ucolor.sendMessage(player, "&d&oJoin Message: &f" + idSection.getString("Join Message"));
                            Ucolor.sendMessage(player, "&d&oQuit Message: &f" + idSection.getString("Quit Message"));
                            player.sendMessage(" ");
                            Ucolor.sendMessage(player, "&d&oSound: &f" + idSection.getString("Sound"));
                            Ucolor.sendMessage(player, "&d&oFirework &f" + idSection.getBoolean("Firework"));
                            player.sendMessage(" ");
                            Ucolor.sendMessage(player, "&d&oCommands Information &7&o((Amount " + idSection.getStringList("commands").size() + "&7&o))");
                            player.sendMessage(" ");
                            for (String command : idSection.getStringList("commands")) {
                                Ucolor.sendMessage(player, "&7- &f&o" + command);
                            }
                            player.sendMessage(" ");


                        } else {
                            Ucolor.sendMessage(player, "&d&lERROR &fCan't find the group:&d " + args[1]);
                        }

                    }
                }

            } else {
                Ucolor.sendMessage(player, "&d&lERROR &fPlease try &7/jp test <group>");
            }
        }
    }
}
