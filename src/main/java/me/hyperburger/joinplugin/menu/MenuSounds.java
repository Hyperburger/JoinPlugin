package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.sounds.XSounds;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MenuSounds extends ChestUI {

    private int page;

    public MenuSounds(Player player, MenuGUI menuGUI) {
        super(player, "Sounds List", 6);
        this.page = page;

        for (int i = 1; i < 9; i++) set(i, 0, MenuGUI.BORDER, null);
        set(0, 0, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"))
                .name("§e <- Go Back ").getItem(), event -> {
            PlayerUI.openUI(player, menuGUI);
        });
        renderPage();
    }

    private void renderPage() {
        if (page > 0) {
            set(2, 0, new ItemBuilder(XMaterial.ARROW.parseMaterial(), 1).name("§e <- Previous Page ").getItem(), event -> {
                event.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        setCancelDragEvent(true);
                    }
                }.runTaskLater(JoinPlugin.getPlugin(JoinPlugin.class), 1);
                page--;
                renderPage();
            });
        } else {
            set(2, 0, MenuGUI.BORDER, null);
        }
        if (page < XSounds.listSound.size() / 45) {
            set(6, 0, new ItemBuilder(XMaterial.ARROW.parseMaterial(), 1).name("§e Next Page -> ").getItem(), event -> {
                event.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        setCancelDragEvent(true);
                    }
                }.runTaskLater(JoinPlugin.getPlugin(JoinPlugin.class), 1);
                page++;
                renderPage();
            });
        } else {
            set(6, 0, MenuGUI.BORDER, null);
        }
        for (int i = 0; i < 45; i++) {
            int index = page * 45 + i;
            if (index < 0) index = 0;

            if (index >= XSounds.listSound.size()) {
                clearSlot(i % 9, 1 + (i / 9));
                continue;
            }

            final int finalIndex = index;

            set(i % 9, 1 + (i / 9), new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                    .name("§e" + XSounds.listSound.get(index).toString())
                    .lore(
                            "",
                            "§8᛫§7 Left click for pitch 0",
                            "§8᛫§7 Right click for pitch 1",
                            "§8᛫§7 Shift click for pitch 2",
                            ""
                    ).getItem(), event -> {
                event.setCancelled(true);
                if (event.isLeftClick()) {
                    player.playSound(player.getLocation(), Sound.valueOf(XSounds.listSound.get(finalIndex).toString().replace(" ", "_").toUpperCase()), 1f, 1f);
                }
                if (event.isRightClick()) {
                    player.playSound(player.getLocation(), Sound.valueOf(XSounds.listSound.get(finalIndex).toString().replace(" ", "_").toUpperCase()), 1f, 2f);
                }
                if (event.isShiftClick()) {
                    player.playSound(player.getLocation(), Sound.valueOf(XSounds.listSound.get(finalIndex).toString().replace(" ", "_").toUpperCase()), 1f, 3f);
                }
            });
        }
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
