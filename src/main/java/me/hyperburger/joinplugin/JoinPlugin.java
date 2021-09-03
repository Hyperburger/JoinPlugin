package me.hyperburger.joinplugin;

import com.earth2me.essentials.Essentials;
import me.hyperburger.joinplugin.commands.CommandManager;
import me.hyperburger.joinplugin.commands.TabCompletion;
import me.hyperburger.joinplugin.configs.MotdFile;
import me.hyperburger.joinplugin.listeners.*;
import me.hyperburger.joinplugin.listeners.motd.JoinMotd;
import me.hyperburger.joinplugin.listeners.motd.ServerMOTD;
import me.hyperburger.joinplugin.utilis.Utilis;
import me.hyperburger.joinplugin.versions.MessageManager;
import me.hyperburger.joinplugin.versions.Message_1_8_8;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * @author HyperBurger
 */

public final class JoinPlugin extends JavaPlugin {

    public static final double version = 2.6;
    public static Essentials essentials;
    public static String sversion;

    private MotdFile motdFile; // motd.yml

    public static MessageManager messageManager;

    @Override
    public void onEnable() {

        motdFile = new MotdFile(this);

        motdFile.setupConfig();
        motdFile.setDefaults();

        MotdFile.getFile().options().copyDefaults(true);
        MotdFile.saveFile();


        System.out.println(MotdFile.getFile().getString("TestMessage"));

        if (setupManager()){
            System.out.println("             =======================");
            System.out.println(" ");
            Utilis.logMessage(this.getClass(), "         [Join Plugin] Using code for 1.8.8");
            System.out.println(" ");
            System.out.println("             =======================");
        }
        this.reloadConfig();

        startMsg();
        checkEssentials();

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.reloadConfig();

        registerEvents();
        registerCommands();
        registerTabCompletion();

    }

    private void registerEvents(){
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new JoinListener(this), this);
        pluginManager.registerEvents(new FirstJoinListener(this), this);
        pluginManager.registerEvents(new JoinMotd(), this);
        pluginManager.registerEvents(new QuitListener(this), this);
        pluginManager.registerEvents(new MaintenanceListener(this), this);
        pluginManager.registerEvents(new ServerMOTD(),this);
    }

    private void registerCommands(){
        this.getCommand("joinplugin").setExecutor(new CommandManager(this));
    }

    private void registerTabCompletion(){
        this.getCommand("joinplugin").setTabCompleter(new TabCompletion(this, new CommandManager(this)));
    }



    //Checking version.
    public static boolean setupManager(){
        JoinPlugin.sversion = "N/A";
        try {
            JoinPlugin.sversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Your server is trying to use a code that is not available for this version.");
            return false;
        }
        if (JoinPlugin.sversion.equalsIgnoreCase("v1_8_R3")){ //Will add supported versions later.
            messageManager = new Message_1_8_8();
        }
        return messageManager != null;
    }

    public boolean checkEssentials(){
        if (this.getServer().getPluginManager().getPlugin("Essentials") != null){
            essentials = (Essentials) this.getServer().getPluginManager().getPlugin("Essentials");
            return true;
        }
        return false;
    }

    public void startMsg(){
            System.out.println("  ------------(Join Plugin)------------  ");
            System.out.println(" ");
            System.out.println("    JoinPlugin: Successfully Loaded!");
            System.out.println("    Author: HyperBurger");
            System.out.println("    version = " + version);
            System.out.println(" ");
            System.out.println("  -------------------------------------  ");
    }


    public static boolean mc18 (){
        return Bukkit.getServer().getVersion().contains("1.8");
    }

}
