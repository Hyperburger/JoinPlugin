package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuConfig extends ChestUI {

    public MenuConfig(Player player, MenuGUI menuGUI) {
        super(player, "JoinPlugin Menu > Menu Config", 4);

        set(1, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5ff31431d64587ff6ef98c0675810681f8c13bf96f51d9cb07ed7852b2ffd1"))
                .name("§aConfig Join Title")
                .lore(
                        "",
                        "§7Config the",
                        "§7join title!",
                        "",
                        "§7Click to§e open"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuConfigTitle(player, this));
        });

        set(3, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7"))
                .name("§aConfig Join ActionBar")
                .lore(
                        "",
                        "§7Config the",
                        "§7join actionbar!",
                        "",
                        "§7Click to§e open"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuConfigActionBar(player, this));
        });

        set(5, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/aec3ff563290b13ff3bcc36898af7eaa988b6cc18dc254147f58374afe9b21b9"))
                .name("§aConfig Join BossBar")
                .lore(
                        "",
                        "§7Config the",
                        "§7join bossbar!",
                        "",
                        "§7Click to§e open"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuConfigBossBar(player, this));
        });

        set(7, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/1e8c70e6a56161ecdc6b75674070fb308714a2b2b70e1e6d76bc920ce6ce7de6")
                ).name("§aConfig Groups")
                .lore(
                        "",
                        "§7Config the",
                        "§7groups!",
                        "",
                        "§7Click to§e open"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuGroups(player, this));
        });

        for (int i = 1; i < 9; i++) set(i, 3, MenuGUI.BORDER, null);
        set(0, 3, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"))
                .name("§e <- Go Back ").getItem(), event -> {
            PlayerUI.openUI(player, menuGUI);
        });
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
