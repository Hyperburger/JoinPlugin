package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPluginHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuConfigActionBar extends ChestUI {

    public MenuConfigActionBar(Player player, MenuConfig menuConfig) {
        super(player, "Menu Config > ActionBar", 4);

        set(2, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7"))
                .name("§aConfig Join ActionBar")
                .lore(
                        "",
                        "§7Config the",
                        "§7join actionbar!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the actionbar you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinActionBarSet(player);
        });

        set(6, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/ca516fbae16058f251aef9a68d3078549f48f6d5b683f19cf5a1745217d72cc"))
                .name("§aConfig Join ActionBar Duration")
                .lore(
                        "",
                        "§7Config the",
                        "§7join actionbar",
                        "§7duration!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the actionbar duration (sec) you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinActionBarDurationSet(player);
        });

        for (int i = 1; i < 9; i++) set(i, 3, MenuGUI.BORDER, null);
        set(0, 3, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"))
                .name("§e <- Go Back ").getItem(), event -> {
            PlayerUI.openUI(player, menuConfig);
        });
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
