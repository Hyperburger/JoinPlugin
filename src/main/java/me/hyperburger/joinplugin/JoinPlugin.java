package me.hyperburger.joinplugin;

import me.hyperburger.joinplugin.commands.CommandManager;
import me.hyperburger.joinplugin.listeners.*;
import me.hyperburger.joinplugin.listeners.motd.JoinMotd;
import me.hyperburger.joinplugin.listeners.motd.ServerMOTD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinPlugin extends JavaPlugin {


    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        startMsg();

        registerEvents();
        registerCommands();
    }



    @Override
    public void onDisable() {
        
    }
    
    
    



    public void startMsg(){
        double version = 1.2;
        System.out.println("------------------");
        System.out.println("JoinPlugin: Successfully Loaded!");
        System.out.println(" ");
        System.out.println("Author: HyperBurger");
        System.out.println("version = " + version);
        System.out.println("------------------");
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new JoinMotd(this), this);
        this.getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new MaintenanceListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ServerMOTD(this),this);
        //this.getServer().getPluginManager().registerEvents(new JoinBook(this), this);
    }

    public void registerCommands(){
        this.getCommand("joinplugin").setExecutor(new CommandManager(this));
    }

    public static boolean mc18 (){
        return Bukkit.getServer().getVersion().contains("1.8");
    }


}
