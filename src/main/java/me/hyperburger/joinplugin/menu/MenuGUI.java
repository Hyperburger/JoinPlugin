package me.hyperburger.joinplugin.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuGUI {
    private final Inventory inv;
    private Map<Integer, MenuGUIAction> actions;

    public MenuGUI(int invSize, String invName) {
        inv = Bukkit.createInventory(null, invSize, invName);
        actions = new HashMap<>();
    }

    public Inventory getInv() {
        return inv;
    }

    public void setItem(int slot, ItemStack stack, MenuGUIAction action) {
        inv.setItem(slot, stack);
        if (action != null) {
            actions.put(slot, action);
        }
    }

    public void setItem(int slot, ItemStack stack) {
        setItem(slot, stack, null);
    }

    public interface MenuGUIAction {
        void click(Player player);
    }

    public void open(Player player) {
        player.openInventory(inv);
    }
}
