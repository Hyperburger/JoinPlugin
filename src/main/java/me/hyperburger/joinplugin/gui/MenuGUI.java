package me.hyperburger.joinplugin.gui;

import me.hyperburger.joinplugin.utilis.Ucolor;
import me.hyperburger.joinplugin.utilis.Utilis;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class MenuGUI {

    public static Inventory joinInventory = Bukkit.createInventory(null, 9, Ucolor.colorize("&d&lJoin Plugin Menu"));

    public static Inventory getJoinInventory (){



        return joinInventory;
    }

}
