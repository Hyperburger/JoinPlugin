package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.messages.ActionBar;
import com.cryptomorin.xseries.messages.Titles;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.utilis.Placeholders;
import me.hyperburger.joinplugin.utilis.Ucolor;
import me.hyperburger.joinplugin.utilis.bossbar.BossBar;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuTestJoinMessages extends ChestUI {

    public MenuTestJoinMessages(Player player, MenuGUI menuGUI) {
        super(player, "JoinPlugin Menu > Messages Join Test", 5);
        Configuration config = JoinPlugin.getPlugin(JoinPlugin.class).getConfig();

        for (int i = 1; i < 9; i++) set(i, 4, MenuGUI.BORDER, null);
        set(0, 4, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"))
                .name("§e <- Go Back ").getItem(), event -> {
            PlayerUI.openUI(player, menuGUI);
        });

        set(1, 1, new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                .name("§eDisplay Join MOTD")
                .lore(
                        "",
                        "§7Click to see",
                        "§7you current join MOTD.",
                        "",
                        "§7Click to§e see!")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.performCommand("jp joinmotd");
        });
        if (config.getBoolean("MOTD.JoinMOTD.Enabled")) {
            set(1, 2, new ItemBuilder(XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§aEnabled")
                    .lore(
                            "",
                            "§7Click to disable",
                            "§7join MOTD.",
                            "",
                            "§7Click to§c disable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("MOTD.JoinMOTD.Enabled", false);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        } else {
            set(1, 2, new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§cDisabled")
                    .lore(
                            "",
                            "§7Click to enabled",
                            "§7join MOTD.",
                            "",
                            "§7Click to§a enable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("MOTD.JoinMOTD.Enabled", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        }
        set(3, 1, new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                .name("§eDisplay Join Title")
                .lore(
                        "",
                        "§7Click to see",
                        "§7you current join",
                        "§7title.",
                        "",
                        "§7Click to§e see!")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            Titles.sendTitle(player,
                    config.getInt("Join Title.fadeIn") * 20,
                    config.getInt("Join Title.Stay") * 20,
                    config.getInt("Join Title.fadeOut") * 20,
                    Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join Title.Title")))),
                    Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join Title.SubTitle")))));
        });
        if (config.getBoolean("Join Title.Enabled")) {
            set(3, 2, new ItemBuilder(XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§aEnabled")
                    .lore(
                            "",
                            "§7Click to disable",
                            "§7join Title.",
                            "",
                            "§7Click to§c disable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("Join Title.Enabled", false);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        } else {
            set(3, 2, new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§cDisabled")
                    .lore(
                            "",
                            "§7Click to enabled",
                            "§7join Title.",
                            "",
                            "§7Click to§a enable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("Join Title.Enabled", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        }
        set(5, 1, new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                .name("§eDisplay Join ActionBar")
                .lore(
                        "",
                        "§7Click to see",
                        "§7you current join",
                        "§7actionbar.",
                        "",
                        "§7Click to§e see!")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            ActionBar.sendActionBar(JoinPlugin.getPlugin(JoinPlugin.class), player, Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join ActionBar.Message"))
                    .replace("%player%", player.getName()
                            .replace("%playerdisplayname%", player.getDisplayName())))), config.getInt("Join ActionBar.Duration") * 10L);
        });
        if (config.getBoolean("Join ActionBar.Enabled")) {
            set(5, 2, new ItemBuilder(XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§aEnabled")
                    .lore(
                            "",
                            "§7Click to disable",
                            "§7join ActionBar.",
                            "",
                            "§7Click to§c disable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("Join ActionBar.Enabled", false);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        } else {
            set(5, 2, new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§cDisabled")
                    .lore(
                            "",
                            "§7Click to enabled",
                            "§7join ActionBar.",
                            "",
                            "§7Click to§a enable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("Join ActionBar.Enabled", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        }
        set(7, 1, new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                .name("§eDisplay Join BossBar")
                .lore(
                        "",
                        "§7Click to see",
                        "§7you current join",
                        "§7bossbar.",
                        "",
                        "§7Click to§e see!")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            BossBar.sendBossbar(player,
                    Ucolor.translateColorCodes(Placeholders.replace(player, String.valueOf(config.getString("Join BossBar.Message")))),
                    config.getInt("Join BossBar.Duration"),
                    config.getString("Join BossBar.Style"),
                    config.getString("Join BossBar.Color"));
        });
        if (config.getBoolean("Join BossBar.Enabled")) {
            set(7, 2, new ItemBuilder(XMaterial.GREEN_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§aEnabled")
                    .lore(
                            "",
                            "§7Click to disable",
                            "§7join BossBar.",
                            "",
                            "§7Click to§c disable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("Join BossBar.Enabled", false);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        } else {
            set(7, 2, new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial(), 1)
                    .name("§cDisabled")
                    .lore(
                            "",
                            "§7Click to enabled",
                            "§7join BossBar.",
                            "",
                            "§7Click to§a enable!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                config.set("Join BossBar.Enabled", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                player.closeInventory();
                PlayerUI.openUI(player, new MenuTestJoinMessages(player, new MenuGUI(player, 0)));
            });
        }
        set(4, 4, new ItemBuilder(XMaterial.RED_DYE.parseMaterial(), 1)
                .name("§cReload Config")
                .lore(
                        "",
                        "§7Click to reload",
                        "§7the plugin.",
                        "",
                        "§7Click to§e reload!"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            JoinPlugin.getPlugin(JoinPlugin.class).reloadConfig();
            Titles.sendTitle(player, 20, 2 * 20, 20, Ucolor.colorize("&a&lConfig"), Ucolor.colorize("&a&lSuccessfully Reloaded!"));
        });
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
