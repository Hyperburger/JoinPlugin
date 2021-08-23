package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.PlayerUI;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPluginHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuConfigTitle extends ChestUI {

    public MenuConfigTitle(Player player, MenuConfig menuConfig) {
        super(player, "Menu Config > Title", 4);

        set(1, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5d8604b9e195367f85a23d03d9dd503638fcfb05b0032535bc43734422483bde"))
                .name("§aConfig Join Title")
                .lore(
                        "",
                        "§7Config the",
                        "§7join title!",
                        "",
                        "§7Click to§e set")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the title you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinTitleSet(player);
        });
        set(3, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/9a2d891c6ae9f6baa040d736ab84d48344bb6b70d7f1a280dd12cbac4d777"))
                .name("§aConfig Join SubTitle")
                .lore(
                        "",
                        "§7Config the",
                        "§7join subtitle!",
                        "",
                        "§7Click to§e set")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the subtitle you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinSubTitleSet(player);
        });
        set(5, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/d9b30303f94e7c785a31e5727a9381535daf4753449ea41db746e1234e9dd2b5"))
                .name("§aConfig Join Title Fade In")
                .lore(
                        "",
                        "§7Config the",
                        "§7join title",
                        "§7fade in!",
                        "",
                        "§7Click to§e set")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the title fade in (sec) you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinTitleFadeInSet(player);
        });
        set(6, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/e3ca7d7c1534dc6b9ed1647f9025ddf244e0107dc8dd4f4f0852c82081d6350e"))
                .name("§aConfig Join Title Stay")
                .lore(
                        "",
                        "§7Config the",
                        "§7join title",
                        "§7stay!",
                        "",
                        "§7Click to§e set")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the title stay (sec) you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinTitleStaySet(player);
        });
        set(7, 1, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/195e1e2fb2de7e6299a0f61ddf7d9a6d101f8d664f1959d3b67dce8b049a8ae1"))
                .name("§aConfig Join Title Fade Out")
                .lore(
                        "",
                        "§7Config the",
                        "§7join title",
                        "§7fade out!",
                        "",
                        "§7Click to§e set")
                .getItem(), event -> {
            event.setCancelled(true);
            setCancelDragEvent(true);
            player.closeInventory();
            player.sendMessage(new String[]{
                    "",
                    "§7Please type in chat the title fade out (sec) you want to set",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.markJoinTitleFadeOutSet(player);
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
