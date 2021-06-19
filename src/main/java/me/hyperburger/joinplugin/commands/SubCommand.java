package me.hyperburger.joinplugin.commands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class SubCommand {

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntx();
    public abstract void perfrom(Player player, String args[], Plugin plugin);

}
