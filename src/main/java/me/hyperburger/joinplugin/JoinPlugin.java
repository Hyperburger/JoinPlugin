package me.hyperburger.joinplugin;

import com.earth2me.essentials.Essentials;
import me.hyperburger.joinplugin.commands.CommandManager;
import me.hyperburger.joinplugin.listeners.*;
import me.hyperburger.joinplugin.listeners.motd.JoinMotd;
import me.hyperburger.joinplugin.listeners.motd.ServerMOTD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinPlugin extends JavaPlugin {

    public static final double version = 1.6;
    public static Essentials essentials;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        startMsg();
        checkEssentials();

        registerEvents();
        registerCommands();
    }
/*
If you haven’t already, you should make the option so instead of choosing the join message you define in the config that the specified group doesn’t have any join message
And if you wanted to be really customisable, make a display type config that has the options:
CHAT
ACTION_BAR
BOTH
Which chooses where to display the message. (The action bar is the text above your hot bar)
Oh oh and another idea, make it compatible with both essentials and CMI and popular vanish plugins, if the player is in vanish mode when they join, don’t send their join message unless specified in config
 */


    @Override
    public void onDisable() {
        
    }

    public void startMsg(){
        if (checkEssentials()) {
            System.out.println("---------(Join Plugin)---------");
            System.out.println(" ");
            System.out.println("JoinPlugin: Successfully Loaded!");
            System.out.println("Found and supporting Essentials");
            System.out.println("Author: HyperBurger");
            System.out.println("version = " + version);
            System.out.println(" ");
            System.out.println("--------------------------------");
        } else {
            System.out.println("---------(Join Plugin)---------");
            System.out.println(" ");
            System.out.println("JoinPlugin: Successfully Loaded!");
            System.out.println("Author: HyperBurger");
            System.out.println("version = " + version);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Essentials isn't enabled/loaded on the server!");
            System.out.println("Won't support essentials.");
            System.out.println(" ");
            System.out.println("--------------------------------");
        }
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinMotd(this), this);
        this.getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new MaintenanceListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ServerMOTD(this),this);
        //this.getServer().getPluginManager().registerEvents(new JoinBook(this), this);
    }

    public boolean checkEssentials(){
        if (this.getServer().getPluginManager().getPlugin("Essentials") != null){
            essentials = (Essentials) this.getServer().getPluginManager().getPlugin("Essentials");
            return true;
        } else {
            System.out.println("---------(Join Plugin)---------");
            System.out.println(" ");
            System.out.println("Essentials isn't enabled/loaded on the server!");
            System.out.println("Won't support essentials.");
            System.out.println(" ");
            System.out.println("--------------------------------");

            return false;
        }
    }

    public void registerCommands(){
        this.getCommand("joinplugin").setExecutor(new CommandManager(this));
    }
    public static boolean mc18 (){
        return Bukkit.getServer().getVersion().contains("1.8");
    }


}
