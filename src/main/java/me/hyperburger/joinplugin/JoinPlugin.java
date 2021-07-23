package me.hyperburger.joinplugin;

import com.earth2me.essentials.Essentials;
import me.hyperburger.joinplugin.commands.CommandManager;
import me.hyperburger.joinplugin.listeners.*;
import me.hyperburger.joinplugin.listeners.motd.JoinMotd;
import me.hyperburger.joinplugin.listeners.motd.ServerMOTD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinPlugin extends JavaPlugin {

    /*
     * @author HyperBurger
     */

    public static final double version = 1.7;
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
    public void registerEvents(){
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new JoinListener(this), this);
        pluginManager.registerEvents(new JoinMotd(this), this);
        pluginManager.registerEvents(new QuitListener(this), this);
        pluginManager.registerEvents(new MaintenanceListener(this), this);
        pluginManager.registerEvents(new ServerMOTD(this),this);
    }

    public void registerCommands(){
        this.getCommand("joinplugin").setExecutor(new CommandManager(this));
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

    public void startMsg(){
        if (checkEssentials()) {
            System.out.println("  ------------(Join Plugin)------------");
            System.out.println(" ");
            System.out.println("    JoinPlugin: Successfully Loaded!");
            System.out.println("    Found and supporting Essentials");
            System.out.println("    Author: HyperBurger");
            System.out.println("    version = " + version);
            System.out.println(" ");
            System.out.println("  -------------------------------------");
        } else {
            System.out.println("  ------------(Join Plugin)------------");
            System.out.println(" ");
            System.out.println("    JoinPlugin: Successfully Loaded!");
            System.out.println("    Author: HyperBurger");
            System.out.println("    version = " + version);
            System.out.println(" ");
            System.out.println("  -------------------------------------");
        }
    }

    public static boolean mc18 (){
        return Bukkit.getServer().getVersion().contains("1.8");
    }


}
