package me.sword7.starmail.gui.page;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.gui.*;
import me.sword7.starmail.gui.data.SessionData;
import me.sword7.starmail.gui.data.WarehouseData;
import me.sword7.starmail.sys.Language;
import me.sword7.starmail.warehouse.WarehouseCache;
import me.sword7.starmail.warehouse.WarehouseEntry;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class WarehouseFrom extends IUpdater {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        menu.setItem(4, Icons.createIcon(XMaterial.NAME_TAG.parseMaterial(), ChatColor.WHITE.toString() + Language.LABEL_FROM));
        WarehouseData warehouseData = (WarehouseData) sessionData;
        WarehouseEntry entry = warehouseData.getEntry();
        menu.setItem(8, Icons.createMail(entry.getMail()));

        ItemStack background = Icons.BACKGROUND_ITEM;
        ItemStack blackStack = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();

        menu.setItem(10, background);
        menu.setItem(11, blackStack);
        menu.setItem(13, blackStack);
        menu.setItem(14, background);
        menu.setItem(15, Icons.createIcon(XMaterial.LEVER.parseMaterial(), ChatColor.WHITE.toString() + Language.ICON_APPLY));
        menu.setItem(16, background);
        menu.setItem(22, Icons.CLOSE);

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        if (slot == 22) {
            sessionData.exit();
        } else if (slot == 15) {
            MenuUtil.playClickSound(player);
            ItemStack itemStack = menu.getItem(12);
            if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta()) {
                WarehouseData warehouseData = (WarehouseData) sessionData;
                WarehouseEntry entry = warehouseData.getEntry();
                entry.setFrom(itemStack.getItemMeta().getDisplayName());
                WarehouseCache.registerEdit(warehouseData.getType(), entry);
                menu.setItem(8, Icons.createMail(entry.getMail()));
                doAccept(player, sessionData, menu);
            } else {
                doReject(player, sessionData, menu);
            }
        }
    }

    private final List<Integer> insertable = Collections.singletonList(12);

    @Override
    public boolean isInsertable(int slot) {
        return slot == 12;
    }

    @Override
    public List<Integer> getOrderedSlots() {
        return insertable;
    }

    private final List<Integer> animationSlots = new ImmutableList.Builder<Integer>()
            .add(1).add(2).add(3).add(5).add(6).add(7)
            .add(9).add(17)
            .add(18).add(19).add(20).add(21).add(23).add(24).add(25).add(26)
            .build();

    @Override
    public List<Integer> getAnimationSlots() {
        return animationSlots;
    }
}
