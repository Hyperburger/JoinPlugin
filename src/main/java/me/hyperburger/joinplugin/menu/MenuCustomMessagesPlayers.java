package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.JoinPluginHelper;
import me.hyperburger.joinplugin.manager.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuCustomMessagesPlayers extends ChestUI {

    public MenuCustomMessagesPlayers(Player player) {
        super(player, "Custom Messages", 4);

        FileConfiguration data = JoinPlugin.getPlugin(JoinPlugin.class).getFileManager().getFileConfig(FileManager.Files.PLAYERS_CUSTOM);

        set(2,1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5d8604b9e195367f85a23d03d9dd503638fcfb05b0032535bc43734422483bde"))
                .name("§aCustom Join Message")
                .lore(
                        "",
                        "§7Click to custom",
                        "§7you join message.",
                        "",
                        "§7Click to§e edit!",
                        "§7Shift click to§c delete!"
                ).getItem(), event -> {
            if (event.isShiftClick()) {
                event.setCancelled(true);
                setCancelDragEvent(true);
                data.set(player.getUniqueId().toString() + ".Quit Message", null);
                JoinPlugin.getPlugin(JoinPlugin.class).getFileManager().saveFileConfig(data, FileManager.Files.PLAYERS_CUSTOM);
                player.closeInventory();
                PlayerUI.openUI(player, new MenuCustomMessagesPlayers(player));
                return;
            }
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the join message you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markCustomJoinMessage(player);
        });

        set(6,1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/482c23992a02725d9ed1bcd90fd0307c8262d87e80ce6fac8078387de18d0851"))
                .name("§aCustom Leave Message")
                .lore(
                        "",
                        "§7Click to custom",
                        "§7you leave message.",
                        "",
                        "§7Click to§e edit!",
                        "§7Shift click to§c delete!"
                ).getItem(), event -> {
            if (event.isShiftClick()) {
                event.setCancelled(true);
                setCancelDragEvent(true);
                data.set(player.getUniqueId().toString() + ".Quit Message", null);
                JoinPlugin.getPlugin(JoinPlugin.class).getFileManager().saveFileConfig(data, FileManager.Files.PLAYERS_CUSTOM);
                player.closeInventory();
                PlayerUI.openUI(player, new MenuCustomMessagesPlayers(player));
                return;
            }
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the leave message you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markCustomLeaveMessage(player);
        });

        for (int i = 0; i < 9; i++) { set(i, 3, MenuGUI.BORDER, null); }
        set(4,3, new ItemBuilder(XMaterial.BARRIER.parseMaterial(), 1).name("§cClose").getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
        });
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
