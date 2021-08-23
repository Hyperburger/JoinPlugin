package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPluginHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuConfigBossBar extends ChestUI {

    public MenuConfigBossBar(Player player, MenuConfig menuConfig) {
        super(player, "Menu Config > BossBar", 4);

        set(1, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/90376dc5e3c981b52960578afe4bfc41c1778789bcd80ec2c2d2fd460e5a51a"))
                .name("§aConfig Join BossBar")
                .lore(
                        "",
                        "§7Config the",
                        "§7join bossbar",
                        "§7message!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the bossbar message you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinBossBarSet(player);
        });

        set(3, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/af22d7cd53d5bfe61eafbc2fb1ac94443eec24f455292139ac9fbdb83d0d09"))
                .name("§aConfig Join BossBar Style")
                .lore(
                        "",
                        "§7Config the",
                        "§7join bossbar",
                        "§7style!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the bossbar message you want to set",
                    "§7Type §ccancel §7to cancel",
                    "§ePossible styles: \"SOLID\", \"SEGMENTED_6\", \"SEGMENTED_10\", \"SEGMENTED_12\", \"SEGMENTED_20\"\n",
                    "§eSee at: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarStyle.html",
                    ""
            });
            JoinPluginHelper.markJoinBossBarStyleSet(player);
        });

        set(5, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/c7ff1377754563ab41b8a0305dac03de63e02e5a39a6956afd6ccabf295a96d8"))
                .name("§aConfig Join BossBar Color")
                .lore(
                        "",
                        "§7Config the",
                        "§7join bossbar",
                        "§7color!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the bossbar message you want to set",
                    "§7Type §ccancel §7to cancel",
                    "§ePossible styles: \"BLUE\", \"GREEN\", \"PINK\", \"PURPLE\", \"RED\", \"WHITE\", \"YELLOW\"",
                    "§eSee at: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarColor.html",
                    ""
            });
            JoinPluginHelper.markJoinBossBarColorSet(player);
        });

        set(7, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/d2a6f0e84daefc8b21aa99415b16ed5fdaa6d8dc0c3cd591f49ca832b575"))
                .name("§aConfig Join BossBar Duration")
                .lore(
                        "",
                        "§7Config the",
                        "§7join bossbar",
                        "§7duration!",
                        "",
                        "§7Click to§e set"
                ).getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the bossbar duration (sec) you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinBossBarDurationSet(player);
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
