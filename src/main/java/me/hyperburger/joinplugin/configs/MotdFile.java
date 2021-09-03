package me.hyperburger.joinplugin.configs;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MotdFile {

    private final JoinPlugin plugin;
    public static FileConfiguration customConfig;
    public static File file;

    public MotdFile(JoinPlugin plugin){
        this.plugin = plugin;

    }

    // Finds or generates the custom config file.
    public void setupConfig(){
        file = new File(plugin.getDataFolder()+"/motd.yml");
        if (!file.exists()){
            System.out.println("Couldn't find motd.yml, Generating a new one...");
            try {
                file.createNewFile();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        customConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveFile(){
        try {
            customConfig.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save motd.yml! Something went wrong.");
            e.printStackTrace();
        }
    }

    public static void reloadFile(){
        customConfig = YamlConfiguration.loadConfiguration(file);
    }


    public void setDefaults(){

        List<String> messages = getFile().getStringList("MOTD.Join");

        messages.add("&f&l&m----------");
        messages.add("&a&This is the join MOTD");
        messages.add("&b&l&m---------------");

        customConfig.addDefault("MOTD.MaxPlayers.Enabled", true);
        customConfig.addDefault("MOTD.MaxPlayers.MaxPlayers", 200);
        customConfig.addDefault("MOTD.MaxPlayers.KickMessage", "&cServer is full!");
        customConfig.addDefault("MOTD.JoinMOTD.Enabled", true);
        customConfig.addDefault("MOTD.JoinMOTD.Message", messages);
        customConfig.addDefault("MOTD.ServerMOTD.Enabled", true);
        customConfig.addDefault("MOTD.ServerMOTD.Line-1", "&d&lJOIN PLUGIN");
        customConfig.addDefault("MOTD.ServerMOTD.Line-2", "&f&lCHANGE THIS IN THE CONFIG.YML");

    }

    public static FileConfiguration getFile(){
        return customConfig;
    }

    public static File getNormalFile(){
        return file;
    }
}
