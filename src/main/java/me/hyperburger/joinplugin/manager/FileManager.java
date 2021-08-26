package me.hyperburger.joinplugin.manager;

import me.hyperburger.joinplugin.JoinPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

public class FileManager {

    public FileManager(JoinPlugin plugin) {
        for(Files f : Files.values()) {
            File fl = new File(plugin.getDataFolder(), f.getLocation());
            if(!fl.exists()) {
                fl.getParentFile().mkdirs();
                plugin.saveResource(f.getLocation(), false);
            }
            FileConfiguration config = new YamlConfiguration();
            try {
                config.load(fl);
            } catch(Exception ex) {}
            file.put(f, fl);
            configuration.put(f, config);
        }
    }



    private HashMap<Files, File> file = new HashMap<Files, File>();
    private HashMap<Files, FileConfiguration> configuration = new HashMap<Files, FileConfiguration>();

    public FileConfiguration getFileConfig(Files f) {
        return configuration.get(f);
    }

    public void saveFileConfig(FileConfiguration data, Files f) {
        try {
            data.save(file.get(f));
        } catch(Exception ex) {

        }
    }

    public void loadFileConfig(FileConfiguration data, Files f) {
        try {
            data.load(file.get(f));
            configuration.replace(f, data);
        } catch(Exception ex) {

        }
    }

    public enum Files {

        PLAYERS_CUSTOM("players.yml"),
        ;

        private String location;

        Files(String l) {
            this.location = l;
        }

        public String getLocation() {
            return this.location;
        }

    }

}
