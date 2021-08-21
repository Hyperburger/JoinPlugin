package me.hyperburger.joinplugin.menu;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import featherpowders.ui.chest.ChestUI;
import featherpowders.utils.ItemBuilder;
import me.hyperburger.joinplugin.JoinPluginHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuGUI extends ChestUI {

    protected static final ItemStack BORDER = new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial(), 1).name("§0").getItem();
    protected static final ItemStack HEAD = new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1).getItem();

    public MenuGUI(Player player, int players) {
        super(player, "JoinPlugin Menu", 5);

        for (int i = 0; i < 9; i++) set(i, 0, BORDER, null);
        set(1, 2, new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, SkullUtils.applySkin(XMaterial.PLAYER_HEAD.parseItem().getItemMeta(), "http://textures.minecraft.net/texture/5d8604b9e195367f85a23d03d9dd503638fcfb05b0032535bc43734422483bde"))
                .name("§eSet Max Players")
                .lore("",
                        "§7Click to set",
                        "§7max player.",
                        "",
                        "§7Click to§e set!")
                .getItem(), event -> {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage(new String[] {
                    "",
                    "§7Please type in chat how much player can in server",
                    "§7Type §ccancel §7to cancel",
                    ""
            });
            JoinPluginHelper.maxPlayers.put(player.getUniqueId(), players);
        });
        set(3, 2, new ItemBuilder(XMaterial.PAPER.parseMaterial(), 1)
                .name("§eDisplay Join MOTD")
                .lore("",
                        "§7Click to see",
                        "§7you current join MOTD.",
                        "",
                        "§7Click to§e see!")
                .getItem(), event -> {
            event.setCancelled(true);
            player.closeInventory();
            player.performCommand("jp joinmotd");
        });
        for (int i = 36; i < 45; i++) set(i, 0, BORDER, null);
    }

    @Override
    public void failback(InventoryClickEvent event) {
        event.setCancelled(true);
    }

}
