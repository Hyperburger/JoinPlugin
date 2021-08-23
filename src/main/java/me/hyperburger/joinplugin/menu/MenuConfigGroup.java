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
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MenuConfigGroup extends ChestUI {

    public MenuConfigGroup(Player player, MenuGroups menuGroups, HashMap<String, Object> groups) {
        super(player, "Groups > Config Group [§a" + groups.get("Name") + "§r]", 4);

        Configuration config = JoinPlugin.getPlugin(JoinPlugin.class).getConfig();

        set(0, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5d8604b9e195367f85a23d03d9dd503638fcfb05b0032535bc43734422483bde"))
                .name("§aConfig Join Message")
                .lore(
                        "",
                        "§7Config the",
                        "§7join message!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the join message you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinMessageSet(player, (String) groups.get("Name"));
        });

        set(2, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/482c23992a02725d9ed1bcd90fd0307c8262d87e80ce6fac8078387de18d0851"))
                .name("§aConfig Leave Message")
                .lore(
                        "",
                        "§7Config the",
                        "§7leave message!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the leave message you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markLeaveMessageSet(player, (String) groups.get("Name"));
        });

        set(6, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/91f26c21cbe1f3e5882ec59501e50c552757f81829c65c3fc4321055ae56d21c"))
                .name("§aConfig Join Sound")
                .lore(
                        "",
                        "§7Config the",
                        "§7join sound!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the join sound you want to set",
                    "§7Type §ccancel §7to cancel",
                    "",
                    "§eSee at: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html"
            });
            JoinPluginHelper.markJoinSoundSet(player, (String) groups.get("Name"));
        });

        set(8, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/1cba7277fc895bf3b673694159864b83351a4d14717e476ebda1c3bf38fcf37"))
                .name("§aConfig Join Command(s)")
                .lore(
                        "",
                        "§7Config the",
                        "§7join command(s)!",
                        "",
                        "§7Click to§e open"
                ).getItem(), event -> {
            PlayerUI.openUI(player, new MenuConfigGroupCommands(player, this, groups));
        });

        if (config.getBoolean("Groups." + groups.get("Name") + ".Firework")) {
            set(4, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/315bbda12e1b832a6a6af85d8439152d9157ce104e6a7f7b36aeaccc863544"))
                    .name("§aToggle Join Firework")
                    .lore(
                            "",
                            "§7Toggle the",
                            "§7join firework!",
                            "",
                            "§7Current:§a Enabled",
                            "",
                            "§7Click to§e toggle"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                config.set("Groups." + groups.get("Name") + ".Firework", false);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                PlayerUI.openUI(player, new MenuConfigGroup(player, new MenuGroups(player, new MenuConfig(player, new MenuGUI(player, 0))), groups));
            });
        } else {
            set(4, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/315bbda12e1b832a6a6af85d8439152d9157ce104e6a7f7b36aeaccc863544"))
                    .name("§aToggle Join Firework")
                    .lore(
                            "",
                            "§7Toggle the",
                            "§7join firework!",
                            "",
                            "§7Current:§c Disabled",
                            "",
                            "§7Click to§e toggle"
                    ).getItem(), event -> {
                event.setCancelled(true);
                setCancelDragEvent(true);
                player.closeInventory();
                config.set("Groups." + groups.get("Name") + ".Firework", true);
                JoinPlugin.getPlugin(JoinPlugin.class).saveConfig();
                PlayerUI.openUI(player, new MenuConfigGroup(player, new MenuGroups(player, new MenuConfig(player, new MenuGUI(player, 0))), groups));
            });
        }

        for (int i = 1; i < 9; i++) set(i, 3, MenuGUI.BORDER, null);
        set(4, 3, new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                .name("§aSet permission")
                .lore(
                        "",
                        "§7Set permission",
                        "§7for this group!",
                        "",
                        "§7Current:§a " + config.getString("Groups." + groups.get("Name") + ".permission"),
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the permission you want to set",
                    "§7Type §ccancel §7to cancel",
                    "",
            });
            JoinPluginHelper.markGroupPermissionSet(player, (String) groups.get("Name"));
        });
        set(0, 3, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/8652e2b936ca8026bd28651d7c9f2819d2e923697734d18dfdb13550f8fdad5f"))
                .name("§e <- Go Back ").getItem(), event -> {
            PlayerUI.openUI(player, menuGroups);
        });
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
