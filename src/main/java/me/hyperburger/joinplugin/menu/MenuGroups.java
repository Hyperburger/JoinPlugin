package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.JoinPluginHelper;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class MenuGroups extends ChestUI {

    private int page;

    public MenuGroups(Player player, MenuConfig menuConfig) {
        super(player, "Menu Config > Groups", 6);
        this.page = page;

        Configuration config = JoinPlugin.getPlugin(JoinPlugin.class).getConfig();

        int slot = 0;
        for (String group : config.getConfigurationSection("Groups").getKeys(false)) {
            set(slot, 0, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/1e8c70e6a56161ecdc6b75674070fb308714a2b2b70e1e6d76bc920ce6ce7de6"))
                    .name(group).getItem(), event -> {
                if (event.isShiftClick() && event.isRightClick()) {
                    config.set("Groups." + group, null);
                    JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                    player.closeInventory();
                    PlayerUI.openUI(player, new MenuGroups(player, new MenuConfig(player, new MenuGUI(player, 0))));
                    return;
                }
                HashMap<String, Object> groups = new HashMap<>();

                groups.put("Name", group);
                groups.put("Permissions", config.getString("Groups." + group + ".permission"));
                groups.put("JoinMessage", config.getString("Groups." + group + ".Join Message"));
                groups.put("QuitMessage", config.getString("Groups." + group + ".Quit Message"));
                groups.put("Firework", config.getBoolean("Groups." + group + ".Firework"));
                groups.put("Sound", config.getString("Groups." + group + ".Sound"));
                groups.put("Commands", config.getStringList("Groups." + group + ".commands"));

                PlayerUI.openUI(player, new MenuConfigGroup(player, this , groups));
            });
            slot += 1;
        }

        for (int i = 1; i < 9; i++) set(i, 5, MenuGUI.BORDER, null);
        set(0, 5, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"))
                .name("§e <- Go Back ").getItem(), event -> {
            PlayerUI.openUI(player, menuConfig);
        });
        set(7, 5, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/b056bc1244fcff99344f12aba42ac23fee6ef6e3351d27d273c1572531f"))
                .name("§a   Create Group   ")
                .lore(
                        "",
                        "§7Create new group",
                        "",
                        "§7Click to§e create"
                )
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat how much player can in server",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markCreateGroup(player);
        });
        renderPage();
    }

    private void renderPage() {
        Configuration config = JoinPlugin.getPlugin(JoinPlugin.class).getConfig();

        if (page > 0) set(2, 5, new ItemBuilder(XMaterial.ARROW.parseMaterial(), 1).name("§e <- Previous Page ").getItem(), event -> {
            page--;
            renderPage();
        });
        if ((page + 1) * 36 < config.getConfigurationSection("Groups").getKeys(false).size()) set(6, 5, new ItemBuilder(XMaterial.ARROW.parseMaterial(), 1).name("§e Next Page -> ").getItem(), event -> {
            page++;
            renderPage();
        });
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
