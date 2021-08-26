package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.messages.Titles;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPlugin;
import me.hyperburger.joinplugin.JoinPluginHelper;
import me.hyperburger.joinplugin.utilis.Ucolor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuGUI extends ChestUI {

    protected static final ItemStack BORDER = new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial(), 1).name("§0").getItem();

    public MenuGUI(Player player, int players) {
        super(player, "JoinPlugin Menu", 5);
        Configuration config = JoinPlugin.getPlugin(JoinPlugin.class).getConfig();

        if (config.getBoolean("MOTD.MaxPlayers.Enabled")) {
            set(5, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/b114da6a7bcf475d3100aa93cbccc97db55f7b1afb537a59b5e075feab569ab5"))
                    .name("§eSet Max Players")
                    .lore(
                            "",
                            "§7Click to set",
                            "§7max player.",
                            "",
                            "§7Current max player: " + JoinPlugin.getPlugin(JoinPlugin.class).getConfig().getString("MOTD.MaxPlayers.MaxPlayers"),
                            "",
                            "§8➥ [§3Left-Click] §7Click to§e set!",
                            "§8➥ [§3Right-Click] §7Click to turn§c off!"
                            ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                if (event.isLeftClick()) {
                    player.closeInventory();
                    player.sendMessage(new String[]{
                            "",
                            "§7Please type in chat how much player can in server",
                            "§7Type §ccancel §7to cancel",
                            ""
                    });
                    JoinPluginHelper.maxPlayers.put(player.getUniqueId(), players);
                }
                if (event.isRightClick()) {
                    player.closeInventory();
                    config.set("MOTD.MaxPlayers.Enabled", false);
                    JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                    Ucolor.sendMessage(player, "&8[&5JoinPlugin&8]&d Max Players Disabled");
                    PlayerUI.openUI(player, new MenuGUI(player, 0));
                }
            });
        } else {
            set(5, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025"))
                    .name("§eSet Max Players")
                    .lore(
                            "",
                            "§7You are current disabled",
                            "§7this feature, please consider",
                            "§7turn on it to use this item!",
                            "",
                            "§7Click to turn§a on!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                config.set("MOTD.MaxPlayers.Enabled", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                Ucolor.sendMessage(player, "&8[&5JoinPlugin&8]&d Max Players Enabled");
                PlayerUI.openUI(player, new MenuGUI(player, 0));
            });
        }
        set(3, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/61c62f8de3b0d40d62a3c4ce65314f03a4b16401d895badc1dd08fe85b9fc1a3"))
                .name("§aMessages Type")
                .lore(
                        "",
                        "§7Click to see",
                        "§7all join messages.",
                        "",
                        "§7Click to§e see!"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuTestJoinMessages(player, this));
        });
        set(5, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/ec103f6eba5d1f23613af90628668929e4d83ff1abddfaac72226e94c0447fa5"))
                .name("§eConfig")
                .lore(
                        "",
                        "§7Click to set",
                        "§7join and leave",
                        "§7messages.",
                        "",
                        "§7Click to§e open!"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuConfig(player, this));
        });
        if (!JoinPlugin.getPlugin(JoinPlugin.class).getServer().hasWhitelist()) {
            set(1, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/c6692f99cc6d78242304110553589484298b2e4a0233b76753f888e207ef5"))
                    .name("§eToggle Whitelist")
                    .lore(
                            "",
                            "§7Click to toggle",
                            "§7whitelist.",
                            "",
                            "§7Current: §cFalse",
                            "",
                            "§7Click to§e toggle!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                JoinPlugin.getPlugin(JoinPlugin.class).getServer().setWhitelist(true);
                player.sendMessage("§8[§5JoinPlugin§8]§d Whitelist has been turn on");
            });
        } else {
            set(1, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/c6692f99cc6d78242304110553589484298b2e4a0233b76753f888e207ef5"))
                    .name("§eToggle Whitelist")
                    .lore(
                            "",
                            "§7Click to toggle",
                            "§7whitelist.",
                            "",
                            "§7Current: §aTrue",
                            "",
                            "§7Click to§e toggle!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                JoinPlugin.getPlugin(JoinPlugin.class).getServer().setWhitelist(false);
                player.sendMessage("§8[§5JoinPlugin§8]§d Whitelist has been turn off");
            });
        }
        set(7, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/884834614c8a1b48f9d479eab8bca3ac743ef1617e566ad1762298ea5f0d1a7f"))
                .name("§ePermissions List")
                .lore(
                        "",
                        "§7Permissions:",
                        "§6»§e joinplugin.view",
                        "§6»§e joinplugin.command.whitelist",
                        "§6»§e joinplugin.command.maxplayers",
                        "§6»§e joinplugin.command.displayservermotd",
                        "§6»§e joinplugin.command.displayjoinactionbar",
                        "§6»§e joinplugin.command.displayjoinbossbar",
                        "§6»§e joinplugin.command.displayjointitle",
                        "§6»§e joinplugin.command.maintenance",
                        "§6»§e joinplugin.command.menu",
                        "§6»§e joinplugin.command.permissions",
                        "§6»§e joinplugin.command.testgroup",
                        "§6»§e joinplugin.command.reload",
                        "§6»§e joinplugin.serverfull",
                        ""
                ).getItem(), null);
        if (!config.getBoolean("General.Maintenance.Enabled")) {
            set(7, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5123b88846d66e1cfe2f664a36ad4a22b1a4c2f2e4d295f41fe5e929b9e7d8"))
                    .name("§eMaintenance Mode")
                    .lore(
                            "",
                            "§7Click to toggle",
                            "§7maintenance mode.",
                            "",
                            "§7Current: §cDisabled",
                            "",
                            "§7Click to§e toggle!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                config.set("General.Maintenance.Enabled", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                Ucolor.sendMessage(player, config.getString("General.Maintenance.Maintenance Enabled"));
            });
        } else {
            set(7, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5123b88846d66e1cfe2f664a36ad4a22b1a4c2f2e4d295f41fe5e929b9e7d8"))
                    .name("§eMaintenance Mode")
                    .lore(
                            "",
                            "§7Click to toggle",
                            "§7maintenance mode.",
                            "",
                            "§7Current: §aEnabled",
                            "",
                            "§7Click to§e toggle!"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                config.set("General.Maintenance.Enabled", false);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                Ucolor.sendMessage(player, config.getString("General.Maintenance.Maintenance Disabled"));
            });
        }
        for (int i = 0; i < 9; i++) set(i, 4, BORDER, null);
        set(4, 4, new ItemBuilder(XMaterial.BARRIER.parseMaterial(), 1)
                .name("§cClose Menu").getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
        });
        set(7, 4, new ItemBuilder(XMaterial.CLOCK.parseMaterial(), 1)
                .name("§aReload Plugin")
                .lore(
                        "",
                        "§7Click to reload",
                        "§7the plugin.",
                        "",
                        "§7Click to§e reload!")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            JoinPlugin.getPlugin(JoinPlugin.class).reloadConfig();
            Titles.sendTitle(player, 20, 2 * 20, 20, Ucolor.colorize("&a&lConfig"), Ucolor.colorize("&a&lSuccessfully Reloaded!"));
        });
        set(3, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/91f26c21cbe1f3e5882ec59501e50c552757f81829c65c3fc4321055ae56d21c"))
                .name("§aMenu Sounds")
                .lore(
                        "",
                        "§7Open the",
                        "§7list sounds!",
                        "",
                        "§7Click to§e open"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuSounds(player, this));
        });
        set(1,1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.getSkull(player.getUniqueId()).getItemMeta())
                .name("§aManager Players Custom Message")
                .getItem(), null);
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }

}
