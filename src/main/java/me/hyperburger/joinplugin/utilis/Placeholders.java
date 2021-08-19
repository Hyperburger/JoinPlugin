package me.hyperburger.joinplugin.utilis;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Placeholders {

    public static List<String> replace(Player player, List<String> strings) {
        List<String> results = new ArrayList<>();
        strings.forEach(str -> results.add(replace(player, str)));
        return results;
    }

    public static String replace(Player player, String string) {
        if (string == null || player == null) {
            return string;
        }
        return PlaceholderAPI.setPlaceholders(player, string);
    }
}
